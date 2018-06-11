import java.util.HashMap;
import java.util.Map;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.util.JSON;
   
public class MongoDB {
    public static void main(String args[]){
        String MongoDB_IP = "127.0.0.1";
        int MongoDB_PORT = 27017;
        String DB_NAME = "testDB";
     
        //Connect to MongoDB
        MongoClient  mongoClient = new MongoClient(new ServerAddress(MongoDB_IP, MongoDB_PORT));
        DB db = mongoClient.getDB(DB_NAME);
        DBCollection collection = db.getCollection("testCollection01");
        
        //=========== Make Data01 by BasicDBObject ===========
        BasicDBObject document = new BasicDBObject();
        document.put("one", "data01");
        document.put("two", "BasicDBObject");
  
        BasicDBObject documentDetail = new BasicDBObject();
        documentDetail.put("three-one", 99);
        documentDetail.put("three-two", "BasicDBObject");
        documentDetail.put("three-three", "true");
        document.put("three", documentDetail);
  
        //Insert Data01
        collection.insert(document);
      
        //=========== Make Data02 by BasicDBObjectBuilder ===========
        BasicDBObjectBuilder documentBuilder = BasicDBObjectBuilder.start()
                .add("one", "data02").add("two", "BasicDBObjectBuilder");
        BasicDBObjectBuilder documentBuilderDetail = BasicDBObjectBuilder.start()
                .add("three-one", 98).add("three-two", "BasicDBObjectBuilder").add("three-three", "fasle");
        documentBuilder.add("three", documentBuilderDetail.get());
     
        //Insert Data02
        collection.insert(documentBuilder.get());
  
        //=========== Make Data03 by BasicDBObjectBuilder ===========
        Map<String, Object> documentMap = new HashMap<String, Object>();
        documentMap.put("one", "data03");
        documentMap.put("two", "Map");
  
        Map<String, Object> documentMapDetail = new HashMap<String, Object>();
        documentMapDetail.put("three-one", 97);
        documentMapDetail.put("three-two", "Map");
        documentMapDetail.put("three-three", "true");
        documentMap.put("three", documentMapDetail);
  
        //Insert Data03
        collection.insert(new BasicDBObject(documentMap));
      
        //=========== Make Data04 by json ===========
        String json = "{'one' : 'data04','two' : 'json'," +
                "'three' : {'three-one' : 96, 'three-two' : 'json', 'three-three' : 'false'}}}";
        DBObject dbObject = (DBObject)JSON.parse(json);
  
        //Insert Data04
        collection.insert(dbObject);
       
        //Check Data in Database
        DBCursor cursorDocBuilder = collection.find();
        while (cursorDocBuilder.hasNext()) {
            System.out.println(cursorDocBuilder.next());
        }
    }
}
