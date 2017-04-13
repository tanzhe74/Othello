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
		super("登录");
		setSize(300,300);
		setResizable(false);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 ImagePanel panel = new ImagePanel(new ImageIcon("dm.jpg").getImage());
		    setContentPane(panel);
			Container mypanel = getContentPane();
		mypanel.setLayout(new GridLayout(4,1));
		JPanel panel1=new JPanel();
		JLabel label1=new JLabel("登录");
		label1.setFont(new Font("华文隶书",Font.PLAIN,40));
		panel1.add(label1);
		panel1.setOpaque(false);
		mypanel.add(panel1);
		JPanel panel2=new JPanel();
		JLabel label2=new JLabel("帐号");
		panel2.add(label2);
		
		panel2.add(txt);
		panel2.setOpaque(false);
	    mypanel.add(panel2);
		JPanel panel3=new JPanel();
		JLabel label4=new JLabel("密码");
		panel3.add(label4);
		
		txt1.setEchoChar('*');
		panel3.add(txt1);
		panel3.setOpaque(false);
	    mypanel.add(panel3);
		JPanel sure=new JPanel();
		JButton su=new JButton("确认");
		su.addActionListener(new BListener());
		//JButton ca=new JButton("取消");
		//ca.addActionListener(new BListener());
		sure.add(su);
		//sure.add(ca);
		sure.setOpaque(false);
	    mypanel.add(sure);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //获取当前屏幕大小
		 this.setLocation((screenSize.width - 1000), (screenSize.height -600));

		
	}
	private class BListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String g = e.getActionCommand();
		
			if (g.equals("确认")) {
				String hao = txt.getText();
				String mi = txt1.getText();
				if (hao.equals("")) {
					JOptionPane.showMessageDialog(null, "账号不能为空");
					System.out.println("账号不能为空");
				} else if (mi.equals("")) {
					JOptionPane.showMessageDialog(null, "密码不能为空");
					System.out.println("密码不能为空");
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
	System.out.println("登陆成功");
							dispose();
							new lobby(in,out,socket);
						} else if (shuru.equals("DENGLUSHIBAI"))
							{JOptionPane.showMessageDialog(null, "用户名或密码输入错误！");
	System.out.println("用户名或密码输入错误");}
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