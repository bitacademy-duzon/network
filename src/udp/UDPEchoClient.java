package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class UDPEchoClient {
	private static final String SERVER_IP = "218.39.221.90";
	
	public static void main(String[] args) {
		Scanner scanner = null;
		DatagramSocket socket = null;
		
		try {
			//1. 키보드 연결
			scanner = new Scanner(System.in);
			
			//2. 소켓 생성
			socket = new DatagramSocket();
			
			while(true) {
				
				//3. 사용자 입력 받음
				System.out.print(">>");
				String message = scanner.nextLine();
				
				if("quit".equals(message)) {
					break;
				}
				
				//4. 메세지 전송
				byte[] data = message.getBytes("UTF-8");
				DatagramPacket sendPacket = new DatagramPacket(
					data,
					data.length,
					new InetSocketAddress(SERVER_IP, UDPEchoServer.PORT)
				);
				socket.send(sendPacket);
			}
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			// 자원정리
			if(scanner != null) {
				scanner.close();
			}
			if(socket != null && socket.isClosed() == false) {
				socket.close();
			}
		}
	}
}
