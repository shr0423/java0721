package org.sp.app0721.network.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//채팅 클라이언트가 엔터를 치지 않아도 서버의메시지를 얻어와야 하므로
//쓰레드로 무한루프를 실행시키기위해(실시간청취)
public class MessageThread extends Thread{
	ClientMain clientMain;
	Socket socket;
	BufferedReader buffr;
	BufferedWriter buffw;
	
	public MessageThread(ClientMain clientMain) {
		this.clientMain=clientMain;
		socket=clientMain.socket;
		
		try {
			buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void listen() {
		String msg=null;
		try {
			msg=buffr.readLine();//서버의 말 받아오기
			clientMain.area.append(msg+"\n");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	public void sendMsg(String msg) {
		try {
			buffw.write(msg+"\n");
			buffw.flush();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	//서버의 메시지를 끝없이 청취
	public void run() {
		while(true) {
			listen();
		}
	}
	
}
