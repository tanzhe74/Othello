
import java.net.*;
import java.util.*;
import java.io.*;
public class Login {
	//��Щ��Arraylist ע����static���ԣ����Բ�ͨ��ʵ�������������Щ����
	//Arraylist��һ������ Ŀ����Ϊ�˳��ж���
	//fangjian1-12����12��������������Ϣ
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
		//ss�Ƿ�����socket��s��ÿһ���ͻ���ͨ��ʹ�õ�socket
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
				System.out.println("���ڵȴ����Ӷ˿� prot=8888");
				
				//�յ�ÿ���ͻ������󣬳�ʼ��һ����½�߳�
				socket=ss.accept();
System.out.println("yigeduankou");
//loginthread��ʵ����runnable�ӿڵ��࣬����ͨ��new Thread(Runnable r)���ֹ��캯������һ���߳�
				loginthread kehu=new loginthread(socket);
				//m_threads.addElement(kehu);
				Thread xiancheng=new Thread(kehu);
				//�����start�൱�ڵ���loginthread��run()����
				xiancheng.start();
				}catch(Exception e){
				System.out.println(e);
			}
		}
	}
	
}
