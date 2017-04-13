import java.awt.*;
import javax.swing.*;
public class Panel extends JPanel{
	ImageIcon nan=new ImageIcon("男生.jpg");
	ImageIcon nv=new ImageIcon("女生.jpg");
	ImageIcon heizi=new ImageIcon("b.png");
	ImageIcon baizi=new ImageIcon("w.png");
	JLabel label1;
	JLabel label2;
	JLabel label3;
	JLabel label4;
	public Panel(){
		super();
		setSize(200,220);
		this.setLayout(null);
//setBackground(Color.BLUE);
		label1=new JLabel();
		label2=new JLabel();
		label3=new JLabel();
		label4=new JLabel();
		label1.setBounds(10,10,120,180);
		label2.setBounds(130,60,70,70);
		label3.setBounds(20,200,90,20);
		label4.setBounds(140,135,50,20);
		add(label1);
		add(label2);
		add(label3);
		add(label4);
		setVisible(true);
	}
	public void setZhuangtai(int abc){
		if(abc==0)
			label2.setIcon(null);
		else if(abc==1)
			label2.setIcon(heizi);
		else if(abc==2)
			label2.setIcon(baizi);
			
	}
	public void shuaxinPanel(){
		label1.setIcon(null);
		label3.setText(null);
		label2.setIcon(null);
		}
	public void setTouxiang(String xingbie){
		if(xingbie.equals("nan"))
			label1.setIcon(nan);
		else if(xingbie.equals("nv"))
			label1.setIcon(nv);
	}
	public void setName(String name){
		label3.setText(name);
	}
	public void setZishu(int a){
		label4.setText("当前子数："+a);
	}
}
