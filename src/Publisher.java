import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class Publisher {

	public static void main(String[] args) throws MqttException {
		
		String messageString = "Hello World from Java!";
		
		if(args.length == 2) {
			messageString = args[1];
		}
		System.out.println("== START PUBLISHER ==");
		
		//클라이언트 생성 및 접속
		MqttClient client = new MqttClient("tcp://localhost:1883",
									MqttClient.generateClientId());
		client.connect();
		
		//토픽 발행
		MqttMessage message = new MqttMessage();
		message.setPayload(messageString.getBytes());
		client.publish("iot_data", message);
		
		System.out.println("\tMessage '"+messageString+"' to 'iot_data'");
		
		//연결 끊기
		client.disconnect();
		client.close();
		System.out.println("== END PUBLISHER ==");
	}

}
