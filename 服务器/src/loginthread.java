import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class loginthread implements Runnable {
	//��Щfinal�������������ܿͻ����źŵ�
	final int LOG = 100, REG = 101, CLOSED = 102;
	
	boolean kongzhi=true;//����Ǹ����أ����忴�������

	Socket socket;

//Vector m_thread;

	DataInputStream in;

	DataOutputStream out;

	int jieshou;//��ʱ������յ����źţ�������LOG��REG,CLOSED

	Connection c;//���ݿ�������

	Statement s;//sql���ʽ��

	ResultSet r;//���������Ҳ��һ��������List������

	public loginthread(Socket s) {
		socket = s;
//m_thread = m;
		try {
			in = new DataInputStream(socket.getInputStream());//in���Ա���ÿͻ��˵�inputstream,outͬ��
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {/*�̵߳�ִ�����У����������ÿ���߳���һ�δ��룬
		Ҫ�ڵ�����ͬʱִ�ж�δ��룬run��д�ľ��Ǵ��������*/

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:�ͻ�");
			s = c.createStatement();//�������ݿ�
			while (kongzhi) {
				try {
					jieshou = in.read();//��һ���źţ��ж���ע���½�����˳�
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return;
				} catch (IOException e) {
					return;
				}
				//��½
				if (jieshou == LOG) {
					try {
						String zhanghao = in.readUTF();//�ͻ��˰���Щ��Ϣ������ͨ��socket��������
						String mima = in.readUTF();

						r = s.executeQuery("select �û���,�ǳ�,����,�Ա�,ͷ��,����,�ȼ�,ʤ����,������,ƽ���� from ��1");
						//ִ�в�ѯ����ý����ResultSet r
						while (r.next()) {
							String z = r.getString("�û���");
							String m = r.getString("����");
							if (z.equals(zhanghao) && m.equals(mima)) {
								String x = r.getString("�Ա�");
								String n = r.getString("�ǳ�");
								int d = r.getInt("�ȼ�");
								int t = r.getInt("ͷ��");
								int j=r.getInt("����");
								int shengju=r.getInt("ʤ����");
								int fuju=r.getInt("������");
								int ping=r.getInt("ƽ����");
								player xinWanjia = new player(z,n,x,d,t,j,shengju,fuju,ping);
								//�������ȡ�Ľ������Ϣ��ʼ����һ��player����
								Lobby ting = new Lobby(in,out,socket, xinWanjia);
								//Ϊ���û���ʼ��һ����������
								Login.dating.add(ting);//�Ѹ��û��Ĵ������뵽Login���dating������
								Login.wanjia.add(ting);
								out.writeUTF("DENGLUCHENGGONG");
								ting.went();//��ʼִ�д�������ķ������ȴ��û��Դ����Ĳ��� ������뷿��֮���
								s.close();
								//r.close();
								c.close();
								return;
							}
						}
						//�������û��������ڵ����
						out.writeUTF("DENGLUSHIBAI");
						socket.close();
					} catch (SQLException e) {
						e.printStackTrace();
						return;
					} catch (IOException e) {
						e.printStackTrace();
						return;
					}
					//�ӿͻ����յ�����ϢΪ�رմ���
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
				//�ӿͻ����յ�����ϢΪע��
				else if (jieshou == REG) {
					boolean shifou=true;
					try {
						//��������������Ϣ
						String zhanghao = in.readUTF();
						String nicheng = in.readUTF();
						String xingbie = in.readUTF();
						String mima = in.readUTF();
						int touxiang = in.read();
						//ִ�в�ѯ
						r = s.executeQuery("select �û��� from ��1");
						while (r.next()) {
							String str = r.getString("�û���");
System.out.println(str);
                        //�û����ظ�
						if (zhanghao.equals(str)) {
								out.writeUTF("ZHUCESHIBAI");
								shifou=false;
								//return;
						} }
						//����ע��
						if (shifou){
								s.executeUpdate("INSERT INTO ��1 (�û���,�ǳ�,����,�Ա�,ͷ��,����,�ȼ�,ʤ����,������,ƽ����) VALUES('"+ zhanghao+ "','"+ nicheng+ "','"+ mima+ "','"+ xingbie+ "',"+touxiang+",0,0,0,0,0)");
								s.close();
								//r.close();
								c.close();
							out.writeUTF("ע��ɹ�");
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
