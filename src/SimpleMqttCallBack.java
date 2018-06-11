import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class SimpleMqttCallBack implements MqttCallback{
	String MongoDB_IP = "127.0.0.1";
    int MongoDB_PORT = 27017;
    String DB_NAME = "testDB";
    
    //Connect to MongoDB
    MongoClient  mongoClient = new MongoClient(new ServerAddress(MongoDB_IP, MongoDB_PORT));
    DB db = mongoClient.getDB(DB_NAME);
    DBCollection collection = db.getCollection("testCollection01");
    
	//연결 끊겼을 때 실행
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection to MQTT broker lost!");
	}
	
	//토픽이 수신되었을 때
	public void messageArrived(String topic, MqttMessage mqttMessage) 
			throws Exception{
		
		final String location = topic.substring(topic.indexOf("/")+1, topic.lastIndexOf("/")); // 센서의 위치(in 또는 out)
        final String sensor = topic.substring(topic.lastIndexOf("/")+1); // 센서명(temp/humid/dust/rain)
        final String value = new String(mqttMessage.getPayload());  // 센서값
		System.out.println("lsv:\t" + location+")"+sensor+": "+value);
		
		//mongoDB에 insert
		
	}
	
	//QoS를 위한 메서드
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		
	}

}
