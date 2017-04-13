import java.net.*;
import java.io.*;
import java.util.*;
public class Lobby {
	public Socket socket;
	player wanjia;
	DataInputStream in;
	DataOutputStream out;
	boolean kongzhi=true;
	//在loginthread这个类里面初始化了Lobby类，传入参数是输入流输出流和用户socket及wanjia对象
	public Lobby(DataInputStream in,DataOutputStream out,Socket s,player wan){
		socket=s;
		wanjia=wan;
		this.in=in;
		this.out=out;		
	}
	//开始执行Lobby类的操作
	public void went(){
		send();//这个方法在Lobby类的最底下，是向该用户客户端输出所有客户的信息
		for(int b=0;b<Login.dating.size();b++){//向除该大厅客户端外的所有大厅客户端传出该用户信息
			try {
				if(Login.dating.get(b)!=this){
				Login.dating.get(b).out.writeUTF("ADD");//这个信号的意思参考客户端 两边对着看
				Login.dating.get(b).out.writeUTF(wanjia.toString());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			try {
				while(kongzhi){
				String weizhi=in.readUTF();
				//用户点击退出时的处理，后面的类似
				if(weizhi.equals("EXIT")){
System.out.println("EXIT");
					in.close();
					out.close();
					kongzhi=false;
					Login.dating.remove(this);
					Login.wanjia.remove(this);
System.out.print(Login.dating.size());
					for(int a=0;a<Login.dating.size();a++)
						Login.dating.get(a).send();//向所有大厅输出所有用户信息
					socket.close();
					return;
				}
				//进入房间的处理
				if(weizhi.equals("ENTER")){
					int room=in.read();
					String location=in.readUTF();
					wanjia.setRoom(room);
					wanjia.setLocation(location);
System.out.println("所在的位置为："+location+"房间为："+room);
					Login.dating.remove(this);//将该dating移除dating这个List并未移除wanjia这个List
System.out.println("大厅里面还有"+Login.dating.size()+"个人");
					for(int a=0;a<Login.dating.size();a++){
						Login.dating.get(a).out.writeUTF("ENTER");
						Login.dating.get(a).out.writeUTF(wanjia.toString());
					}
					out.writeUTF("ZIJIENTER");
					out.writeUTF(location);
					room fang=null;//将该玩家的信息初始化一个room对象，并且加入用户选择的room List中
					if(room==1){
						fang=new room(in,out,socket,wanjia,Login.fangjian1);
						Login.fangjian1.add(fang);
						//Login中的fangjian1-fangjian12是保存Login对象的ArrayList，
						//对于Arraylist可以认为是可变换长度的数组
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
					fang.go();//开始执行房间的操作
					Login.dating.add(this);
					for(int a=0;a<Login.dating.size();a++)
						Login.dating.get(a).send();
					}
				}
			} catch (IOException e) {
				return;
			}
	}
	//向客户端输出信息	
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
