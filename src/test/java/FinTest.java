import com.mongodb.*;

import java.util.Arrays;

public class FinTest {
    public static void main(String[] args) {
        ServerAddress serverAddress = new ServerAddress("localhost", 27017);
        MongoCredential mongoCredential = MongoCredential.createCredential("admin", "admin", "123456".toCharArray());
        MongoClientOptions.Builder o = MongoClientOptions.builder().connectTimeout(3000);
        MongoClient mongoClient = new MongoClient(serverAddress, Arrays.asList(mongoCredential), o.build());

        chnageNameInCollection(mongoClient.getDB("test"), "one","two", "Sasha123");

    }


    private static void chnageNameInCollection(DB databaseName, String collectionName, String key, String value){
        DBCollection collection = databaseName.getCollection(collectionName);
        BasicDBObject toDocument = new BasicDBObject().append("$set", new BasicDBObject().append(key, value));
        if(String.valueOf(collection.find().one()).contains(key)){
            collection.update(new BasicDBObject(), toDocument);
            System.out.println(collection.find().one());
        }else{
            System.out.println("No suck key in collection");
        }
    }
}
