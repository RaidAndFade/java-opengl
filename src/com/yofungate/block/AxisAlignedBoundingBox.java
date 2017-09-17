 package com.yofungate.block;

public class AxisAlignedBoundingBox {
	public float x1,y1,z1,x2,y2,z2;
	public AxisAlignedBoundingBox(float _x1,float _y1,float _z1,float _x2,float _y2, float _z2){
		x1=_x1;y1=_y1;z1=_z1;x2=_x2;y2=_y2;z2=_z2;
	}
	public void x1(float _x){
		x1=_x;
	}
	public float x1(){
		return x1;
	}
	public void y1(float _y){
		y1=_y;
	}
	public float y1(){
		return y1;
	}
	public void z1(float _z){
		z1=_z;
	}
	public float z1(){
		return z1;
	}
	public void x2(float _x){
		x2=_x;
	}
	public float x2(){
		return x2;
	}
	public void y2(float _y){
		y2=_y;
	}
	public float y2(){
		return y2;
	}
	public void z2(float _z){
		z2=_z;
	}
	public float z2(){
		return z2;
	}
}
