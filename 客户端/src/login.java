import java.net.*;
import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
public class login extends JFrame{
     static reversi f=new reversi();
	final int DENGLU = 100, ZHUCE = 101, CLOSED = 102;
	JTextField txt=new JTextField(14);
	JPasswordField txt1=new JPasswordField(14);
	JFrame frame;
	public login(){
		super("��¼");
		setSize(300,300);
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ImagePanel panel = new ImagePanel(new ImageIcon("dm.jpg").getImage());
		    setContentPane(panel);
			Container mypanel = getContentPane();
		mypanel.setLayout(new GridLayout(4,1));
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("��¼");
		label1.setFont(new Font("��������",Font.PLAIN,40));
		panel1.add(label1);
		panel1.setOpaque(false);
		mypanel.add(panel1);
		JPanel panel2=new JPanel();
		JLabel label2=new JLabel("�ʺ�");
		panel2.add(label2);
		
		panel2.add(txt);
		panel2.setOpaque(false);
	    mypanel.add(panel2);
		JPanel panel3=new JPanel();
		JLabel label4=new JLabel("����");
		panel3.add(label4);
		
		txt1.setEchoChar('*');
		panel3.add(txt1);
		panel3.setOpaque(false);
	    mypanel.add(panel3);
		JPanel sure=new JPanel();
		JButton su=new JButton("ȷ��");
		su.addActionListener(new BListener());
		//JButton ca=new JButton("ȡ��");
		//ca.addActionListener(new BListener());
		sure.add(su);
		//sure.add(ca);
		sure.setOpaque(false);
	    mypanel.add(sure);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //��ȡ��ǰ��Ļ��С
		 this.setLocation((screenSize.width - 1000), (screenSize.height -600));

		
	}
	private class BListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String g = e.getActionCommand();
		
			if (g.equals("ȷ��")) {
				String hao = txt.getText();
				String mi = txt1.getText();
				if (hao.equals("")) {
					JOptionPane.showMessageDialog(null, "�˺Ų���Ϊ��");
					System.out.println("�˺Ų���Ϊ��");
				} else if (mi.equals("")) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					System.out.println("���벻��Ϊ��");
				} else {
					try {

						Socket socket = new Socket("127.0.0.1", 8888);
						DataInputStream in = new DataInputStream(socket
								.getInputStream());
						DataOutputStream out = new DataOutputStream(socket
								.getOutputStream());
						out.write(DENGLU);
						out.writeUTF(txt.getText());
						out.writeUTF(txt1.getText());
						String shuru = in.readUTF();
						if (shuru.equals("DENGLUCHENGGONG")) {
	System.out.println("��½�ɹ�");
							dispose();
							new lobby(in,out,socket);
						} else if (shuru.equals("DENGLUSHIBAI"))
							{JOptionPane.showMessageDialog(null, "�û����������������");
	System.out.println("�û����������������");}
					} catch (UnknownHostException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}	
		}
		}
	
}