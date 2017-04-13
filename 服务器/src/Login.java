
import java.net.*;
import java.util.*;
import java.io.*;
public class Login {
	//这些是Arraylist 注意是static属性，可以不通过实例化对象访问这些变量
	//Arraylist是一种容器 目的是为了持有对象
	//fangjian1-12保存12个房间里的玩家信息
	static List<room> fangjian1=new ArrayList<room>();
	static List<room> fangjian2=new ArrayList<room>();
	static List<room> fangjian3=new ArrayList<room>();
	static List<room> fangjian4=new ArrayList<room>();
	static List<room> fangjian5=new ArrayList<room>();
	static List<room> fangjian6=new ArrayList<room>();
	static List<room> fangjian7=new ArrayList<room>();
	static List<room> fangjian8=new ArrayList<room>();
	static List<room> fangjian9=new ArrayList<room>();
	static List<room> fangjian10=new ArrayList<room>();
	static List<room> fangjian11=new ArrayList<room>();
	static List<room> fangjian12=new ArrayList<room>();
	static List<Lobby> dating=new ArrayList<Lobby>();
	static List<Lobby> wanjia=new ArrayList<Lobby>();
	public static void main(String[] args) {
		//Vector<DengluXiancheng> m_threads=new Vector<DengluXiancheng>();
		//ss是服务器socket，s是每一个客户端通信使用的socket
		ServerSocket ss=null;
		Socket socket=null;
		try{
			ss=new ServerSocket(8888);
		}catch(IOException e){
			System.out.println(e);
			System.exit(0);
		}
		while(true){
			try{
				System.out.println("正在等待连接端口 prot=8888");
				
				//收到每个客户端请求，初始化一个登陆线程
				socket=ss.accept();
System.out.println("yigeduankou");
//loginthread是实现了runnable接口的类，可以通过new Thread(Runnable r)这种构造函数生成一个线程
				loginthread kehu=new loginthread(socket);
				//m_threads.addElement(kehu);
				Thread xiancheng=new Thread(kehu);
				//这里的start相当于调用loginthread中run()方法
				xiancheng.start();
				}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
}
