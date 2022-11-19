package edu.uchicago.zachhuang4026.quarkus.Repositories;
import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import edu.uchicago.zachhuang4026.quarkus.Models.Result;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.HashMap;

@ApplicationScoped
public class PeopleRepository {

    @Inject
    MongoClient mongoClient;

    private Gson gson = new Gson();
    public static final int PAGE_SIZE = 20;
//    void onStart(@Observes StartupEvent ev) {
//        long collectionSize = getCollection().countDocuments();
//        if (collectionSize > 0) return;
//
//        Faker faker = new Faker();
//
//        getCollection().insertMany(
//                Stream.generate(
//                                () -> new Item(
//                                        UUID.randomUUID().toString(),
//                                        faker.beer().name(),
//                                        faker.address().country())
//                        )
//                        .peek(item -> System.out.println(item))
//                        .map(item -> item2doc(item))
//                        .limit(1000).collect(Collectors.toList())
//
//        );
//    }

    private MongoCollection getCollection() {
        return mongoClient.getDatabase("people_db").getCollection("people_collection");
    }

    private Result doc2item(Document document) {
        if (document != null && !document.isEmpty()) {
            return gson.fromJson(document.toJson(), Result.class);
        }
        return null;

    }

    private Document item2doc(Result result) {
        if (result != null) {
            String json = gson.toJson(result);
            return Document.parse(json);
        }
        return null;
    }

    public List<Result> paged( int page){
        List<Result> results = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).skip(PAGE_SIZE * (page - 1)).limit(PAGE_SIZE).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                results.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public Result add(Result result) {
        result.setId(UUID.randomUUID().toString());
        getCollection().insertOne(item2doc(result));
        return result;
    }

    public Result get(String id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);

        FindIterable<Document> documents = getCollection().find(query);

        List<Result> results = new ArrayList<>();
        for (Document document : documents) {
            results.add(doc2item(document));
        }

        //this will produce a 404 not found
        if (results.size() != 1) return null;

        return results.get(0);
    }

    public String getAll() {
        HashMap<String, List<Result>> resultMap = new HashMap<String, List<Result>>();
        List<Result> results = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        try {
            MongoCursor<Document> cursor =
                    getCollection().find(query).iterator();
            while (cursor.hasNext()) {
                Document document = cursor.next();
                results.add(doc2item(document));
            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        resultMap.put("results", results);
        String json = new Gson().toJson(resultMap);
        return json;
    }

    public Result delete( String id ) {
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

    public Result update( String id, Result newResult) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);


        BasicDBObject update = new BasicDBObject();
        update.put("id", newResult.getId());
        update.put("name", newResult.getName());
        update.put("image", newResult.getImage());
        update.put("description", newResult.getDescription());
        update.put("type", newResult.getType());
        update.put("url", newResult.getUrl());
        update.put("detailedDescription", newResult.getDetailedDescription());

        getCollection().replaceOne( query, update );

        return newResult;

    }

}
