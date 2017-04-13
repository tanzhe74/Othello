import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class lobby extends JFrame{
	JFrame frame;
	Socket socket;
	DataOutputStream out;
	DataInputStream in;
	lobby ting=this;
	Desk [] desk = new Desk[12];
	JScrollPane scroll;
	TextArea panel1;
	JLabel label1;
	JPanel panel2;
	ImageIcon icon=new ImageIcon("beijing.jpg");
	ImageIcon icon2=new ImageIcon("小桌子.jpg");
	playerroom fang;
	visitorroom fang2;
	JButton renji,beijing;
	
	JButton buttona;
	JButton buttonb;
	JButton buttonc;
	JButton buttond;
	JButton buttone;
	JButton buttonf;
	ImageIcon icona=new ImageIcon("a.jpg");
	ImageIcon iconb=new ImageIcon("b.jpg");
	ImageIcon iconc=new ImageIcon("c.jpg");
	ImageIcon icond=new ImageIcon("d.jpg");
	ImageIcon icone=new ImageIcon("e.jpg");
	ImageIcon iconf=new ImageIcon("f.jpg");
	GenghuanBeijing that;
	static boolean dianji1=true;
	boolean dianji2=true;
	
	public lobby(DataInputStream in,DataOutputStream out,Socket s){
		super();
		setBounds(300,100,1000,600);
		setLayout(null);
		setResizable(false);
		label1=new JLabel(icon);
		label1.setLayout(new GridLayout(3,4));
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowEar());
		panel1=new TextArea();
		panel1.setEditable(false);
		panel2=new JPanel();
		scroll=new JScrollPane(panel1);
		panel1.setBounds(720,50,270,500);
		
		socket=s;
		this.in=in;
		this.out=out;
		
		for(int a=0;a<12;a++){
			desk[a]=new Desk(a);
			label1.add(desk[a]);
		}
		add(label1);
		add(panel1);
		label1.setBounds(0,0,720,600);
		panel2.setBounds(720,0,280,50);
		renji=new JButton("棋局练习");
		renji.addActionListener(new ButtonListener());
		beijing=new JButton("更换背景");
		beijing.addActionListener(new ButtonListener());
		panel2.add(renji);
		panel2.add(beijing);
		add(panel2);
		setVisible(true);
		new Jieshou().start();
	}

	private class ButtonEar implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Button button=(Button) e.getSource(); 
System.out.println(button.getPanduan());
System.out.println(button.getWeizhi());
			if((!button.getWeizhi().equals("MIDDLE"))&&button.getPanduan()==null){
			int d=button.getRoom();
System.out.println(d);
			String s=button.getWeizhi();
			try {
				fang=new playerroom(socket,in,out,s);
				out.writeUTF("ENTER");
				out.write(d);
				out.writeUTF(s);
				} catch (IOException e1) {
				e1.printStackTrace();
			}
			}else if(button.getWeizhi().equals("MIDDLE")){
				int d=button.getRoom();
				String s=button.getWeizhi();
				try {fang2=new visitorroom(socket,in,out);
					out.writeUTF("ENTER");
					out.write(d);
					out.writeUTF(s);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String xinxi=e.getActionCommand();
			if(xinxi.equals("棋局练习")&&dianji1){
				new only();
				dianji1=false;
			}else if(xinxi.equals("更换背景")&&dianji2){
				new GenghuanBeijing();
				dianji2=false;
			}
		}
	}
	
	private class WindowEar extends WindowAdapter{
		public void windowClosing(WindowEvent e) {
			try {
				out.writeUTF("EXIT");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			//System.exit(0);
			dispose();
		}

	}
	
	public class GenghuanBeijing extends JFrame{
		public GenghuanBeijing(){
			super();
			that=this;
			setLayout(new GridLayout(1,5));
			setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setBounds(100,50,600,130);
			buttona=new JButton();
			buttona.setIcon(icona);
			buttona.setActionCommand("a");
			buttona.addActionListener(new BeijingXuanze());
			buttonb=new JButton();
			buttonb.setIcon(iconb);
			buttonb.setActionCommand("b");
			buttonb.addActionListener(new BeijingXuanze());
			buttonc=new JButton();
			buttonc.setIcon(iconc);
			buttonc.setActionCommand("c");
			buttonc.addActionListener(new BeijingXuanze());
			buttond=new JButton();
			buttond.setIcon(icond);
			buttond.setActionCommand("d");
			buttond.addActionListener(new BeijingXuanze());
			buttone=new JButton();
			buttone.setIcon(icone);
			buttone.setActionCommand("e");
			buttone.addActionListener(new BeijingXuanze());
			buttonf=new JButton();
			buttonf.setIcon(iconf);
			buttonf.setActionCommand("f");
			buttonf.addActionListener(new BeijingXuanze());
			add(buttona);
			add(buttonb);
			add(buttonc);
			add(buttond);
			add(buttone);
			add(buttonf);
			setVisible(true);
		}
	}
	
	
	private class BeijingXuanze implements ActionListener{
		public void actionPerformed(ActionEvent e){
		String ss=e.getActionCommand();
		if(ss.equals("a")){
			label1.setIcon(icona);
			that.dispose();
			dianji2=true;
		}else if(ss.equals("b")){
			label1.setIcon(iconb);
			that.dispose();
			dianji2=true;
		}else if(ss.equals("c")){
			label1.setIcon(iconc);
			that.dispose();
			dianji2=true;
		}else if(ss.equals("d")){
			label1.setIcon(icond);
			that.dispose();
			dianji2=true;
		}else if(ss.equals("e")){
			label1.setIcon(icone);
			that.dispose();
			dianji2=true;
		}else if(ss.equals("f")){
			label1.setIcon(iconf);
			that.dispose();
			dianji2=true;
		}
		}
	}
	
	

	private class Jieshou extends Thread{
		public void run(){
			while(true){
			try {
				String mingling=in.readUTF();
System.out.println(mingling);
				if(mingling.equals("ADD")){
					String wanjia=in.readUTF();
System.out.println(wanjia);
					Scanner scan=new Scanner(wanjia);
					String nicheng=scan.next();
System.out.println(nicheng);
					scan.next();
					int dengji=scan.nextInt();
					scan.nextInt();
					scan.next();
					scan.nextInt();
					int jifen=scan.nextInt();
					int sheng=scan.nextInt();
					int fu=scan.nextInt();
					int ping =scan.nextInt();
					//list.add(new ButtonBar(nicheng,dengji));
					panel1.setText(panel1.getText()+"\n"+nicheng+"\t"+jifen+"\t"+dengji+"\t"+sheng+"\t"+fu+"\t"+ping+"\n");
					//change();
				}else if(mingling.equals("SHUAXIN")){
					for(int a=0;a<12;a++){
						desk[a].shuaxin();
					}
					panel1.setText("在线玩家"+"\t"+"积分"+"\t"+"等级"+"\t"+"胜局数"+"\t"+"负局数"+"\t"+"平局数");
					int b=in.read();
//System.out.println(b);
					for(int i=0;i<b;i++){
						String wanjia=in.readUTF();
System.out.println(wanjia);
						Scanner scan=new Scanner(wanjia);
						String nicheng=scan.next();
						scan.next();
						int dengji=scan.nextInt();
						int room=scan.nextInt();
						String location=scan.next();
						scan.nextInt();
						int jifen=scan.nextInt();
						int sheng=scan.nextInt();
						int fu=scan.nextInt();
						int ping =scan.nextInt();
						int touxiang=scan.nextInt();
System.out.println(location);
							//list.add(new ButtonBar(nicheng,dengji));
			panel1.setText(panel1.getText()+"\n"+nicheng+"     "+"\t"+jifen+"\t"+dengji+"\t"+sheng+"\t"+fu+"\t"+ping+"\n");
						if(room==-1||location.equals("MIDDLE")){
							
						}
						else{
							if(location.equals("LEFT")){
								desk[room].left.setIcon(face.touxiang[touxiang]);
								desk[room].left.setPanduan("HAS");
								desk[room].zuo.setText(nicheng);
							}else if(location.equals("RIGHT")){
								desk[room].right.setIcon(face.touxiang[touxiang]);
								desk[room].right.setPanduan("HAS");
								desk[room].you.setText(nicheng);
							}
						}
						//change();
						//panel1=new JList(thatlist);
						repaint();
					}
				}else if(mingling.equals("ZIJIENTER")){
					String wei=in.readUTF();
					if(!wei.equals("MIDDLE")){
						ting.setVisible(false);
						fang.go();
						ting.setVisible(true);
System.out.println("已从房间中退出");}else if(wei.equals("MIDDLE")){
		ting.setVisible(false);
		fang2.go();
		ting.setVisible(true);
}
				}
				else if(mingling.equals("ENTER")){
					String wanjia=in.readUTF();
System.out.println("一个玩家进入房间");
System.out.println(wanjia);
					Scanner scan=new Scanner(wanjia);
					String nicheng=scan.next();
					scan.next();
					scan.nextInt();
					int room=scan.nextInt();
System.out.println("进入的房间为"+room);
					String location=scan.next();
					scan.nextInt();
					scan.nextInt();
					scan.nextInt();
					scan.nextInt();
					scan.nextInt();
					int touxiang=scan.nextInt();
System.out.println(location);
					
					if(location.equals("LEFT")){
						desk[room].left.setIcon(face.touxiang[touxiang]);
						desk[room].left.setPanduan("HAS");
						desk[room].zuo.setText(nicheng);
					}else if(location.equals("RIGHT")){
						desk[room].right.setIcon(face.touxiang[touxiang]);
						desk[room].right.setPanduan("HAS");
						desk[room].you.setText(nicheng);
						}

				}
			} catch (IOException e) {
				return;
			}
			}
		}
	}
	private class Desk extends JLabel{
		Button left;
		Button right;
		Button center;
		JLabel zuo;
		JLabel you;
		JLabel xia;
		public Desk(int vule){
		super();
		setLayout(null);

		setSize(170,200);
		left=new Button(vule,"LEFT");
//this.left.setIcon(icon);
		right=new Button(vule,"RIGHT");
		left.addActionListener(new ButtonEar());
		right.addActionListener(new ButtonEar());
		center=new Button(vule,"MIDDLE");
		center.setIcon(icon2);
		center.addActionListener(new ButtonEar());
		zuo=new JLabel();
		you=new JLabel();
		xia=new JLabel();
		
		left.setBounds(10,50,40,40);
		right.setBounds(130,50,40,40);
		center.setBounds(60,40,60,60);
		zuo.setBounds(10,100,40,30);
		you.setBounds(130,100,40,30);
		xia.setBounds(80,110,110,30);
		xia.setText("--"+(vule+1)+"--");
		
		add(left);
		add(right);
		add(center);
		add(zuo);
		add(you);
		add(xia);
		setVisible(true);
		}
		private void shuaxin(){
			left.shuaxin();
			right.shuaxin();
			zuo.setText(null);
			you.setText(null);
		}
	}
}