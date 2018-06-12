import java.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback {
	MongoDB mongoDB = new MongoDB();
	
	// 연결 끊겼을 때 실행
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection to MQTT broker lost!");
	}

	// 토픽이 수신되었을 때
	public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {

		final String location = topic.substring(topic.indexOf("/") + 1, topic.lastIndexOf("/")); // 센서의 위치(in 또는 out)
		final String sensor = topic.substring(topic.lastIndexOf("/") + 1); // 센서명(temp/humid/dust/rain)
		final String value = new String(mqttMessage.getPayload()); // 센서값
		System.out.println("lsv:\t" + location + ")" + sensor + ": " + value);

		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

/*		// mongoDB에 insert
		collection = db.getCollection(location);

		BasicDBObject document = new BasicDBObject();

		document.put("date", timeStamp.toString());
		document.put(sensor, value);

		collection.insert(document);
*/
	}

	// QoS를 위한 메서드
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

	}

}