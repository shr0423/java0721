package org.sp.app0721.network.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientMain extends JFrame{
	JPanel p_north;
	JComboBox box;
	JTextField t_port;
	JButton bt;
	
	JTextArea area;
	JScrollPane scroll;
	
	JTextField t_input;
	
	Socket socket;
	MessageThread mt;
	
	public ClientMain() {
		p_north = new JPanel();
		box = new JComboBox();
		t_port = new JTextField("7777", 6);
		bt = new JButton("접속");
		
		area = new JTextArea();
		scroll = new JScrollPane(area);
		
		t_input = new JTextField();
		
		//아이피 채워넣기
		box.addItem("192.168.1.226");
		
		//조립 
		p_north.add(box);
		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		
		add(scroll);
		add(t_input, BorderLayout.SOUTH);
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {//엔터치면..
					//서버에 메시지 전송
					send();
				
				}
			}
		});
	}
	
	//서버에 접속하기 
	public void connect() {
		int port=Integer.parseInt(t_port.getText());
		
		//소켓의 생성자 호출시 접속이 시도됨
		try {
			socket=new Socket((String)box.getSelectedItem(), port);
			
			//대화용 쓰레드 생성
			mt=new MessageThread(this);
			mt.start();
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	//서버에 메시지 보내기 
	public void send() {
		String msg=t_input.getText();
		
		mt.sendMsg(msg);//서버에 메시지 보내기
		
		//입력 테스트필드 초기화
		t_input.setText("");
		
	}
	
	public static void main(String[] args) {
		new ClientMain();
	}
}