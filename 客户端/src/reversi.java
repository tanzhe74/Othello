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

	super("登录");
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(700,700);
    ImagePanel panel = new ImagePanel(new ImageIcon("qp.jpg").getImage());
    setContentPane(panel);
	Container mypanel = getContentPane();
	mypanel.setLayout(new BorderLayout());
	JPanel mypanel1=new JPanel();
	mypanel1.setSize(600,100);
	JLabel label1=new JLabel("黑白棋");
	label1.setFont(new Font("华文隶书",Font.PLAIN,80)); 
	mypanel1.add(label1);
	mypanel1.setOpaque(false);
	mypanel.add(mypanel1,BorderLayout.NORTH);
	JPanel mypanel2=new JPanel();
	mypanel2.setSize(800,100);
	JButton log=new JButton("登录");
	log.addActionListener(new ButtonListener());
	JButton reg=new JButton("注册");
	reg.addActionListener(new ButtonListener());
	JButton exit=new JButton("退出");
	exit.addActionListener(new ButtonListener());
	JButton explain=new JButton("游戏说明");
	explain.addActionListener(new ButtonListener());
	JButton danji=new JButton("单机游戏");
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
	//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();   //获取当前屏幕大小
	 //this.setLocation((screenSize.width - 1200), (screenSize.height -800));
	}
	private class ButtonListener implements ActionListener{
		private Object login;

		public void actionPerformed(ActionEvent e){
			
			String g = e.getActionCommand();
			if(g.equals("单机游戏")){
				only a=new only();
				a.setVisible(true);
			}
		
			if(g.equals("退出"))
				System.exit(0);
            if (g.equals("登录")){
                login log=new login();
			    log.setVisible(true);
			
			    }
			if (g.equals("注册")) {
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
			if(e.getActionCommand().equals("游戏说明")){
				JOptionPane.showMessageDialog(null,"黑白棋的棋盘是一个有8*8方格的棋盘。" +
						"+\n下棋时将棋下在空格中间，而不是像围棋一样下在交叉点上。" +
						"\n开始时在棋盘正中有两白两黑四个棋子交叉放置，黑棋总是先下子。" +
						"\n 把自己颜色的棋子放在棋盘的空格上，而当自己放下的棋子在横、竖、斜八个方向内有一个自己的棋子，则被夹在中间的全部翻转会成为自己的棋子。" +
						"\n并且，只有在可以翻转棋子的地方才可以下子。\n棋规 \n" +
						"1．棋局开始时黑棋和白棋都位于棋盘中央。\n"+
						"2．黑方先行，双方交替下棋。\n " +
						"3．一步合法的棋步包括：在一个空格新落下一个棋子，并且翻转对手一个或多个棋子。\n " +
						"4．新落下的棋子与棋盘上已有的同色棋子间，对方被夹住的所有棋子都要翻转过来。可以是横\n" +
						"着夹，竖着夹，或是斜着夹。夹住的位置上必须全部是对手的棋子，不能有空格。\n" +
						"5．一步棋可以在数个方向上翻棋，任何被夹住的棋子都必须被翻转过来，棋手无权选择不去翻某个棋子。\n" +
						"6．除非至少翻转了对手的一个棋子，否则就不能落子。如果一方没有\n" +
						"合法棋步，也就是说不管他下到哪里，都不能至少翻转对手的一个棋子，那他这一轮只能弃权，而由他的对手继续落子直到他有合法棋步可下。\n" +
						"7．如果一方至少有一步合法棋步可下，他就必须落子，不得弃权。\n" +
						"8．棋局持续下去，直到棋盘填满或者双方都无合法棋步可下。");
			}
			
		}

		
		
	}
}
