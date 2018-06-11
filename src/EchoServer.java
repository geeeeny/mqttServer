import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(3000);
		System.out.println("Server 준비 완료");
		
		while(true) {
			Socket socket = serverSocket.accept(); //클라이언트의 요청 받음
			System.out.println("Client가 연결되었습니다.");
			try(
				BufferedReader in = new BufferedReader(
						new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(
						new OutputStreamWriter(socket.getOutputStream()));
			){
				String msg = in.readLine(); //요청에 개행문자가 포함되어 있어야 함!
				System.out.println("Client 에서 보낸 메시지: " + msg);
				
				out.println(msg);
				out.flush(); //출력 버퍼 비움
			}catch(Exception e) {
				e.printStackTrace();
			}
		}// end of while
	}//end of main

}
