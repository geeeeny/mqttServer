import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class MongoDB {
	String MongoDB_IP = "70.12.112.61";
	int MongoDB_PORT = 27017;
	String DB_NAME = "dadajo";
	MongoClient mongoClient;
	DB db;
	DBCollection collection;

	public MongoDB() {
		// Connect to MongoDB
		mongoClient = new MongoClient(new ServerAddress(MongoDB_IP, MongoDB_PORT));
		db = mongoClient.getDB(DB_NAME);
		collection = db.getCollection("window");
		
		getWindowState();
	}
	
	//초기 창문 상태 받아오기
	public void getWindowState() {
		collection = db.getCollection("window");
		DBCursor dbCursor = collection.find().sort(new BasicDBObject("date", -1)).limit(1);
		System.out.println(dbCursor.next());
	}
}
