import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class loginthread implements Runnable {
	//这些final属性是用来接受客户端信号的
	final int LOG = 100, REG = 101, CLOSED = 102;
	
	boolean kongzhi=true;//这个是个开关，具体看后面解释

	Socket socket;

//Vector m_thread;

	DataInputStream in;

	DataOutputStream out;

	int jieshou;//暂时保存接收到的信号，可以是LOG，REG,CLOSED

	Connection c;//数据库连接类

	Statement s;//sql表达式类

	ResultSet r;//结果集，这也是一个类似于List的容器

	public loginthread(Socket s) {
		socket = s;
//m_thread = m;
		try {
			in = new DataInputStream(socket.getInputStream());//in属性保存该客户端的inputstream,out同理
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {/*线程的执行序列，可以想象成每个线程是一段代码，
		要在电脑里同时执行多段代码，run中写的就是代码的内容*/

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:客户");
			s = c.createStatement();//连接数据库
			while (kongzhi) {
				try {
					jieshou = in.read();//读一个信号，判断是注册登陆还是退出
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				} catch (IOException e) {
					return;
				}
				//登陆
				if (jieshou == LOG) {
					try {
						String zhanghao = in.readUTF();//客户端把这些信息读过来通过socket传到这里
						String mima = in.readUTF();

						r = s.executeQuery("select 用户名,昵称,密码,性别,头像,积分,等级,胜局数,负局数,平局数 from 表1");
						//执行查询，获得结果集ResultSet r
						while (r.next()) {
							String z = r.getString("用户名");
							String m = r.getString("密码");
							if (z.equals(zhanghao) && m.equals(mima)) {
								String x = r.getString("性别");
								String n = r.getString("昵称");
								int d = r.getInt("等级");
								int t = r.getInt("头像");
								int j=r.getInt("积分");
								int shengju=r.getInt("胜局数");
								int fuju=r.getInt("负局数");
								int ping=r.getInt("平局数");
								player xinWanjia = new player(z,n,x,d,t,j,shengju,fuju,ping);
								//用上面获取的结果集信息初始化了一个player对象
								Lobby ting = new Lobby(in,out,socket, xinWanjia);
								//为该用户初始化一个大厅对象
								Login.dating.add(ting);//把该用户的大厅加入到Login类的dating容器里
								Login.wanjia.add(ting);
								out.writeUTF("DENGLUCHENGGONG");
								ting.went();//开始执行大厅对象的方法，等待用户对大厅的操作 例如加入房间之类的
								s.close();
								//r.close();
								c.close();
								return;
							}
						}
						//下面是用户名不存在的情况
						out.writeUTF("DENGLUSHIBAI");
						socket.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
					//从客户端收到的信息为关闭窗口
				} else if (jieshou == CLOSED) {
					try {
						in.close();
						out.close();
						socket.close();
						return;
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
				}
				//从客户端收到的信息为注册
				else if (jieshou == REG) {
					boolean shifou=true;
					try {
						//输入流读各种信息
						String zhanghao = in.readUTF();
						String nicheng = in.readUTF();
						String xingbie = in.readUTF();
						String mima = in.readUTF();
						int touxiang = in.read();
						//执行查询
						r = s.executeQuery("select 用户名 from 表1");
						while (r.next()) {
							String str = r.getString("用户名");
System.out.println(str);
                        //用户名重复
						if (zhanghao.equals(str)) {
								out.writeUTF("ZHUCESHIBAI");
								shifou=false;
								//return;
						} }
						//可以注册
						if (shifou){
								s.executeUpdate("INSERT INTO 表1 (用户名,昵称,密码,性别,头像,积分,等级,胜局数,负局数,平局数) VALUES('"+ zhanghao+ "','"+ nicheng+ "','"+ mima+ "','"+ xingbie+ "',"+touxiang+",0,0,0,0,0)");
								s.close();
								//r.close();
								c.close();
							out.writeUTF("注册成功");
							socket.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
				}
			}

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
			return;
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
	}
}
