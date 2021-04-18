package me.corperateraider.fallingtrees;

public class DrehMatrix {
	
	double
	xx=1, xy=0, xz=0,
	yx=0, yy=1, yz=0,
	zx=0, zy=0, zz=1;
	
	int[] w = new int[9];

	public DrehMatrix(){}
	
	public DrehMatrix rotX(double alpha){
		DrehMatrix ret = new DrehMatrix();
		double sin=Math.sin(alpha), cos=Math.cos(alpha);
		// X
		ret.xx=xx;
		ret.xy=xy;
		ret.xz=xz;
		// Y
		ret.yx=cos*yx-sin*zx;
		ret.yy=cos*yy-sin*zy;
		ret.yz=cos*yz-sin*zz;
		// Z
		ret.zx=sin*yx+cos*zx;
		ret.zy=sin*yy+cos*zy;
		ret.zz=sin*yz+cos*zz;
		return ret;
	}
	
	public DrehMatrix rotY(double alpha){
		DrehMatrix ret = new DrehMatrix();
		double sin=Math.sin(alpha), cos=Math.cos(alpha);
		// Y
		ret.yx=yx;
		ret.yy=yy;
		ret.yz=yz;
		// X
		ret.xx=cos*xx-sin*zx;
		ret.xy=cos*xy-sin*zy;
		ret.xz=cos*xz-sin*zz;
		// Z
		ret.zx=sin*xx+cos*zx;
		ret.zy=sin*xy+cos*zy;
		ret.zz=sin*xz+cos*zz;
		return ret;
	}
	
	public DrehMatrix rotZ(double alpha){
		DrehMatrix ret = new DrehMatrix();
		double sin=Math.sin(alpha), cos=Math.cos(alpha);
		// Z
		ret.zx=zx;
		ret.zy=zy;
		ret.zz=zz;
		// X
		ret.xx=cos*xx-sin*yx;
		ret.xy=cos*xy-sin*yy;
		ret.xz=cos*xz-sin*yz;
		// Y
		ret.yx=sin*xx+cos*yx;
		ret.yy=sin*xy+cos*yy;
		ret.yz=sin*xz+cos*yz;
		return ret;
	}
	
	public int sq(int i){return i*i;}
	
	public void finalize(){
		w[0]=(int)(xx*512);w[1]=(int)(xy*512);w[2]=(int)(xz*512);
		w[3]=(int)(yx*512);w[4]=(int)(yy*512);w[5]=(int)(yz*512);
		w[6]=(int)(zx*512);w[7]=(int)(zy*512);w[8]=(int)(zz*512);
	}
	
	public int x(int x, int y, int z){
		return (w[0]*x + w[1]*y + w[2]*z)>>9;
	}
	
	public int y(int x, int y, int z){
		return (w[3]*x + w[4]*y + w[5]*z)>>9;
	}
	
	public int z(int x, int y, int z){
		return (w[6]*x + w[7]*y + w[8]*z)>>9;
	}
	
	public int dir(){
		return sq(w[0])+sq(w[1])+sq(w[2])>sq(w[6])+sq(w[7])+sq(w[8])?4:8;
	}
	
	public double x(double x, double y, double z){
		return xx*x+xy*y+xz*z;
	}
	
	public double y(double x, double y, double z){
		return yx*x+yy*y+yz*z;
	}
	
	public double z(double x, double y, double z){
		return zx*x+zy*y+zz*z;
	}
}
