import com.mongodb.*;
import com.mongodb.util.JSON;

import java.util.Arrays;
import java.util.Scanner;

public class AuthenticateMongo{
    public static void main(String[] args) {
        MongoClientOptions.Builder o = MongoClientOptions.builder().connectTimeout(3000);
        MongoCredential credential = MongoCredential.createCredential("admin", "admin", "123456".toCharArray());   //main poit here!!! use to connect to admin database
        MongoClient db = new MongoClient(new ServerAddress("localhost", 27017), Arrays.asList(credential), o.build()); //--straight in constructor

        DB testDataBase = db.getDB("test");
        DBCollection dbCollection = testDataBase.getCollection("first");

        Scanner sc = new Scanner(System.in);
        String command = null;
        while(true) {
            command = sc.nextLine();
            if(command.equals("exit")) break;
            else if(command.equals("findAll")) findAll(dbCollection);
            else if(command.equals("insertJSON")) insertJSON(sc, dbCollection);
            else if(command.equals("inser")) insert(sc, dbCollection);
            else if(command.equals("delete")) delete(sc, dbCollection);
            else if(command.equals("update")) update(sc, dbCollection);
        }

    }

    private static void insert(Scanner sc, DBCollection dbCollection){
        //1
        DBObject dbObject = new BasicDBObject();
        dbObject.put("name", sc.nextLine());
        //2
        dbObject.put("name", sc.nextLine());
        //3
        String str = sc.nextLine();
        String[] temp = str.split(",");
        int i = 0;
        BasicDBList tempList = new BasicDBList();
        DBObject tempp = null;
        while (i<temp.length){
            tempp = new BasicDBObject();
            tempp.put("name", temp[i]);
            tempList.add(tempp);
        }
        dbObject.put("tut",tempp);
        dbCollection.insert(dbObject);
    }
    private static void delete(Scanner sc, DBCollection dbCollection){
        DBObject dbObject = new BasicDBObject();
        dbObject.put("name", sc.nextLine());
        dbCollection.remove(dbObject);
    }
    private static void update(Scanner sc, DBCollection dbCollection){
        //from
        DBObject fromdbObject = new BasicDBObject();
        fromdbObject.put("name", sc.nextLine());
        //to
        DBObject dbObject = new BasicDBObject();
        dbObject.put("name", sc.nextLine());
        //action
        dbCollection.update(fromdbObject, dbObject);
    }

    private static void findAll(DBCollection dbCollection){
        DBCursor dbCursor = dbCollection.find();
        while (dbCursor.hasNext()){
            System.out.println(dbCursor.next());
        }
    }

    public static void insertJSON(Scanner sc, DBCollection dbCollection) {
        dbCollection.insert((DBObject) JSON.parse(sc.nextLine()));
    }


}
