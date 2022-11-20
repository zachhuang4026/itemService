package edu.uchicago.zachhuang4026.quarkus.Repositories;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import io.quarkus.runtime.StartupEvent;
import org.bson.Document;
import edu.uchicago.zachhuang4026.quarkus.Models.Object;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class ObjectRepository {
    @Inject
    MongoClient mongoClient;

    private Gson gson = new Gson();
    public static final int PAGE_SIZE = 20;

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("object_db").getCollection("object_collection");
    }

    private Object doc2item(Document document) {
        if (document != null && !document.isEmpty()) {
            return gson.fromJson(document.toJson(), Object.class);
        }
        return null;

    }

    private Document item2doc(Object object) {
        if (object != null) {
            String json = gson.toJson(object);
            return Document.parse(json);
        }
        return null;
    }

    public List<Object> paged( int page){
        List<Object> objects = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).skip(PAGE_SIZE * (page - 1)).limit(PAGE_SIZE).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                objects.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public Object add(Object object) {
        object.setId(UUID.randomUUID().toString());
        getCollection().insertOne(item2doc(object));
        return object;
    }

    public Object get(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        FindIterable<Document> documents = getCollection().find(query);

        List<Object> objects = new ArrayList<>();
        for (Document document : documents) {
            objects.add(doc2item(document));
        }

        //this will produce a 404 not found
        if (objects.size() != 1) return null;

        return objects.get(0);
    }

    public List<Object> getAll() {
        List<Object> objects = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                objects.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public Object delete( String id ) {
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

    public Object update( String id, Object newObject) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        System.out.print(id);
        BasicDBObject update = new BasicDBObject();
        update.put("id", id);
        update.put("name", newObject.getName());
        update.put("description", newObject.getDescription());
        update.put("categoryID", newObject.getCategoryID());
        update.put("categoryName", newObject.getCategoryName());
        update.put("quantity", newObject.getQuantity());
        update.put("bidPrice", newObject.getBidPrice());
        update.put("isSold", newObject.isSold());
        System.out.println(newObject.getName());
        getCollection().replaceOne( query, update );

        return newObject;

    }


}
