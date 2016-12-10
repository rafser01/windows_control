package com.rakin.reverse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

@ServerEndpoint("/reverse")
public class ReverseWebSocketEndpoint {
	
	@OnMessage
	public void handleMessage(Session session, String message) throws IOException {
		String[] args=message.split("\\s+");
		System.out.println("msg "+message);
		String result=null;
       
			  result= executeMethod(args) + " :  (from your server)";
		 
        System.out.println(result);
		/*session.getBasicRemote()
				.sendText("Reversed: " + new StringBuilder(result).reverse());*/
        session.getBasicRemote().sendText("Reversed: "+result);
		 
		 System.out.println("finish");
}
	@OnOpen
	public void onOpenMsg(){
		System.out.println("mesg");
		
	}
	/*
	@OnMessage
    public void echo(String message)  {
			byte[] decoded=org.apache.commons.codec.binary.Base64.decodeBase64(message.getBytes());
			 FileUtils.writeByteArrayToFile(new File("C:/Users/HP/Desktop/tttt.csv"), decoded);
		System.out.println(message);
		String result=null;
        try {
			  result= executeMethod(message) + " :  (from your server)";
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(result);
         
    }*/
	
	public String executeMethod(String[] command) {
		ProcessBuilder builder=new ProcessBuilder(command);
		Process process;
		StringBuilder sb=new StringBuilder();
		try {
			process = builder.start();
			int errorCode=process.waitFor();
			System.out.println("Error = "+(errorCode==0 ? "No" : "Yes"));
			
			
			BufferedReader br=new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line+System.getProperty("line.separator"));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return sb.toString();
		
		
		
	}
	
	/* @OnMessage
	 public void onMessage(Session session, ByteBuffer message) throws IOException {
	        System.out.println("Binary Data");      

	        //FileUtils.writeByteArrayToFile(new File("C:/Users/HP/Desktop/tttt.csv"), message.get());

	        new FileOutputStream(new File("C:/Users/HP/Desktop/tttt.csv")).write(message.get());;
	    }*/
	
	 
 

}
