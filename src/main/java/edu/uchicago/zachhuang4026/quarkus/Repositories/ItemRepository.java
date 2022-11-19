package edu.uchicago.zachhuang4026.quarkus.Repositories;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.uchicago.zachhuang4026.quarkus.Models.Item;
import io.quarkus.runtime.StartupEvent;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
@ApplicationScoped
public class ItemRepository {

    @Inject
    MongoClient mongoClient;

    private Gson gson = new Gson();
    public static final int PAGE_SIZE = 20;
    void onStart(@Observes StartupEvent ev) {
        long collectionSize = getCollection().countDocuments();
        if (collectionSize > 0) return;

        Faker faker = new Faker();

        getCollection().insertMany(
                Stream.generate(
                                () -> new Item(
                                        UUID.randomUUID().toString(),
                                        faker.beer().name(),
                                        faker.address().country())
                        )
                        .peek(item -> System.out.println(item))
                        .map(item -> item2doc(item))
                        .limit(1000).collect(Collectors.toList())

        );
    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("beer_db").getCollection("beer_collection");
    }

    private Item doc2item(Document document) {
        if (document != null && !document.isEmpty()) {
            return gson.fromJson(document.toJson(), Item.class);
        }
        return null;

    }

    private Document item2doc(Item item) {
        if (item != null) {
            String json = gson.toJson(item);
            return Document.parse(json);
        }
        return null;
    }

    public List<Item> paged( int page){
        List<Item> items = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).skip(PAGE_SIZE * (page - 1)).limit(PAGE_SIZE).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                items.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item add(Item item) {
        item.setId(UUID.randomUUID().toString());
        getCollection().insertOne(item2doc(item));
        return item;
    }

    public Item get(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        FindIterable<Document> documents = getCollection().find(query);

        List<Item> items = new ArrayList<>();
        for (Document document : documents) {
            items.add(doc2item(document));
        }

        //this will produce a 404 not found
        if (items.size() != 1) return null;

        return items.get(0);
    }

    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                items.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item delete( String id ) {
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

    public Item update( String id, Item newItem) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);


        BasicDBObject update = new BasicDBObject();
        update.put("id", newItem.getId());
        update.put("name", newItem.getName());
        update.put("origin", newItem.getOrigin());

        getCollection().replaceOne( query, update );

        return newItem;

    }

}
