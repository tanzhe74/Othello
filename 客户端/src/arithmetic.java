//�����count������ʾ������ɫ��1��-1������Ӱ���
public class arithmetic {
	public int shuzu[][]=new int [8][8];//�������̵����飬Ӧ��ûʲô���⣬8*8��
	int shuzu2[][]=new int [8][8];
	int shuzu3[][]=new int[8][8];
	public arithmetic(){
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				shuzu[a][b]=0;
				//�����������ʼ�� �����м��Ŀ������������ǿյ�
			shuzu[3][3]=1;
			shuzu[3][4]=-1;
			shuzu[4][3]=-1;
			shuzu[4][4]=1;
			}
		}
	//���涼��������㷨����ɨ��˸�����
	private void left(int a,int b,int count){
		if(a>0&&shuzu[a-1][b]==-count)
			left(a-1,b,count);
		if(a>0&&shuzu[a-1][b]==count)
			shuzu[a][b]=count;
		}
	private void right(int a,int b,int count){
		if(a<7&&shuzu[a+1][b]==-count)
			right(a+1,b,count);
		if(a<7&&shuzu[a+1][b]==count)
			shuzu[a][b]=count;
		}
	private void above(int a,int b,int count){
		if(b>0&&shuzu[a][b-1]==-count)
			above(a,b-1,count);
		if(b>0&&shuzu[a][b-1]==count)
			shuzu[a][b]=count;
	}
	private void below(int a,int b,int count){
		if(b<7&&shuzu[a][b+1]==-count)
			below(a,b+1,count);
		if(b<7&&shuzu[a][b+1]==count)
			shuzu[a][b]=count;
	}
	private void left_above(int a,int b,int count){
		if(a>0&&b>0&&shuzu[a-1][b-1]==-count)
			left_above(a-1,b-1,count);
		if(a>0&&b>0&&shuzu[a-1][b-1]==count)
			shuzu[a][b]=count;
	}
	private void right_below(int a,int b,int count){
		if(a<7&&b<7&&shuzu[a+1][b+1]==-count)
			right_below(a+1,b+1,count);
		if(a<7&&b<7&&shuzu[a+1][b+1]==count)
			shuzu[a][b]=count;
	}
	private void left_below(int a,int b,int count){
		if(a>0&&b<7&&shuzu[a-1][b+1]==-count)
			left_below(a-1,b+1,count);
		if(a>0&&b<7&&shuzu[a-1][b+1]==count)
			shuzu[a][b]=count;
	}
	private void right_above(int a,int b,int count){
		if(a<7&&b>0&&shuzu[a+1][b-1]==-count)
			right_above(a+1,b-1,count);
		if(a<7&&b>0&&shuzu[a+1][b-1]==count)
			shuzu[a][b]=count;
	}
	public void shuaxin(int a,int b,int title){
		int ha[]={a,a,a,a,a,a,a,a};
		int hb[]={b,b,b,b,b,b,b,b};
		fuzhi2();
		shuzu[a][b]=title;
		this.above(ha[0], hb[0], title);
		this.below(ha[1], hb[1], title);
		this.left(ha[2], hb[2], title);
		this.right(ha[3], hb[3], title);
		this.left_above(ha[4], hb[4], title);
		this.right_below(ha[5], hb[5], title);
		this.left_below(ha[6], hb[6], title);
		this.right_above(ha[7], hb[7], title);
	}
	//�õ�������Ŀ���㷨
	public int gethei(){
		int heizi=0;
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				if(shuzu[a][b]==1)
					heizi++;
			}
		return heizi;
	}

	//�õ�������Ŀ���㷨
	public int getbai(){
		int baizi=0;
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				if(shuzu[a][b]==-1)
					baizi++;
			}
		return baizi;
	}
	//�������̵��㷨��Ϊ�˻������Ա���ǰ����������
	private void fuzhi(){
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				shuzu2[a][b]=shuzu[a][b];
			}
	}
	private void fuzhi2(){
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				shuzu3[a][b]=shuzu[a][b];
			}
	}
	//һ�λ������壬�ڵ���һ���ֵ��Լ��µ�ʱ��ע���ֵ�ĳ�����µ�ʱ�������ܻ���
	public void huiqi(){
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				shuzu[a][b]=shuzu3[a][b];
			}
	}
	//�ж��ܷ�����
	public boolean panduan(int a,int b,int yanse){
		if(shuzu[a][b]!=0)
			return false;//Ҫ����ĵط��Ѿ���������
		else{
			arithmetic shuzu1=new arithmetic();
			fuzhi();
			shuzu1.shuzu=this.shuzu2;
			shuzu1.shuaxin(a, b, yanse);
			if(this.getbai()==shuzu1.getbai()||this.gethei()==shuzu1.gethei())
				return false;//����ĵط�û������ ��ˢ�����̺� ����ǰһ�� ˵������ʧ��
			else
				return true;//��������ͳɹ���
		}
	}
	public boolean jiancha(int vlue){
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				if(panduan(a,b,vlue))
					return false;
			}return true;
	}
	public void xiaqi(int count){
		int vule=0,c=0,d=0;
		if(panduan(0,0,count)){
			shuaxin(0,0,count);return;
		}
		if(panduan(0,7,count)){
			shuaxin(0,7,count);return;
		}
		if(panduan(7,0,count)){
			shuaxin(7,0,count);return;
		}
		if(panduan(7,7,count)){
			shuaxin(7,7,count);return;
		}
		for(int a=0;a<8;a++)
			for(int b=0;b<8;b++){
				if(panduan(a,b,count)){
					arithmetic shuzu1=new arithmetic();
					fuzhi();
					shuzu1.shuzu=this.shuzu2;
					shuzu1.shuaxin(a,b,count);
					if(vule<Math.abs(this.getbai()-shuzu1.getbai())){
						vule=Math.abs(this.getbai()-shuzu1.getbai());
						c=a;
						d=b;
					}
				}
			}
		if(!(c==0&&d==0)){
		shuaxin(c,d,count);}
	}
	
}