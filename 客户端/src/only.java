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
	arithmetic control;//�����㷨��ʼ��
	ImageIcon heizi=new ImageIcon("����.jpg");
	ImageIcon baizi=new ImageIcon("����.jpg");
	ImageIcon qipan=new ImageIcon("����.jpg");
	ImageIcon qipan1=new ImageIcon("����1.jpg");
	TextField heizishu;
	TextField baizishu;
	boolean shi=false;//�������������Ϸ�Ƿ����
	JButton kaishi;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new only();
	}
	public only(){
		super("�ڰ���");
		this.setResizable(false);
		setBounds(300,100,WIDTH,HEIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowEar());
		
		JPanel panelyouxi=new JPanel();//��Ϸ����
		panelyouxi.setLayout(new BorderLayout());
		JPanel panelHeibai=new JPanel();//��Ϸ����
		JPanel panelButton=new JPanel();//�±����
		panelButton.setLayout(new BoxLayout(panelButton,BoxLayout.X_AXIS));
		JPanel panelfield=new JPanel();//�Ҳ����
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
		
		JLabel hei=new JLabel("������");
		JLabel bai=new JLabel("������");
		heizishu=new TextField(2);
		heizishu.setEditable(false);
		baizishu=new TextField(2);
		baizishu.setEditable(false);
		kaishi=new JButton("���¿�ʼ");
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
		
		JLabel label1=new JLabel("��ǰΪ");
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
	//button1������ʾ��ǰ˭�������õ�,-1�����Ǻ�ɫ��1���ǰ�ɫ
	public void dingyiDangqian(){
		if(count==-1)
			button1.setBackground(Color.BLACK);
		else if(count==1)
			button1.setBackground(Color.WHITE);
		count=-count;
	}
	//������������������ж�ʤ��������
	public void wuyiyi1(){
		dingyiDangqian();
		if(control.jiancha(count)){
			count=-count;
			if(control.jiancha(count)){
				if(control.getbai()>control.gethei()){
						JOptionPane.showMessageDialog(null, "����ʤ");
						shi=true;
						return;
				}else if(control.getbai()<control.gethei()){
						JOptionPane.showMessageDialog(null, "����ʤ");
						shi=true;
						return;
				}else if(control.gethei()==control.getbai()){
					JOptionPane.showMessageDialog(null, "˫��ƽ��");
					shi=true;
					return;
				}
			}else if(count==1){
			JOptionPane.showMessageDialog(null, "�������ӿ���");
			shi=true;
			return;}
			else if(count==1){
			JOptionPane.showMessageDialog(null, "�������ӿ���");
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
		if(actionCommand.equals("���¿�ʼ")){
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