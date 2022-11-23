package edu.uchicago.zachhuang4026.quarkus.Repositories;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;
import org.bson.Document;
import edu.uchicago.zachhuang4026.quarkus.Models.Category;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CategoryRepository {
    @Inject
    MongoClient mongoClient;

    private Gson gson = new Gson();
    public static final int PAGE_SIZE = 20;

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("category_db").getCollection("category_collection");
    }

    private Category doc2item(Document document) {
        if (document != null && !document.isEmpty()) {
            return gson.fromJson(document.toJson(), Category.class);
        }
        return null;
    }

    private Document item2doc(Category category) {
        if (category != null) {
            String json = gson.toJson(category);
            return Document.parse(json);
        }
        return null;
    }

    public List<Category> paged( int page){
        List<Category> categories = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).skip(PAGE_SIZE * (page - 1)).limit(PAGE_SIZE).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                categories.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category add(Category category) {
        category.setId(UUID.randomUUID().toString());
        getCollection().insertOne(item2doc(category));
        return category;
    }

    public Category get(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        FindIterable<Document> documents = getCollection().find(query);

        List<Category> categories = new ArrayList<>();
        for (Document document : documents) {
            categories.add(doc2item(document));
        }

        //this will produce a 404 not found
        if (categories.size() != 1) return null;

        return categories.get(0);
    }

    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                categories.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    public Category delete( String id ) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        FindIterable<Document> documents = getCollection().find(query);

        Document firstDocument = null;
        for (Document document : documents) {
            firstDocument = document;
            break;
        }
        getCollection().deleteOne(firstDocument);
        return doc2item(firstDocument);
    }

    public Category update( String id, Category newCategory) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        BasicDBObject update = new BasicDBObject();
        update.put("id", id);
        update.put("name", newCategory.getName());

        getCollection().replaceOne( query, update );

        return newCategory;

    }
}
