import com.mongodb.*;

import java.util.Arrays;

public class FindMongo {
    public static void main(String[] args) {
        MongoCredential credential = MongoCredential.createCredential("admin", "admin", "123456".toCharArray());   //main poit here!!! use to connect to admin database
        MongoClient db = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential)); //--straight in constructor

        DB testDataBase = db.getDB("test");
        DBCollection dbCollection = testDataBase.getCollection("first");

        //find collection by value
        {
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("key1", "val1");

            DBCursor dbCursor = dbCollection.find(basicDBObject);
            while(dbCursor.hasNext()){
                System.out.println(dbCursor.next());
            }
        }
    }
}
