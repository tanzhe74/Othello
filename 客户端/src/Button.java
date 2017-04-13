import javax.swing.*;
public class Button extends JButton{
	String panduan=null;
	String weizhi;
	int room;
	ImageIcon icon=new ImageIcon("a.jpg");
	public Button(int a,String weizhi){
		super();
		room=a;
		setIcon(icon);
		this.weizhi=weizhi;
	}
	public int getRoom(){
		return room;
	}
	public void setPanduan(String s){
		panduan=s;
	}
	public String getPanduan(){
		return panduan;
	}
	public String getWeizhi(){
		return weizhi;
	}
	public void shuaxin(){
		setIcon(icon);
		setPanduan(null);
	}
}