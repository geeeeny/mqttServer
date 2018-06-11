import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class SimpleMqttCallBack implements MqttCallback{

	//연결 끊겼을 때 실행
	public void connectionLost(Throwable throwable) {
		System.out.println("Connection to MQTT broker lost!");
	}
	
	//토픽이 수신되었을 때
	public void messageArrived(String topic, MqttMessage mqttMessage) 
			throws Exception{
		System.out.println("Message received:\t" + 
				new String(mqttMessage.getPayload()));
	}
	
	//QoS를 위한 메서드
	public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
		
	}

}
