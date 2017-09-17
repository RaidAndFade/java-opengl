package com.yofungate.game;

import java.awt.Paint;
import java.awt.Point;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;


import org.lwjgl.*;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import com.yofungate.block.Block;
import com.yofungate.chunk.Chunk;
import com.yofungate.game.Game;
import com.yofungate.render.BlockRenderer;
import com.yofungate.render.Camera;
import com.yofungate.render.DrawMisc;
import com.yofungate.utils.CONSTANTS;
import com.yofungate.utils.VARIABLES;
import com.yofungate.world.World;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.Project.*;

public class Game {
	private boolean Clipped=true;
	Camera cam;
	float x=0;
	private World world;
	private int lastmouseY;
	private int lastmouseX;
	private boolean[] keyDown;
	public Game(Camera _cam) {
		this.cam = _cam;
		keyDown = new boolean[1000];
		init();
	}
	
	
	private void init() {
		VARIABLES.init();
		Block.init();
		world=new World();
	}

	public boolean ldown=false,rdown=false,mdown=false;
	public void input(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){cam.move(CONSTANTS.move_speed,1);}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){cam.move(-CONSTANTS.move_speed,1);}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){cam.move(CONSTANTS.move_speed,0);}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){cam.move(-CONSTANTS.move_speed,0);}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){cam.moveY(CONSTANTS.Y_move_speed);}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){cam.moveY(-CONSTANTS.Y_move_speed);}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){if(!keyDown[Keyboard.KEY_ESCAPE]){if(Clipped){Clipped=false;}else{Clipped=true;}}keyDown[Keyboard.KEY_ESCAPE]=true;}else{keyDown[Keyboard.KEY_ESCAPE]=false;}
		//left
		if(Mouse.isButtonDown(0)&&!ldown){
			ldown=true;
			world.breakBlock((int)World.specialBlock.x,(int)World.specialBlock.y,(int)World.specialBlock.z);
		}
		if(!(Mouse.isButtonDown(0)&&ldown)){
			ldown=false;
		}
		//right
		if(Mouse.isButtonDown(1)&&!rdown){
			rdown=true;
			world.setBlock((int)World.specialBlock.x,(int)World.specialBlock.y,(int)World.specialBlock.z,Block.BRICK);
		}
		if(!(Mouse.isButtonDown(1)&&rdown)){
			rdown=false;
		}
		
		//middle
		if(Mouse.isButtonDown(2)){
			
		}
		if(Clipped){
			if(lastmouseX == Display.getWidth()/2){
				cam.movePITCH(-CONSTANTS.rotate_speed*(lastmouseX-Mouse.getX()));
			}
			if(lastmouseY == Display.getHeight()/2){
				cam.moveYAW(CONSTANTS.rotate_speed*(lastmouseY-Mouse.getY()));
			}
			if(Mouse.getY()!=Display.getHeight()/2||Mouse.getX()!=Display.getWidth()/2){
				Mouse.setCursorPosition(Display.getWidth()/2,Display.getHeight()/2);
			}
			lastmouseX = Mouse.getX();
			lastmouseY = Mouse.getY();
			FloatBuffer mousePos = getOGLPos(Mouse.getX(), Mouse.getY());
			selectObject(mousePos.get(0), mousePos.get(1), mousePos.get(2));
			try{
				if(Mouse.getNativeCursor()==VARIABLES.defaultcursor)Mouse.setNativeCursor(new Cursor(10,10, 0, 0, 1, BufferUtils.createIntBuffer(1), null));
			}catch(Exception e){}
		}else{
			try {
				if(Mouse.getNativeCursor()!=VARIABLES.defaultcursor)Mouse.setNativeCursor(VARIABLES.defaultcursor);
			}catch(Exception e){}
		}
	}
	private FloatBuffer getOGLPos(int mouseX, int mouseY){
        IntBuffer viewport = BufferUtils.createIntBuffer(16);
        FloatBuffer modelview = BufferUtils.createFloatBuffer(16);
        FloatBuffer projection = BufferUtils.createFloatBuffer(16);
        FloatBuffer winZ = BufferUtils.createFloatBuffer(1);
        float winX, winY;
        FloatBuffer position = BufferUtils.createFloatBuffer(3);
        glGetFloat(GL_MODELVIEW_MATRIX, modelview);
        glGetFloat(GL_PROJECTION_MATRIX, projection);
        glGetInteger(GL_VIEWPORT, viewport);
        winX = (float)mouseX;
        winY = (float)viewport.get(3) - (float)mouseY;
        glReadPixels(mouseX, (int)winY, 1, 1, GL_DEPTH_COMPONENT, GL_FLOAT, winZ);
        gluUnProject(winX, winY, winZ.get(), modelview, projection, viewport, position);
        return position;
    }
	public void selectObject(float mouseX, float mouseY, float mouseZ){
		mouseX-=0.00001f;mouseY-=0.00001f;mouseZ-=0.00001f;
		mouseX/=8;mouseY/=8;mouseZ/=8;
		if(mouseX-(float) Math.floor(mouseX)>1f){mouseX-=0.01f;}
		if(mouseY-(float) Math.floor(mouseY)>1f){mouseY-=0.01f;}
		if(mouseZ-(float) Math.floor(mouseZ)>1f){mouseZ-=0.01f;}
        System.out.print("X:"+mouseX+"-Y:"+mouseY+"-Z:"+mouseZ+"|");
		mouseX=(float) Math.floor(mouseX);mouseY=(float) Math.floor(mouseY);mouseZ=(float) Math.floor(mouseZ);
		World.specialBlock.x=mouseX;World.specialBlock.y=mouseY;World.specialBlock.z=mouseZ;
        System.out.println("X:"+mouseX+"-Y:"+mouseY+"-Z:"+mouseZ);
    }
	public void update(){
		
	}
	public void render(){
		glTranslatef(0,0,-10);
		//glRotatef(x,1,1,0);
		glBegin(GL_LINES);
			//X
			glColor3f(1F,0F,0F);
			glVertex3f(0,0,0);
			glVertex3f(50,0,0);
			//Y
			glColor3f(0F,1F,0F);
			glVertex3f(0,0,0);
			glVertex3f(0,50,0);
			//Z
			glColor3f(0F,0F,1F);
			glVertex3f(0,0,0);
			glVertex3f(0,0,50);
		glEnd();
		x++;
		world.renderWorld();
		//System.out.println();
		//
//			for(float x=-5;x<5;x++){
//				for(float y=-5;y<5;y++){
//					
//				}
//			}
			
	}
}
