import java.net.*;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.net.*;
import java.io.*;
public class reversi extends JFrame {
	static reversi myframe=new reversi();
	public static void main(String[] args) {
		// TODO Auto-generated method stub

myframe.setVisible(true);
}
	JFrame frame;
	public reversi() {

	super("��¼");
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(700,700);
    ImagePanel panel = new ImagePanel(new ImageIcon("qp.jpg").getImage());
    setContentPane(panel);
	Container mypanel = getContentPane();
	mypanel.setLayout(new BorderLayout());
	JPanel mypanel1=new JPanel();
	mypanel1.setSize(600,100);
	JLabel label1=new JLabel("�ڰ���");
	label1.setFont(new Font("��������",Font.PLAIN,80)); 
	mypanel1.add(label1);
	mypanel1.setOpaque(false);
	mypanel.add(mypanel1,BorderLayout.NORTH);
	JPanel mypanel2=new JPanel();
	mypanel2.setSize(800,100);
	JButton log=new JButton("��¼");
	log.addActionListener(new ButtonListener());
	JButton reg=new JButton("ע��");
	reg.addActionListener(new ButtonListener());
	JButton exit=new JButton("�˳�");
	exit.addActionListener(new ButtonListener());
	JButton explain=new JButton("��Ϸ˵��");
	explain.addActionListener(new ButtonListener());
	JButton danji=new JButton("������Ϸ");
	danji.addActionListener(new ButtonListener());
	log.setBackground(Color.white);
	reg.setBackground(Color.white);
	exit.setBackground(Color.white);
	explain.setBackground(Color.white);
	mypanel2.add(log);
	mypanel2.add(reg);
	mypanel2.add(danji);
	mypanel2.add(exit);
	mypanel2.add(explain);
	mypanel2.setOpaque(false);
	mypanel.add(mypanel2,BorderLayout.SOUTH);
	//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //��ȡ��ǰ��Ļ��С
	 //this.setLocation((screenSize.width - 1200), (screenSize.height -800));
	}
	private class ButtonListener implements ActionListener{
		private Object login;

		public void actionPerformed(ActionEvent e){
			
			String g = e.getActionCommand();
			if(g.equals("������Ϸ")){
				only a=new only();
				a.setVisible(true);
			}
		
			if(g.equals("�˳�"))
				System.exit(0);
            if (g.equals("��¼")){
                login log=new login();
			    log.setVisible(true);
			
			    }
			if (g.equals("ע��")) {
				try {
					Socket socket = new Socket("127.0.0.1", 8888);
					DataOutputStream out = new DataOutputStream(socket
							.getOutputStream());
					register zhu = new register(socket);
					zhu.setVisible(true);
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
			if(e.getActionCommand().equals("��Ϸ˵��")){
				JOptionPane.showMessageDialog(null,"�ڰ����������һ����8*8��������̡�" +
						"+\n����ʱ�������ڿո��м䣬��������Χ��һ�����ڽ�����ϡ�" +
						"\n��ʼʱ���������������������ĸ����ӽ�����ã��������������ӡ�" +
						"\n ���Լ���ɫ�����ӷ������̵Ŀո��ϣ������Լ����µ������ںᡢ����б�˸���������һ���Լ������ӣ��򱻼����м��ȫ����ת���Ϊ�Լ������ӡ�" +
						"\n���ң�ֻ���ڿ��Է�ת���ӵĵط��ſ������ӡ�\n��� \n" +
						"1����ֿ�ʼʱ����Ͱ��嶼λ���������롣\n"+
						"2���ڷ����У�˫���������塣\n " +
						"3��һ���Ϸ����岽��������һ���ո�������һ�����ӣ����ҷ�ת����һ���������ӡ�\n " +
						"4�������µ����������������е�ͬɫ���Ӽ䣬�Է�����ס���������Ӷ�Ҫ��ת�����������Ǻ�\n" +
						"�żУ����żУ�����б�żС���ס��λ���ϱ���ȫ���Ƕ��ֵ����ӣ������пո�\n" +
						"5��һ������������������Ϸ��壬�κα���ס�����Ӷ����뱻��ת������������Ȩѡ��ȥ��ĳ�����ӡ�\n" +
						"6���������ٷ�ת�˶��ֵ�һ�����ӣ�����Ͳ������ӡ����һ��û��\n" +
						"�Ϸ��岽��Ҳ����˵�������µ�������������ٷ�ת���ֵ�һ�����ӣ�������һ��ֻ����Ȩ���������Ķ��ּ�������ֱ�����кϷ��岽���¡�\n" +
						"7�����һ��������һ���Ϸ��岽���£����ͱ������ӣ�������Ȩ��\n" +
						"8����ֳ�����ȥ��ֱ��������������˫�����޺Ϸ��岽���¡�");
			}
			
		}

		
		
	}
}
