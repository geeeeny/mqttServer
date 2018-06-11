import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

public class Subscriber {

	public static void main(String[] args) throws MqttException {

		System.out.println("== START SUBSCRIBER ==");
		
		MqttClient client = new MqttClient("tcp://localhost:1883", 
									MqttClient.generateClientId());
		client.setCallback(new SimpleMqttCallBack());
		client.connect();
		
		//지정한 토픽으로 구독자 등록
		client.subscribe("iot_data");
	}

}
