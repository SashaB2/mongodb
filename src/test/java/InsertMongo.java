import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertMongo {
    public static void main(String[] args) {
        List<ServerAddress> seeds = new ArrayList<ServerAddress>();
        seeds.add(new ServerAddress("localhost", 27017));   //createconnection
        List<MongoCredential> mongoCredentials = new ArrayList<MongoCredential>();   // create credentials
        mongoCredentials.add(
                MongoCredential.createCredential("admin", "admin", "123456".toCharArray())
        );

        MongoClient db = new MongoClient(seeds, mongoCredentials);  //pretty
        DB testDataBase = db.getDB("test");
        DBCollection dbCollection = testDataBase.getCollection("first");

//        add value to collection
        {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("key3","val1");
            basicDBObject.put("key4","val2");
            dbCollection.insert(basicDBObject);
        }



    }
}
