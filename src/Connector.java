import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

import youxian.ncumap.ActivityData;

public class Connector {
	//設定要連線的IP和PORT
	static SocketAddress sockaddr = new InetSocketAddress("", 1234); 
public static String SendWriteDataCommand(ActivityData a){
		
		String s="";
		Socket sock = new Socket();
		try {    
			sock.connect(sockaddr,1000);  //建立連線,延時1秒
			ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
			oos.writeObject("2;140.115.123.111;");
			oos.writeObject(a);
			ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
			ois.available();
			s = (String)ois.readObject();
			sock.close();
		}
		catch(IOException e) {
			//NotConnected(context);
			return "failure";
		}catch(Exception e) {
			//NotConnected(context);
			e.printStackTrace();
		}
		return s;
		
	}
@SuppressWarnings("unchecked")
public static List<ActivityData> SendGetDataCommand(){
	List<ActivityData> activitys = new ArrayList<ActivityData>();
	Socket sock = new Socket();
	try {    
		sock.connect(sockaddr,10000);  //建立連線,延時1秒
		
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject("1;140.115.123.111;");

		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		ois.available();
		//String message = (String)ois.readObject();
		String s = (String)ois.readObject();
		System.out.print(s);
		activitys = (List<ActivityData>)ois.readObject();		
		if (activitys == null) {
			new Exception();
		}/*
		else{
			List<ActivityData> t = new ArrayList<ActivityData>();
			for(int i=activitys.size()-1;i>=0;i--){
				t.add(activitys.get(i));
			}
			sock.close();
			return t;
		}*/
		sock.close();  
	}catch(IOException e) {
		//NotConnected(context);
		return null;
	}catch(Exception e) {
		//NotConnected(context);
		e.printStackTrace();
	}
	return activitys;
	
	}
public static String SendDeleteDataCommand(int id){
	
	String s="";
	Socket sock = new Socket();
	try {    
		sock.connect(sockaddr,1000);  //建立連線,延時1秒
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject("7;140.115.123.111;");
		oos.writeObject(id);
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		ois.available();
		s = (String)ois.readObject();
		sock.close();
	}
	catch(IOException e) {
		//NotConnected(context);
		return "failure";
	}catch(Exception e) {
		//NotConnected(context);
		e.printStackTrace();
	}
	return s;
	
}
public static String SendEditDataCommand(ActivityData a){
	
	String s="";
	Socket sock = new Socket();
	try {    
		sock.connect(sockaddr,1000);  //建立連線,延時1秒
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject("8;140.115.123.111;");
		oos.writeObject(a);
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		ois.available();
		s = (String)ois.readObject();
		sock.close();
	}
	catch(IOException e) {
		//NotConnected(context);
		return "failure";
	}catch(Exception e) {
		//NotConnected(context);
		e.printStackTrace();
	}
	return s;
	
}
public static ActivityData SendGetDataByIdCommand(int id){
	ActivityData a = null;
	//String s="";
	Socket sock = new Socket();
	try {    
		sock.connect(sockaddr,1000);  //建立連線,延時1秒
		ObjectOutputStream oos = new ObjectOutputStream(sock.getOutputStream());
		oos.writeObject("9;140.115.123.111;");
		oos.writeObject(id);
		ObjectInputStream ois = new ObjectInputStream(sock.getInputStream());
		ois.available();
		a = (ActivityData)ois.readObject();
		sock.close();
	}
	catch(IOException e) {
		//NotConnected(context);
		return null;
	}catch(Exception e) {
		//NotConnected(context);
		e.printStackTrace();
	}
	return a;
	
}
}
