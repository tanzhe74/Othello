import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;
public class only extends JFrame implements ActionListener{
	public final int WIDTH=600;
	public final int HEIGHT=620;
	public static int count=1;
	/**
	 * @param args
	 */
	JButton button[][];
	JButton button1;
	arithmetic control;//控制算法初始化
	ImageIcon heizi=new ImageIcon("黑棋.jpg");
	ImageIcon baizi=new ImageIcon("白棋.jpg");
	ImageIcon qipan=new ImageIcon("棋盘.jpg");
	ImageIcon qipan1=new ImageIcon("棋盘1.jpg");
	TextField heizishu;
	TextField baizishu;
	boolean shi=false;//这个变量代表游戏是否结束
	JButton kaishi;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new only();
	}
	public only(){
		super("黑白棋");
		this.setResizable(false);
		setBounds(300,100,WIDTH,HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowEar());
		
		JPanel panelyouxi=new JPanel();//游戏主题
		panelyouxi.setLayout(new BorderLayout());
		JPanel panelHeibai=new JPanel();//游戏部分
		JPanel panelButton=new JPanel();//下边面板
		panelButton.setLayout(new BoxLayout(panelButton,BoxLayout.X_AXIS));
		JPanel panelfield=new JPanel();//右侧面板
		panelfield.setSize(200,600);
		add(panelyouxi,BorderLayout.CENTER);
		panelyouxi.add(panelHeibai,BorderLayout.CENTER);
		panelyouxi.add(panelButton,BorderLayout.SOUTH);
		
		
		add(panelfield,BorderLayout.EAST);
		
		panelHeibai.setBackground(Color.yellow);
		panelHeibai.setLayout(new GridLayout(8,8));
		
		button=new JButton[8][8];
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				button[a][b]=new JButton("");
				//button[a][b].setMargin(new Insets(0,0,0,0));
				button[a][b].setIcon(qipan);
				panelHeibai.add(button[a][b]);
				button[a][b].setActionCommand(""+a+" "+b);
				button[a][b].addActionListener(this);
			}
		button[3][3].setIcon(heizi);
		button[3][4].setIcon(baizi);
		button[4][3].setIcon(baizi);
		button[4][4].setIcon(heizi);
		
		control=new arithmetic();
		
		JLabel hei=new JLabel("黑子数");
		JLabel bai=new JLabel("白子数");
		heizishu=new TextField(2);
		heizishu.setEditable(false);
		baizishu=new TextField(2);
		baizishu.setEditable(false);
		kaishi=new JButton("重新开始");
		kaishi.addActionListener(this);
		panelButton.add(kaishi);
		panelButton.add(hei);
		panelButton.add(heizishu);
		panelButton.add(Box.createRigidArea(new Dimension(10,0)));
		panelButton.add(bai);
		panelButton.add(baizishu);
		panelButton.add(Box.createRigidArea(new Dimension(10,0)));
		
		heizishu.setText(""+2);
		baizishu.setText(""+2);
		
		JLabel label1=new JLabel("当前为");
		button1=new JButton(" ");
		button1.setBackground(Color.BLACK);
		panelButton.add(label1);
		panelButton.add(button1);
		setVisible(true);
	}
	
	private class WindowEar extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			lobby.dianji1=true;
			dispose();
		}
	}
	//button1用来表示当前谁在下棋用的,-1它就是黑色，1就是白色
	public void dingyiDangqian(){
		if(count==-1)
			button1.setBackground(Color.BLACK);
		else if(count==1)
			button1.setBackground(Color.WHITE);
		count=-count;
	}
	//下面这个方法是用来判断胜负条件的
	public void wuyiyi1(){
		dingyiDangqian();
		if(control.jiancha(count)){
			count=-count;
			if(control.jiancha(count)){
				if(control.getbai()>control.gethei()){
						JOptionPane.showMessageDialog(null, "白棋胜");
						shi=true;
						return;
				}else if(control.getbai()<control.gethei()){
						JOptionPane.showMessageDialog(null, "黑棋胜");
						shi=true;
						return;
				}else if(control.gethei()==control.getbai()){
					JOptionPane.showMessageDialog(null, "双方平局");
					shi=true;
					return;
				}
			}else if(count==1){
			JOptionPane.showMessageDialog(null, "白棋无子可下");
			shi=true;
			return;}
			else if(count==1){
			JOptionPane.showMessageDialog(null, "黑棋无子可下");
				control.xiaqi(count);
				for(int c=0;c<8;c++)
					for(int d=0;d<8;d++){
						if(control.shuzu[c][d]==1)
							button[c][d].setIcon(heizi);
						if(control.shuzu[c][d]==0)
							button[c][d].setIcon(qipan);
						if(control.shuzu[c][d]==(-1))
							button[c][d].setIcon(baizi);
					}wuyiyi1();
			}

			}
		}
	
	public void actionPerformed(ActionEvent e){
		String actionCommand=e.getActionCommand();
		if(actionCommand.equals("重新开始")){
			control=new arithmetic();
			count=1;
			for(int a=0;a<8;a++)
				for(int b=0;b<8;b++){
					button[a][b].setIcon(qipan);
				}
			button[3][3].setIcon(heizi);
			button[3][4].setIcon(baizi);
			button[4][3].setIcon(baizi);
			button[4][4].setIcon(heizi);
			heizishu.setText(""+2);
			baizishu.setText(""+2);
		}
		else{Scanner scan=new Scanner(actionCommand);
		int a=scan.nextInt();
		int b=scan.nextInt();
		//System.out.println(""+a+" "+b);
		if(control.panduan(a, b,count))
		{control.shuzu[a][b]=count;
		control.shuaxin(a,b,count);
		for(int c=0;c<8;c++)
			for(int d=0;d<8;d++){
				if(control.shuzu[c][d]==1)
					button[c][d].setIcon(heizi);
				if(control.shuzu[c][d]==0)
					button[c][d].setIcon(qipan);
				if(control.shuzu[c][d]==(-1))
					button[c][d].setIcon(baizi);
			}baizishu.setText(""+control.getbai());
			heizishu.setText(""+control.gethei());
			
			
			
			
			
			wuyiyi1();
			if(shi){
				shi=false;
				for(int x=0;x<8;x++)
					for(int y=0;y<8;y++){
						if(control.panduan(x,y,count))
							button[x][y].setIcon(qipan1);
					}
				return;
			}
			
			
			
			
			
		control.xiaqi(count);
		for(int c=0;c<8;c++)
			for(int d=0;d<8;d++){
				if(control.shuzu[c][d]==1)
					button[c][d].setIcon(heizi);
				if(control.shuzu[c][d]==0)
					button[c][d].setIcon(qipan);
				if(control.shuzu[c][d]==(-1))
					button[c][d].setIcon(baizi);
			}baizishu.setText(""+control.getbai());
			heizishu.setText(""+control.gethei());
			
			
			
			wuyiyi1();
			if(shi){
				shi=false;
				for(int x=0;x<8;x++)
					for(int y=0;y<8;y++){
						if(control.panduan(x,y,count))
							button[x][y].setIcon(qipan1);
					}
				return;
			}
			for(int x=0;x<8;x++)
				for(int y=0;y<8;y++){
					if(control.panduan(x,y,count))
						button[x][y].setIcon(qipan1);
				}
		}	
		}	

	}
	

}