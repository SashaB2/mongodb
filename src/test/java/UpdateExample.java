import com.mongodb.*;

import java.util.Arrays;

public class UpdateExample {
    public static void main(String[] args) {
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        MongoCredential mongoCredential = MongoCredential.createCredential("admin", "admin", "123456".toCharArray());
        MongoClientOptions.Builder o = MongoClientOptions.builder().connectTimeout(3000);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential), o.build());

        changeValueInCollection(mongoClient.getDB("test"), "one","name", "Sasha123");
        deleteKey(mongoClient.getDB("test"), "_id","ObjectId(\"5bfb2340669eb2d30c985f43\")","one","name");
    }


    private static void changeValueInCollection(DB databaseName, String collectionName, String key, String value){
        DBCollection collection = databaseName.getCollection(collectionName);
        if(String.valueOf(collection.find().one()).contains(key)){
            BasicDBObject toDocument = new BasicDBObject().append("$set", new BasicDBObject().append(key, value));
            collection.update(new BasicDBObject(), toDocument);
            System.out.println(collection.find().one());

        }else{
            System.out.println("No suck key in collection");
        }
    }

    public static void deleteKey(DB databaseName, String collectionName, String queryKey, String queryValue, String key){
        DBCollection collection = databaseName.getCollection(collectionName);
        if(String.valueOf(collection.find().one()).contains(key)){
            BasicDBObject toDocument = new BasicDBObject().append("$unset", new BasicDBObject().append(key, ""));

            collection.update(new BasicDBObject().append(queryKey, queryValue), toDocument);

        }else{
            System.out.println("No suck key in collection");
        }
    }
}
