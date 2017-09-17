package com.yofungate.render;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

import com.yofungate.game.GameObject;


import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.*;

public class Camera extends GameObject{
	public Camera(float _fov, float _aspect, float _near, float _far){
		x=0;y=0;z=0;rx=0;ry=0;rz=0;fov=_fov;aspect=_aspect;near=_near;far=_far;
		initProjection();
	}
	private void initProjection(){
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(fov,aspect,near,far);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glClearDepth(1.0f);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_TEXTURE_2D);
	}
	public void useView(){
		glRotatef(rx,1,0,0);
		glRotatef(ry,0,1,0);
		glRotatef(rz,0,0,1);
		glTranslatef(x,y,z);
	}
	
	public float getX(){return x;}
	public float getY(){return y;}
	public float getZ(){return z;}

	public float getYAW(){return rx;}
	public float getPITCH(){return ry;}
	public float getRZ(){return rz;}

	public float getFov(){return fov;}
	public float getAspect(){return aspect;}
	public float getNear(){return near;}
	public float getFar(){return far;}
	
	public void moveX(float _x){ x+=_x;}
	public void moveY(float _y){ y+=_y;}
	public void move(float _z,float dir){ z+=_z*Math.sin(Math.toRadians(ry+90*dir));x+=_z*Math.cos(Math.toRadians(ry+90*dir));}
	
	public void moveYAW(float _rx){ rx+=_rx;if(rx>90){rx=90;}if(rx<-90){rx=-90;}}
	public void movePITCH(float _ry){ ry+=_ry;}
	public void moveRZ(float _rz){ rz+=_rz;}
	
	public void setX(float _x){ x=_x;}
	public void setY(float _y){ y=_y;}
	public void setZ(float _z){ z=_z;}

	public void setRX(float _rx){ rx=_rx;}
	public void setRY(float _ry){ ry=_ry;}
	public void setRZ(float _rz){ rz=_rz;}

	public void setFov(float _fov){ fov=_fov;}
	public void setAspect(float _aspect){ aspect=_aspect;}
	public void setNear(float _near){ near=_near;}
	public void setFar(float _far){ far=_far;}
}
