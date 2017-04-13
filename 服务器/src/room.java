import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
//room类的操作，由Lobby类初始化,里面的操作由Lobby对象调用
public class room {
	Socket socket;//玩家的socket
	player wanjia;//保存玩家基本信息
	List<room> ren=null;//保存和这个room对象一个房间的所有room对象
	DataInputStream in;
	DataOutputStream out;
	boolean control=true;
	int zhuang=0;
	Connection c;

	Statement s;

	ResultSet r;
	public room(DataInputStream in,DataOutputStream out,Socket s,player wan,List list){
		ren=list;//这个list最终指向了Login中的一些静态属性，参见Login中的注释
		wanjia=wan;
		socket=s;
		this.in=in;
		this.out=out;
	}
	//开始执行操作
	public void go(){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:客户");
			s = c.createStatement();

			fangjianSend();//在最后
System.out.println("房间中有"+ren.size()+"个人");
			for(int i=0;i<ren.size();i++){//向所有该房间的其他客户端发出本用户加入房间的信息
				if(!ren.get(i).equals(this)){
					ren.get(i).out.writeUTF("JIARU");
					ren.get(i).out.writeUTF(wanjia.toString());
					}
			}
System.out.println("当前玩家的位置为"+wanjia.getLocation());
			if(wanjia.getLocation().equals("MIDDLE")){
				for(int i=0;i<ren.size();i++){
					if(ren.get(i).zhuang!=0){
						ren.get(i).out.writeUTF("DEDAOQIPU");
						int aa=ren.indexOf(this);
						ren.get(i).out.write(aa);
						break;
					}
				}
			}
			//后面是所有与其他玩家信息的交互
			while(control){
				String str=in.readUTF();
				if(str.equals("TUICHU")){
					ren.remove(this);
					zhuang=0;
					wanjia.setRoom(-1);
					wanjia.setLocation("MIDDLE");
					wanjia.setZhuangtai(0);
					out.writeUTF("ZIJITUICHU");
					for(int i=0;i<ren.size();i++)
						ren.get(i).fangjianSend();
					return;
				}else if(str.equals("ZHUNBEI")){
					for(int i=0;i<ren.size();i++){
						if(ren.get(i).zhuang==1)
							{zhuang=2;
							break;}
						if(ren.get(i).zhuang==2)
							{zhuang=1;
							break;}
					}
					if(zhuang==0){
						Random generator=new Random();
						int zhuangtai=generator.nextInt(2)+1;
						zhuang=zhuangtai;}
					out.writeUTF("DANGQIANZHUANGTAI");
					out.write(zhuang);
					out.flush();
					wanjia.setZhuangtai(zhuang);
System.out.println("玩家状态为"+zhuang);
					for(int i=0;i<ren.size();i++){
							ren.get(i).out.writeUTF("ZHUNBEI");
							ren.get(i).out.writeUTF(wanjia.getLocation());
							ren.get(i).out.write(zhuang);
							ren.get(i).out.flush();
					}
				}else if(str.equals("XIAQI")){
					String weizhi=in.readUTF();
System.out.println("收到下棋命令");
					for(int i=0;i<ren.size();i++){
						ren.get(i).out.writeUTF("XIAQI");
						ren.get(i).out.writeUTF(weizhi);
						ren.get(i).out.flush();
System.out.println("下棋的位置为"+weizhi);
					}
				}else if(str.equals("HUIQI")){
					for(int i=0;i<ren.size();i++){
						if(!ren.get(i).equals(this)){
						ren.get(i).out.writeUTF("HUIQI");
						ren.get(i).out.writeUTF(wanjia.getname());
						}
						}
				}else if(str.equals("LIAOTIAN")){
					String word=in.readUTF();
System.out.println("客户端发送了一句话");
					for(int i=0;i<ren.size();i++){
System.out.println("服务器发送了一句话");
						ren.get(i).out.writeUTF("LIAOTIAN");
						ren.get(i).out.writeUTF(wanjia.getname());
						ren.get(i).out.writeUTF(word);
					}
				}else if(str.equals("HEQI")){
System.out.println("客户请求和棋");
					for(int i=0;i<ren.size();i++){
						if(!ren.get(i).equals(this)){
						ren.get(i).out.writeUTF("HEQI");
						ren.get(i).out.writeUTF(wanjia.getname());
						}
					}
				}else if(str.equals("TAOPAO")){
					ren.remove(this);
					zhuang=0;
					wanjia.setRoom(-1);
					wanjia.setLocation("MIDDLE");
					wanjia.setZhuangtai(0);
					out.writeUTF("ZIJITUICHU");
					for(int c=0;c<ren.size();c++)
						{ren.get(c).out.writeUTF("TAOPAO");
						ren.get(c).out.writeUTF(wanjia.getname());
						}
//for(int i=0;i<ren.size();i++)
//ren.get(i).fangjianSend();
					return;
					
				}else if(str.equals("发送棋谱")){
System.out.println("接收到棋谱");
					int count=in.read();
					int index=in.read();
					String sss=in.readUTF();
System.out.println("Count的值为"+count);
					ren.get(index).out.writeUTF("DANGQIANQIPU");
					ren.get(index).out.write(count);
					ren.get(index).out.writeUTF(sss);
					ren.get(index).out.flush();
System.out.println(sss);
				}
				else if(str.equals("RENSHU")){
					for(int i=0;i<ren.size();i++){
						if(!ren.get(i).equals(this)){
						ren.get(i).out.writeUTF("RENSHU");
						ren.get(i).out.writeUTF(wanjia.getname());
						}
					}
				}else if(str.equals("HEQI_TONGYI")){
					String shifou=in.readUTF();
					for(int i=0;i<ren.size();i++){
						ren.get(i).out.writeUTF("HEQI_TONGYI");
						ren.get(i).out.writeUTF(shifou);
						ren.get(i).out.writeUTF(wanjia.getname());
						}
				}else if(str.equals("HUIQI_TONGYI")){
					String shifou=in.readUTF();
					for(int i=0;i<ren.size();i++){
						ren.get(i).out.writeUTF("HUIQI_TONGYI");
						ren.get(i).out.writeUTF(shifou);
						ren.get(i).out.writeUTF(wanjia.getZhanghao());
					}
				}else if(str.equals("赢了")){
					int aa=wanjia.getSheng()+1;
					int bb=wanjia.getJifen()+2;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setSheng(wanjia.getSheng()+1);
					wanjia.setJifen(wanjia.getJifen()+2);
					s.executeUpdate("UPDATE 表1 SET 胜局数=胜局数+1 WHERE 用户名='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE 表1 SET 积分=积分+2 WHERE 用户名='"+wanjia.getZhanghao()+"'");
					for(int i=0;i<ren.size();i++)
						ren.get(i).fangjianSend();
					
				}else if(str.equals("输了")){
					int aa=wanjia.getFu()+1;
					int bb=wanjia.getJifen()-1;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setFu(wanjia.getFu()+1);
					wanjia.setJifen(wanjia.getJifen()-1);
					s.executeUpdate("UPDATE 表1 SET 负局数=负局数+1 WHERE 用户名='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE 表1 SET 积分=积分-1 WHERE 用户名='"+wanjia.getZhanghao()+"'");
				}else if(str.equals("和了")){
					int aa=wanjia.getPing()+1;
					int bb=wanjia.getPing()+1;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setPing(wanjia.getPing()+1);
					wanjia.setJifen(wanjia.getJifen()+1);
					s.executeUpdate("UPDATE 表1 SET 平局数=平局数+1 WHERE 用户名='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE 表1 SET 积分=积分+1 WHERE 用户名='"+wanjia.getZhanghao()+"'");
					for(int i=0;i<ren.size();i++)
						ren.get(i).fangjianSend();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//发送房间信息
	public void fangjianSend(){
		try {
			out.writeUTF("SHUAXIN");
System.out.println("向客户端发送了一句话");
			out.write(ren.size());
			out.flush();
System.out.println("房间中有"+ren.size());
			for(int a=0;a<ren.size();a++)
				{out.writeUTF(ren.get(a).wanjia.toString());
				out.flush();}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
