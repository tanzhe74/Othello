import java.net.*;
import java.io.*;
import java.util.*;
public class Lobby {
	public Socket socket;
	player wanjia;
	DataInputStream in;
	DataOutputStream out;
	boolean kongzhi=true;
	//��loginthread����������ʼ����Lobby�࣬�����������������������û�socket��wanjia����
	public Lobby(DataInputStream in,DataOutputStream out,Socket s,player wan){
		socket=s;
		wanjia=wan;
		this.in=in;
		this.out=out;		
	}
	//��ʼִ��Lobby��Ĳ���
	public void went(){
		send();//���������Lobby�������£�������û��ͻ���������пͻ�����Ϣ
		for(int b=0;b<Login.dating.size();b++){//����ô����ͻ���������д����ͻ��˴������û���Ϣ
			try {
				if(Login.dating.get(b)!=this){
				Login.dating.get(b).out.writeUTF("ADD");//����źŵ���˼�ο��ͻ��� ���߶��ſ�
				Login.dating.get(b).out.writeUTF(wanjia.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			try {
				while(kongzhi){
				String weizhi=in.readUTF();
				//�û�����˳�ʱ�Ĵ������������
				if(weizhi.equals("EXIT")){
System.out.println("EXIT");
					in.close();
					out.close();
					kongzhi=false;
					Login.dating.remove(this);
					Login.wanjia.remove(this);
System.out.print(Login.dating.size());
					for(int a=0;a<Login.dating.size();a++)
						Login.dating.get(a).send();//�����д�����������û���Ϣ
					socket.close();
					return;
				}
				//���뷿��Ĵ���
				if(weizhi.equals("ENTER")){
					int room=in.read();
					String location=in.readUTF();
					wanjia.setRoom(room);
					wanjia.setLocation(location);
System.out.println("���ڵ�λ��Ϊ��"+location+"����Ϊ��"+room);
					Login.dating.remove(this);//����dating�Ƴ�dating���List��δ�Ƴ�wanjia���List
System.out.println("�������滹��"+Login.dating.size()+"����");
					for(int a=0;a<Login.dating.size();a++){
						Login.dating.get(a).out.writeUTF("ENTER");
						Login.dating.get(a).out.writeUTF(wanjia.toString());
					}
					out.writeUTF("ZIJIENTER");
					out.writeUTF(location);
					room fang=null;//������ҵ���Ϣ��ʼ��һ��room���󣬲��Ҽ����û�ѡ���room List��
					if(room==1){
						fang=new room(in,out,socket,wanjia,Login.fangjian1);
						Login.fangjian1.add(fang);
						//Login�е�fangjian1-fangjian12�Ǳ���Login�����ArrayList��
						//����Arraylist������Ϊ�ǿɱ任���ȵ�����
					}else if(room==2){
						fang=new room(in,out,socket,wanjia,Login.fangjian2);
						Login.fangjian2.add(fang);
					}else if(room==3){
						fang=new room(in,out,socket,wanjia,Login.fangjian3);
						Login.fangjian3.add(fang);
					}else if(room==4){
						fang=new room(in,out,socket,wanjia,Login.fangjian4);
						Login.fangjian4.add(fang);
					}else if(room==5){
						fang=new room(in,out,socket,wanjia,Login.fangjian5);
						Login.fangjian5.add(fang);
					}else if(room==6){
						fang=new room(in,out,socket,wanjia,Login.fangjian6);
						Login.fangjian6.add(fang);
					}else if(room==7){
						fang=new room(in,out,socket,wanjia,Login.fangjian7);
						Login.fangjian7.add(fang);
					}else if(room==8){
						fang=new room(in,out,socket,wanjia,Login.fangjian8);
						Login.fangjian8.add(fang);
					}else if(room==9){
						fang=new room(in,out,socket,wanjia,Login.fangjian9);
						Login.fangjian9.add(fang);
					}else if(room==10){
						fang=new room(in,out,socket,wanjia,Login.fangjian10);
						Login.fangjian10.add(fang);
					}else if(room==11){
						fang=new room(in,out,socket,wanjia,Login.fangjian11);
						Login.fangjian11.add(fang);
					}else if(room==0){
						fang=new room(in,out,socket,wanjia,Login.fangjian12);
						Login.fangjian12.add(fang);
					}
					fang.go();//��ʼִ�з���Ĳ���
					Login.dating.add(this);
					for(int a=0;a<Login.dating.size();a++)
						Login.dating.get(a).send();
					}
				}
			} catch (IOException e) {
				return;
			}
	}
	//��ͻ��������Ϣ	
	public void send(){
		try{
			out.writeUTF("SHUAXIN");
			out.write(Login.wanjia.size());
		for(int i=0;i<Login.wanjia.size();i++){
			Lobby d=Login.wanjia.get(i);
			out.writeUTF(d.wanjia.toString());
			}
		}catch (IOException e) {
				e.printStackTrace();
			}
		}
}
