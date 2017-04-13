import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.*;
public class visitorroom extends JFrame implements ActionListener{
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	String weizhi;
	int count=1;
	int count1=0;
	int count2=0;
	int wanjiashu=0;
	arithmetic control;
	int second=30;
	boolean kongzhi=true;
	JButton[][] button=new JButton[8][8];
	Panel panel5=new Panel();
	Panel panel6=new Panel();
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JPanel panel4=new JPanel();
	JPanel panel8=new JPanel();
	ImageIcon heizi=new ImageIcon("bc.png");
	ImageIcon baizi=new ImageIcon("wc.png");
	TextField field;
	TextArea area;
	JButton button1;
	JButton kaishi;
	JButton huiqi;
	JButton qiuhe;
	JButton renshu;
	JButton cunpan;
	JButton fupan;
	JLabel label1=new JLabel("���ӣ�2");
	JLabel label2=new JLabel("���ӣ�2");
	JLabel time1=new JLabel();
	JLabel time2=new JLabel();
	TextArea panel7=new TextArea();
	MusicBox music=new MusicBox();
	private Timer timer1= new Timer(1000, new timeListener1());
	private Timer timer2= new Timer(1000, new timeListener2());
	public visitorroom(Socket s,DataInputStream in,DataOutputStream out){
		super();
		setBounds(0,0,1100,720);
		setLayout(null);
		this.setResizable(false);
		addWindowListener(new WindowEye());
		panel1.setLayout(new GridLayout(8,8));
		
		time1.setBounds(0,292,200,30);
		time2.setBounds(0,597,200,20);
		panel5.setBounds(0,20,200,220);
		panel6.setBounds(0,320,200,217);
		panel1.setBounds(200,0,640,640);
		panel2.setBounds(260,650,560,50);
		panel3.setBounds(0,240,200,60);
		panel4.setBounds(0,540,200,60);
		panel7.setBounds(850,100,240,200);
		panel7.setEditable(false);
		panel8.setBounds(850,610,250,100);
		music.setBounds(850,0,240,100);
		label1.setBounds(200,650,60,20);
		label2.setBounds(200,670,60,20);
		time1.setText("ʣ��ʱ��"+30);
		time2.setText("ʣ��ʱ��"+30);
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				button[a][b]=new JButton("");
				Color brown=new Color(236,190,98);
				button[a][b].setBackground(brown);
				panel1.add(button[a][b]);
				button[a][b].setActionCommand(""+a+" "+b);
			}
		button[3][3].setIcon(heizi);
		button[3][4].setIcon(baizi);
		button[4][3].setIcon(baizi);
		button[4][4].setIcon(heizi);
		
		kaishi=new JButton("��ʼ");

		huiqi=new JButton("����");

		qiuhe=new JButton("���");

		renshu=new JButton("����");

		cunpan=new JButton("����");
		fupan=new JButton("����");
		panel2.add(kaishi);
		panel2.add(huiqi);
		panel2.add(qiuhe);
		panel2.add(renshu);
		panel2.add(cunpan);
		cunpan.addActionListener(this);
		panel2.add(fupan);
		fupan.addActionListener(this);
		
		field=new TextField(20);
		area=new TextArea(20,10);
		area.setEditable(false);
		button1=new JButton("����");
		button1.addActionListener(this);
		panel8.add(field);
		panel8.add(button1);
		area.setBounds(850,300,240,290);
		
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		add(panel6);
		add(area);
		add(panel7);
		add(panel8);
		add(time1);
		add(time2);
		add(music);
		add(label1);
		add(label2);
		setVisible(true);
		
		
		socket=s;
		this.in=in;
		this.out=out;
		control=new arithmetic();
		wanjiashu=0;
	}
	
		public void actionPerformed(ActionEvent e){
			String actionCommand=e.getActionCommand();
			try{
			if(actionCommand.equals("����")){
				String fa=field.getText();
			field.setText(null);
					if(fa!=null){
						out.writeUTF("LIAOTIAN");
						out.writeUTF(fa+"\n");
					}
			}else if(actionCommand.equals("����")){
				try
				{PrintWriter qipu=new PrintWriter(new FileOutputStream("����.txt"));
				String ssss="";
				for(int aa=0;aa<8;aa++)
					for(int bb=0;bb<8;bb++){
						 ssss=ssss+" "+control.shuzu[aa][bb];
					}
				qipu.println(ssss);
				qipu.close();
				JOptionPane.showMessageDialog(null, "���̳ɹ�");
				}
				catch(FileNotFoundException e1)
				{JOptionPane.showMessageDialog(null, "����ʧ��");
				}
			}else if(actionCommand.equals("����")&&wanjiashu==0){
				try
				{
				
					BufferedReader student=new BufferedReader(new FileReader("����.txt"));
					String line=student.readLine();
					Scanner scan=new Scanner(line);
					for(int aa=0;aa<8;aa++)
						for(int bb=0;bb<8;bb++){
						int cc=scan.nextInt();
						if(cc==1)
							button[aa][bb].setIcon(heizi);
						else if(cc==-1)
							button[aa][bb].setIcon(baizi);
						else if(cc==0)
							button[aa][bb].setIcon(null);
					}
				student.close();
				}
				catch(FileNotFoundException e1)
				{
					JOptionPane.showMessageDialog(null, "����δ���̣�");
				}
			}
				}catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	
	private class WindowEye extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			try {
				out.writeUTF("TUICHU");
				dispose();
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class timeListener1 implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			second--;
			time1.setText("����ʱ:"+second);
			if (second == 0) {
				timer1.stop();
				JOptionPane.showMessageDialog(null, "һ����ʱ");
		        	panel5.setZhuangtai(0);
		        	panel6.setZhuangtai(0);
		        	wanjiashu=0;
		        	}
				 }
	}
	private class timeListener2 implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			second--;
			time2.setText("����ʱ:"+second);
			if (second == 0) {
				timer2.stop();
				JOptionPane.showMessageDialog(null, "һ����ʱ");
				panel5.setZhuangtai(0);
		        panel6.setZhuangtai(0);
		        	wanjiashu=0;
				} 
			}
		}
	
	public void jiemianshuaxin(){
		control=new arithmetic();
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				button[a][b].setIcon(null);
			}
		button[3][3].setIcon(heizi);
		button[3][4].setIcon(baizi);
		button[4][3].setIcon(baizi);
		button[4][4].setIcon(heizi);
	}
	
	public void jiaohuan(){
System.out.println("Count="+count);
	int zhuang1=(-2*(count1-1))+1;
	int zhuang2=(-2*(count2-1))+1;
		if(wanjiashu==2&&count==zhuang1)
		{second=30;
				timer1.start();
				timer2.stop();
		}else if(wanjiashu==2&&count==zhuang2){
			second=30;
					timer1.stop();
					timer2.start();
		}
	}
	
	
		public void go(){
			while(true){
System.out.println("�߳̿�ʼ");
				try {
					String duqu=in.readUTF();
System.out.println("�յ���Ӧ");
					if(duqu.equals("SHUAXIN")){
System.out.println("�յ�ˢ��");
						panel5.shuaxinPanel();
						panel6.shuaxinPanel();
						panel7.setText("�������"+"\t"+"����"+"\t"+"�ȼ�"+"\t"+"ʤ����"+"\t"+"������"+"\t"+"ƽ����");
						wanjiashu=0;
						count1=0;
						count2=0;
						
					int a=in.read();
System.out.println(a);
					for(int b=0;b<a;b++){
						String wanjia=in.readUTF();
System.out.println(wanjia);
						Scanner scan=new Scanner(wanjia);
						String nicheng=scan.next();
						String xingbie=scan.next();
						int dengji=scan.nextInt();
						scan.nextInt();
						String location=scan.next();
System.out.println(location);
						int zhuangtai=scan.nextInt();
						int jifen=scan.nextInt();
						int sheng=scan.nextInt();
						int fu=scan.nextInt();
						int ping=scan.nextInt();
						if(zhuangtai!=0)
							wanjiashu++;
						if(location.equals("LEFT")){
							panel5.setTouxiang(xingbie);
							panel5.label3.setText(nicheng);
							count1=zhuangtai;
							panel5.setZhuangtai(zhuangtai);
						}else if(location.equals("RIGHT")){
							panel6.setTouxiang(xingbie);
							count2=zhuangtai;
							panel6.label3.setText(nicheng);
							panel6.setZhuangtai(zhuangtai);
						}panel7.setText(panel7.getText()+"\n"+nicheng+"     "+"\t"+jifen+"\t"+dengji+"\t"+sheng+"\t"+fu+"\t"+ping+"\n");
					}
					
					}else if(duqu.equals("JIARU")){
						String wanjia=in.readUTF();
						Scanner scan=new Scanner(wanjia);
						String nicheng=scan.next();
						String xingbie=scan.next();
						int dengji=scan.nextInt();
						scan.nextInt();
						String location=scan.next();
System.out.println(location);
						scan.nextInt();
						int jifen=scan.nextInt();
						int sheng=scan.nextInt();
						int fu=scan.nextInt();
						int ping=scan.nextInt();
						if(location.equals("LEFT")){
							panel5.setTouxiang(xingbie);
							panel5.label3.setText(nicheng);
						}else if(location.equals("RIGHT")){
							panel6.setTouxiang(xingbie);
							panel6.label3.setText(nicheng);
						}panel7.setText(panel7.getText()+"\n"+nicheng+"     "+"\t"+jifen+"\t"+dengji+"\t"+sheng+"\t"+fu+"\t"+ping+"\n");
					}else if(duqu.equals("XIAQI")){
System.out.println("�յ���������");
						String luozi=in.readUTF();
System.out.println("����λ��Ϊ"+luozi);
						Scanner scan=new Scanner(luozi);
						int a=scan.nextInt();
						int b=scan.nextInt();
						if(control.panduan(a, b,count))
						{
						control.shuaxin(a,b,count);
						for(int c=0;c<8;c++)
							for(int d=0;d<8;d++){
								if(control.shuzu[c][d]==1)
							button[c][d].setIcon(heizi);
								if(control.shuzu[c][d]==0)
									button[c][d].setIcon(null);
								if(control.shuzu[c][d]==(-1))
									button[c][d].setIcon(baizi);
							}label1.setText("���ӣ�"+control.getbai());
							label2.setText("���ӣ�"+control.gethei());
						count=-count;
						if(control.jiancha(count)){
							count=-count;
							if(control.jiancha(count)){
								if(control.getbai()>control.gethei()){
									timer1.stop();
									timer2.stop();
									count1=0;
									count2=0;
									count=1;
									wanjiashu=0;
									JOptionPane.showMessageDialog(null, "����Ӯ��");
									panel5.setZhuangtai(0);
									panel6.setZhuangtai(0);
								}else if(control.getbai()<control.gethei()){
									timer1.stop();
									timer2.stop();
									count1=0;
									count2=0;
									count=1;
									wanjiashu=0;
										JOptionPane.showMessageDialog(null, "����Ӯ��");
										panel5.setZhuangtai(0);
										panel6.setZhuangtai(0);
								}else if(control.getbai()==control.gethei()){
									timer1.stop();
									timer2.stop();
									count1=0;
									count2=0;
									count=1;
									wanjiashu=0;
									JOptionPane.showMessageDialog(null, "˫������");
									panel5.setZhuangtai(0);
									panel6.setZhuangtai(0);
								}
							}else if(count==1)
								JOptionPane.showMessageDialog(null, "�������ӿ���");
							else if(count==-1)
								JOptionPane.showMessageDialog(null, "�������ӿ���");
							}
						}jiaohuan();
					}else if(duqu.equals("LIAOTIAN")){
						String name=in.readUTF();
						String word=in.readUTF();
System.out.println("Jieshou daoyijuhua");
						String yiqian=area.getText();
						area.setText(yiqian+name+":"+word);
					}else if(duqu.equals("HUIQI")){
						JOptionPane.showMessageDialog(null, "һ���������");
					}else if(duqu.equals("TAOPAO")){
						String ss=in.readUTF();
						timer1.stop();
						timer2.stop();
						count1=0;
						count2=0;
						count=1;
						JOptionPane.showMessageDialog(null, ss+"����");
						wanjiashu=0;
						panel5.setZhuangtai(0);
						panel6.setZhuangtai(0);
					}else if(duqu.equals("RENSHU")){
						timer1.stop();
						timer2.stop();
						count=1;
						count1=0;
						count2=0;
						wanjiashu=0;
						JOptionPane.showMessageDialog(null, "һ������");
						panel5.setZhuangtai(0);
						panel6.setZhuangtai(0);
					}else if(duqu.equals("HEQI")){
System.out.println("���ܵ�����");
					JOptionPane.showMessageDialog(null,"һ�����");
						
					}else if(duqu.equals("ZHUNBEI")){
						String location=in.readUTF();
						int zhuangtai=in.read();
						if(location.equals("LEFT")){
							panel5.setZhuangtai(zhuangtai);
							wanjiashu++;
							count1=zhuangtai;
						}else if(location.equals("RIGHT")){
							panel6.setZhuangtai(zhuangtai);
							wanjiashu++;
							count2=zhuangtai;
						}jiemianshuaxin();
						jiaohuan();
						label1.setText("���ӣ�2");
						label2.setText("���ӣ�2");
						
					}else if(duqu.equals("ZIJITUICHU")){
System.out.println("�Լ��˳�");
						return;
					}else if(duqu.equals("DANGQIANQIPU")){
System.out.println("�õ�����");
						int aaa=in.read();
						if(aaa==255)
							count=-1;
System.out.println("Count��ֵΪ"+count);
						String sss=in.readUTF();
						Scanner scan=new Scanner(sss);

						for(int cc=0;cc<8;cc++)
							for(int dd=0;dd<8;dd++){
								int qizi=scan.nextInt();
System.out.println(qizi);
								control.shuzu[cc][dd]=qizi;
								if(qizi==1)
									button[cc][dd].setIcon(heizi);
								if(qizi==-1)
									button[cc][dd].setIcon(baizi);
								if(qizi==0)
									button[cc][dd].setIcon(null);
							}label1.setText("���ӣ�"+control.getbai());
							label2.setText("���ӣ�"+control.gethei());
						jiaohuan();
					}else if(duqu.equals("HUIQI_TONGYI")){
						String a=in.readUTF();
						String b=in.readUTF();
						if(a.equals("��"))
						{	JOptionPane.showMessageDialog(null, b+"ͬ�����");
							control.huiqi();
							for(int c=0;c<8;c++)
								for(int d=0;d<8;d++){
									if(control.shuzu[c][d]==1)
								button[c][d].setIcon(heizi);
									if(control.shuzu[c][d]==0)
										button[c][d].setIcon(null);
									if(control.shuzu[c][d]==(-1))
										button[c][d].setIcon(baizi);
								}label1.setText("���ӣ�"+control.getbai());
								label2.setText("���ӣ�"+control.gethei());
							count=-count;
							jiaohuan();
						}else if(a.equals("��"))
							JOptionPane.showMessageDialog(null, b+"��ͬ�����");
					}else if(duqu.equals("HEQI_TONGYI")){
						String a=in.readUTF();
						String b=in.readUTF();
						if(a.equals("��")){
							timer1.stop();
							timer2.stop();
							wanjiashu=0;
							count=1;
							count1=0;
							count2=0;
							JOptionPane.showMessageDialog(null,"˫������");
							panel5.setZhuangtai(0);
							panel6.setZhuangtai(0);
							}
						if(a.equals("��"))
							JOptionPane.showMessageDialog(null,b+"��ͬ�����");
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	
}
