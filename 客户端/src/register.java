import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.*;

public class register extends JFrame{
	Socket socket;
	JTextField hao;
	JTextField name;
	JPasswordField mi1;
	JPasswordField mi2;
	JButton wancheng;
	JButton button1;
	String xingbie="nan";
	DataOutputStream out;
	DataInputStream in;
	int touxiang=0;
	JButton[] button=new JButton[25];
	TianjiaTouxiang tianjia;
	ButtonGroup group;
	JRadioButton boy,girl;
	boolean dianji=true;
	public register(Socket s){
		super();
		socket=s;
		try {
			in=new DataInputStream(socket.getInputStream());
			out=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowEar());
		setSize(500,500);
		setResizable(false);
		 ImagePanel panel = new ImagePanel(new ImageIcon("dm.jpg").getImage());
		    setContentPane(panel);
			Container mypanel =this. getContentPane();
			mypanel.setLayout(new GridLayout(10,1));
        JPanel panel1=new JPanel();
		JLabel label1=new JLabel("ע��");
		label1.setFont(new Font("��������",Font.PLAIN,40));
		panel1.add(label1);
		panel1.setOpaque(false);
		mypanel.add(panel1);
		JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label2=new JLabel("�ʺ�");
		panel2.add(label2);
		hao=new JTextField(14);
		panel2.add(hao);
		JLabel label3=new JLabel("����6��14λ���ֻ���ĸ");
		panel2.add(label3);
		panel2.setOpaque(false);
	    mypanel.add(panel2);
		JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label4=new JLabel("����");
		panel3.add(label4);
		mi1=new JPasswordField(14);
		mi1.setEchoChar('*');
		panel3.add(mi1);
		JLabel label5=new JLabel("����6��14λ���ֻ���ĸ");
        panel3.add(label5);
        panel3.setOpaque(false);
		mypanel.add(panel3);
		JPanel panel4=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label6=new JLabel("ȷ������");
		panel4.add(label6);
		mi2=new JPasswordField(14);
		mi2.setEchoChar('*');
		panel4.add(mi2);
		JLabel label7=new JLabel("�ٴ��������� ");
		panel4.add(label7);
		panel4.setOpaque(false);
		mypanel.add(panel4);
		JPanel panel5=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel label8=new JLabel("�ǳ�");
		panel5.add(label8);
		name=new JTextField(14);
		panel5.add(name);
		panel5.setOpaque(false);
		mypanel.add(panel5);
		JPanel buttonpanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel sex=new JLabel("�Ա�");
		buttonpanel.add(sex);
		ButtonGroup group=new ButtonGroup();
		boy=new JRadioButton("��",false);
		group.add(boy);
		buttonpanel.add(boy);
		girl=new JRadioButton("Ů",true);
		group.add(girl);
		buttonpanel.add(girl);
		buttonpanel.setOpaque(false);
	    mypanel.add(buttonpanel);
	    JPanel panel6=new JPanel(new FlowLayout(FlowLayout.LEFT));
	    JLabel tou=new JLabel("ѡ��ͷ��");
		button1=new JButton("");
		button1.addActionListener(new windowlistener());
		button1.setSize(40,40);
		button1.setActionCommand("ͷ��");
		button1.setIcon(face.touxiang[0]);
		panel6.add(tou);
		panel6.add(button1);
		mypanel.add(panel6);
		panel6.setOpaque(false);
		JPanel sure=new JPanel();
		JButton su=new JButton("���");
		su.addActionListener(new windowlistener());
		//JButton ca=new JButton("ȡ��");
		//ca.addActionListener(new windowlistener());
		sure.add(su);
		//sure.add(ca);
		sure.setOpaque(false);
		mypanel.add(sure);
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //��ȡ��ǰ��Ļ��С
		 this.setLocation((screenSize.width - 1000), (screenSize.height -600));
		}
	/*private class Listener extends JFrame implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getActionCommand().equals("ȷ��")){
			sure s=new sure();
			s.setVisible(true);
			}
			if(e.getActionCommand().equals("ȡ��"))
				System.exit(0);
				
		}
	}*/
	private class windowlistener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String g=e.getActionCommand();
			
			//if(g.equals("ȡ��"))
				//System.exit(0);
			if(g.equals("��"))
				xingbie="nan";
			else if(g.equals("Ů"))
				xingbie="nv";
			else if(g.equals("ͷ��")&&dianji){
				new TianjiaTouxiang();
				dianji=false;
			}else if(g.equals("���")){
				String zhanghao2=hao.getText();
				String nicheng2=name.getText();
				String mima1=mi1.getText();
				String mima2=mi2.getText();
				if(zhanghao2.equals("")){
					JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��");
	System.out.println("�˺Ų���Ϊ��");}
				else if(nicheng2.equals("")){
					JOptionPane.showMessageDialog(null, "�ǳƲ���Ϊ��");
	System.out.println("�ǳƲ���Ϊ��");}
				else if(xingbie.equals("")){
					JOptionPane.showMessageDialog(null, "�Ա���Ϊ��");
	System.out.println("�Ա���Ϊ��");}
				else if(mima1.equals("")){
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
	System.out.println("���벻��Ϊ��");}
				else if(!mima1.equals(mima2)){
					JOptionPane.showMessageDialog(null, "�������ô���");
	System.out.println("�������ô���");}
				else{
					try{
					out.write(101);
					out.writeUTF(zhanghao2);
					out.writeUTF(nicheng2);
					out.writeUTF(xingbie);
					out.writeUTF(mima1);
					out.write(touxiang);
	System.out.println("ע����Ϣ�ѷ���");
					String str=in.readUTF();
	System.out.println(str);
					if(str.equals("ע��ɹ�")){
						JOptionPane.showMessageDialog(null, "ע��ɹ�");
						out.write(102);
						in.close();
						out.close();
						dispose();
						}
					else if(str.equals("ZHUCESHIBAI"))
						JOptionPane.showMessageDialog(null, "���û����Ѵ���");
						return;
					} catch (IOException h) {
						h.printStackTrace();
					}
				}
			}
		}
	}
	private class WindowEar extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			try {
				out.write(102);
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			dispose();
		}
	}
	private class TouxiangXuanze implements ActionListener{
		public void actionPerformed(ActionEvent e){
		String ss=e.getActionCommand();
		Scanner scan=new Scanner(ss);
		int a=scan.nextInt();
		touxiang=a;
		button1.setIcon(face.touxiang[a]);
		dianji=true;
		tianjia.dispose();
		}
	}
	public class TianjiaTouxiang extends JFrame{
		public TianjiaTouxiang(){
			super();
			setLayout(new GridLayout(5,5));
			setResizable(false);
			//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			setBounds(300,300,200,210);
			tianjia=this;
			for(int a=0;a<25;a++){
				button[a]=new JButton(""+a);
				button[a].setIcon(face.touxiang[a]);
				button[a].addActionListener(new TouxiangXuanze());
				add(button[a]);
			}
			setVisible(true);
		}
	}
}