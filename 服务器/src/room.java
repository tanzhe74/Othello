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
//room��Ĳ�������Lobby���ʼ��,����Ĳ�����Lobby�������
public class room {
	Socket socket;//��ҵ�socket
	player wanjia;//������һ�����Ϣ
	List<room> ren=null;//��������room����һ�����������room����
	DataInputStream in;
	DataOutputStream out;
	boolean control=true;
	int zhuang=0;
	Connection c;

	Statement s;

	ResultSet r;
	public room(DataInputStream in,DataOutputStream out,Socket s,player wan,List list){
		ren=list;//���list����ָ����Login�е�һЩ��̬���ԣ��μ�Login�е�ע��
		wanjia=wan;
		socket=s;
		this.in=in;
		this.out=out;
	}
	//��ʼִ�в���
	public void go(){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			c = DriverManager.getConnection("jdbc:odbc:�ͻ�");
			s = c.createStatement();

			fangjianSend();//�����
System.out.println("��������"+ren.size()+"����");
			for(int i=0;i<ren.size();i++){//�����и÷���������ͻ��˷������û����뷿�����Ϣ
				if(!ren.get(i).equals(this)){
					ren.get(i).out.writeUTF("JIARU");
					ren.get(i).out.writeUTF(wanjia.toString());
					}
			}
System.out.println("��ǰ��ҵ�λ��Ϊ"+wanjia.getLocation());
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
			//���������������������Ϣ�Ľ���
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
System.out.println("���״̬Ϊ"+zhuang);
					for(int i=0;i<ren.size();i++){
							ren.get(i).out.writeUTF("ZHUNBEI");
							ren.get(i).out.writeUTF(wanjia.getLocation());
							ren.get(i).out.write(zhuang);
							ren.get(i).out.flush();
					}
				}else if(str.equals("XIAQI")){
					String weizhi=in.readUTF();
System.out.println("�յ���������");
					for(int i=0;i<ren.size();i++){
						ren.get(i).out.writeUTF("XIAQI");
						ren.get(i).out.writeUTF(weizhi);
						ren.get(i).out.flush();
System.out.println("�����λ��Ϊ"+weizhi);
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
System.out.println("�ͻ��˷�����һ�仰");
					for(int i=0;i<ren.size();i++){
System.out.println("������������һ�仰");
						ren.get(i).out.writeUTF("LIAOTIAN");
						ren.get(i).out.writeUTF(wanjia.getname());
						ren.get(i).out.writeUTF(word);
					}
				}else if(str.equals("HEQI")){
System.out.println("�ͻ��������");
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
					
				}else if(str.equals("��������")){
System.out.println("���յ�����");
					int count=in.read();
					int index=in.read();
					String sss=in.readUTF();
System.out.println("Count��ֵΪ"+count);
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
				}else if(str.equals("Ӯ��")){
					int aa=wanjia.getSheng()+1;
					int bb=wanjia.getJifen()+2;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setSheng(wanjia.getSheng()+1);
					wanjia.setJifen(wanjia.getJifen()+2);
					s.executeUpdate("UPDATE ��1 SET ʤ����=ʤ����+1 WHERE �û���='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE ��1 SET ����=����+2 WHERE �û���='"+wanjia.getZhanghao()+"'");
					for(int i=0;i<ren.size();i++)
						ren.get(i).fangjianSend();
					
				}else if(str.equals("����")){
					int aa=wanjia.getFu()+1;
					int bb=wanjia.getJifen()-1;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setFu(wanjia.getFu()+1);
					wanjia.setJifen(wanjia.getJifen()-1);
					s.executeUpdate("UPDATE ��1 SET ������=������+1 WHERE �û���='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE ��1 SET ����=����-1 WHERE �û���='"+wanjia.getZhanghao()+"'");
				}else if(str.equals("����")){
					int aa=wanjia.getPing()+1;
					int bb=wanjia.getPing()+1;
					zhuang=0;
					wanjia.setZhuangtai(0);
					wanjia.setPing(wanjia.getPing()+1);
					wanjia.setJifen(wanjia.getJifen()+1);
					s.executeUpdate("UPDATE ��1 SET ƽ����=ƽ����+1 WHERE �û���='"+wanjia.getZhanghao()+"'");
					s.executeUpdate("UPDATE ��1 SET ����=����+1 WHERE �û���='"+wanjia.getZhanghao()+"'");
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
	
	
	//���ͷ�����Ϣ
	public void fangjianSend(){
		try {
			out.writeUTF("SHUAXIN");
System.out.println("��ͻ��˷�����һ�仰");
			out.write(ren.size());
			out.flush();
System.out.println("��������"+ren.size());
			for(int a=0;a<ren.size();a++)
				{out.writeUTF(ren.get(a).wanjia.toString());
				out.flush();}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
