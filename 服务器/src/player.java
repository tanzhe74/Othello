//玩家类，保存的是玩家各种的基本信息，基本上是个基础类，没有其他自定义类的对象
public class player {
	
	String nicheng,xingbie,zhanghao,location;
	int dengji,touxiang,room=-1;
	int zhuangtai=0;
	int jifen,sheng,fu,ping;
	public player(String zhanghao,String nicheng,String xingbie,int dengji,int touxiang,int jifen,int sheng,int fu,int ping){
		this.zhanghao=zhanghao;
		this.xingbie=xingbie;
		this.dengji=dengji;
		this.nicheng=nicheng;
		this.touxiang=touxiang;
		this.jifen=jifen;
		this.sheng=sheng;
		this.fu=fu;
		this.ping=ping;
		location="MIDDLE";
	}
	public String getZhanghao(){
		return zhanghao;
	}
	public String getname(){
		return nicheng;
	}
	public String getxingbie(){
		return xingbie;
	}
	public void setRoom(int room){
		this.room=room;
	}
	public void setLocation(String location){
		this.location=location;
	}
	public int getDengji(){
		return dengji;
	}
	public int getTouxiang(){
		return touxiang;
	}
	public int getRoom(){
		return room;
	}
	public String getLocation(){
		return location;
	}
	public void setZhuangtai(int abc){
		zhuangtai=abc;
	}
	public int getJifen(){
		return jifen;
	}
	public int getSheng(){
		return sheng;
	}
	public int getFu(){
		return fu;
	}
	public int getPing(){
		return ping;
	}
	public void setJifen(int room){
		this.jifen=room;
	}
	public void setSheng(int room){
		this.sheng=room;
	}
	public void setFu(int room){
		this.fu=room;
	}
	public void setPing(int room){
		this.ping=room;
	}
	public String toString(){
		return nicheng+" "+xingbie+" "+dengji+" "+room+" "+location+" "+zhuangtai+" "+jifen+" "+sheng+" "+fu+" "+ping+" "+touxiang;
	}
}
