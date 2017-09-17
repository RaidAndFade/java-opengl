package com.yofungate.render;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.opengl.Texture;

import com.yofungate.main.Main;

public class DrawMisc {
	public static void drawRECT(float x,float y,float l,float w,float d){
		glVertex3f(x,y,d);
		glVertex3f(x,y+w,d);
		glVertex3f(x+l,y+w,d);
		glVertex3f(x+l,y,d);
	}
	public static void drawBOX(float x, float y, float z, float l, float w, float d){
		//FRONT
		glColor3f(1F,0F,0F);
		glVertex3f(x,y,z);
		glVertex3f(x,y+w,z);
		glVertex3f(x+l,y+w,z);
		glVertex3f(x+l,y,z);
		//BACK
		glColor3f(1F,0F,1F);
		glVertex3f(x,y,z+d);
		glVertex3f(x,y+w,z+d);
		glVertex3f(x+l,y+w,z+d);
		glVertex3f(x+l,y,z+d);
		//LEFTSIDE
		glColor3f(1F,1F,0F);
		glVertex3f(x,y,z);
		glVertex3f(x,y+w,z);
		glVertex3f(x,y+w,z+d);
		glVertex3f(x,y,z+d);
		//RIGHTSIDE
		glColor3f(0F,1F,0F);
		glVertex3f(x,y,z);
		glVertex3f(x+l,y,z);
		glVertex3f(x+l,y,z+d);
		glVertex3f(x,y,z+d);
		//BOTTOMSIDE
		glColor3f(0F,0F,1F);
		glVertex3f(x+l,y,z);
		glVertex3f(x+l,y+w,z);
		glVertex3f(x+l,y+w,z+d);
		glVertex3f(x+l,y,z+d);
		//TOPSIDe
		glColor3f(0F,1F,1F);
		glVertex3f(x,y+w,z);
		glVertex3f(x+l,y+w,z);
		glVertex3f(x+l,y+w,z+d);
		glVertex3f(x,y+w,z+d);
	}
	//TODO Front RAY = broken
	//TODO Back Ray = broken
	//TODO Right Ray = broken
	public static void drawTexturedBOX(float x, float y, float z, float l, float w, float d,boolean outline, Texture... textures) {
		boolean singleTexture=false;
		if(textures.length==1){singleTexture = true;}else if(textures.length!=6){throw(new NullPointerException("Not Enough Textures"));}
		glPushMatrix();
		textures[0].bind();
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		glBegin(GL_QUADS);
		glColor3f(1F,1F,1F);
			if(Main.debug)glColor3f(1F,0F,0F);//red
			//FRONT
			
			glTexCoord2f(0,0);			glVertex3f(x,y,z);
			glTexCoord2f(0,1);			glVertex3f(x,y+w,z);
			glTexCoord2f(1,1);			glVertex3f(x+l,y+w,z);
			glTexCoord2f(1,0);			glVertex3f(x+l,y,z);
			
		if(!singleTexture){glEnd();textures[1].bind();glBegin(GL_QUADS);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);}
		if(Main.debug)glColor3f(1F,1F,0F);//yellow
			//BACK
			glTexCoord2f(0,0);			glVertex3f(x,y,z+d);
			glTexCoord2f(0,1);			glVertex3f(x,y+w,z+d);
			glTexCoord2f(1,1);			glVertex3f(x+l,y+w,z+d);
			glTexCoord2f(1,0);			glVertex3f(x+l,y,z+d);
			
		if(!singleTexture){glEnd();textures[2].bind();glBegin(GL_QUADS);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);}
		if(Main.debug)glColor3f(0F,1F,0F);//green
			//LEFTSIDE
			glTexCoord2f(0,0);			glVertex3f(x,y,z);
			glTexCoord2f(0,1);			glVertex3f(x,y+w,z);
			glTexCoord2f(1,1);			glVertex3f(x,y+w,z+d);
			glTexCoord2f(1,0);			glVertex3f(x,y,z+d);
			
		if(!singleTexture){glEnd();textures[3].bind();glBegin(GL_QUADS);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);}
		if(Main.debug)glColor3f(0F,1F,1F);//pink
			//RIGHTSIDE
			glTexCoord2f(0,0);			glVertex3f(x,y,z);
			glTexCoord2f(0,1);			glVertex3f(x+l,y,z);
			glTexCoord2f(1,1);			glVertex3f(x+l,y,z+d);
			glTexCoord2f(1,0);			glVertex3f(x,y,z+d);
			
		if(!singleTexture){glEnd();textures[4].bind();glBegin(GL_QUADS);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);}
		if(Main.debug)glColor3f(1F,0F,1F);//lightblue
			//BOTTOMSIDE
			glTexCoord2f(0,0);			glVertex3f(x+l,y,z);
			glTexCoord2f(0,1);			glVertex3f(x+l,y+w,z);
			glTexCoord2f(1,1);			glVertex3f(x+l,y+w,z+d);
			glTexCoord2f(1,0);			glVertex3f(x+l,y,z+d);
			
		if(!singleTexture){glEnd();textures[5].bind();glBegin(GL_QUADS);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);}
		if(Main.debug)glColor3f(0F,0F,1F);//blue
			//TOPSIDe
			glTexCoord2f(0,0);			glVertex3f(x,y+w,z);
			glTexCoord2f(0,1);			glVertex3f(x+l,y+w,z);
			glTexCoord2f(1,1);			glVertex3f(x+l,y+w,z+d);
			glTexCoord2f(1,0);			glVertex3f(x,y+w,z+d);
		
		if(outline){
			glBindTexture(GL_TEXTURE_2D, 0);
			float depth = 0.0001f;float borderWidth = 0.1f;
			glColor3f(0F,0F,0F);
			//FRONT
			z+=-depth;
			glVertex3f(x,y,z);
			glVertex3f(x,y+w,z);
			glVertex3f(x+borderWidth,y+w,z);
			glVertex3f(x+borderWidth,y,z);
			
			glVertex3f(x+l,y,z);
			glVertex3f(x+l,y+w,z);
			glVertex3f(x+l-borderWidth,y+w,z);
			glVertex3f(x+l-borderWidth,y,z);
			
			glVertex3f(x,y,z);
			glVertex3f(x+l,y,z);
			glVertex3f(x+l,y+borderWidth,z);
			glVertex3f(x,y+borderWidth,z);
			
			glVertex3f(x,y+w,z);
			glVertex3f(x+l,y+w,z);
			glVertex3f(x+l,y+w-borderWidth,z);
			glVertex3f(x,y+w-borderWidth,z);
			
			//BACK
			z+=depth*2;
			glVertex3f(x,y,z+d);
			glVertex3f(x,y+w,z+d);
			glVertex3f(x+borderWidth,y+w,z+d);
			glVertex3f(x+borderWidth,y,z+d);
			
			glVertex3f(x+l,y,z+d);
			glVertex3f(x+l,y+w,z+d);
			glVertex3f(x+l-borderWidth,y+w,z+d);
			glVertex3f(x+l-borderWidth,y,z+d);
			
			glVertex3f(x,y,z+d);
			glVertex3f(x+l,y,z+d);
			glVertex3f(x+l,y+borderWidth,z+d);
			glVertex3f(x,y+borderWidth,z+d);
			
			glVertex3f(x,y+w,z+d);
			glVertex3f(x+l,y+w,z+d);
			glVertex3f(x+l,y+w-borderWidth,z+d);
			glVertex3f(x,y+w-borderWidth,z+d);
			//Top
			z+=-depth;
			y+=depth;
			
			glVertex3f(x,y+w,z);
			glVertex3f(x+borderWidth,y+w,z);
			glVertex3f(x+borderWidth,y+w,z+d);
			glVertex3f(x,y+w,z+d);
			
			glVertex3f(x+l,y+w,z);
			glVertex3f(x+l-borderWidth,y+w,z);
			glVertex3f(x+l-borderWidth,y+w,z+d);
			glVertex3f(x+l,y+w,z+d);
			
			glVertex3f(x,y+w,z+d);
			glVertex3f(x,y+w,z+d-borderWidth);
			glVertex3f(x+l,y+w,z+d-borderWidth);
			glVertex3f(x+l,y+w,z+d);
			
			glVertex3f(x,y+w,z);
			glVertex3f(x,y+w,z+borderWidth);
			glVertex3f(x+l,y+w,z+borderWidth);
			glVertex3f(x+l,y+w,z);
			y+=-depth*2;
			//Bottom
			glVertex3f(x,y,z);
			glVertex3f(x+borderWidth,y,z);
			glVertex3f(x+borderWidth,y,z+d);
			glVertex3f(x,y,z+d);
			
			glVertex3f(x+l,y,z);
			glVertex3f(x+l-borderWidth,y,z);
			glVertex3f(x+l-borderWidth,y,z+d);
			glVertex3f(x+l,y,z+d);
			
			glVertex3f(x,y,z+d);
			glVertex3f(x,y,z+d-borderWidth);
			glVertex3f(x+l,y,z+d-borderWidth);
			glVertex3f(x+l,y,z+d);
			
			glVertex3f(x,y,z);
			glVertex3f(x,y,z+borderWidth);
			glVertex3f(x+l,y,z+borderWidth);
			glVertex3f(x+l,y,z);
			
			//Left
			x+=depth;
			glVertex3f(x+l,y,z);
			glVertex3f(x+l,y+borderWidth,z);
			glVertex3f(x+l,y+borderWidth,z+d);
			glVertex3f(x+l,y,z+d);
			
			glVertex3f(x+l,y+w,z);
			glVertex3f(x+l,y+w-borderWidth,z);
			glVertex3f(x+l,y+w-borderWidth,z+d);
			glVertex3f(x+l,y+w,z+d);
			
			glVertex3f(x+l,y,z+d);
			glVertex3f(x+l,y,z+d-borderWidth);
			glVertex3f(x+l,y+w,z+d-borderWidth);
			glVertex3f(x+l,y+w,z+d);
			
			glVertex3f(x+l,y,z);
			glVertex3f(x+l,y,z+borderWidth);
			glVertex3f(x+l,y+w,z+borderWidth);
			glVertex3f(x+l,y+w,z);
			
			//Right
			x+=-depth*2;
			glVertex3f(x,y,z);
			glVertex3f(x,y+borderWidth,z);
			glVertex3f(x,y+borderWidth,z+d);
			glVertex3f(x,y,z+d);
			
			glVertex3f(x,y+w,z);
			glVertex3f(x,y+w-borderWidth,z);
			glVertex3f(x,y+w-borderWidth,z+d);
			glVertex3f(x,y+w,z+d);
			
			glVertex3f(x,y,z+d);
			glVertex3f(x,y,z+d-borderWidth);
			glVertex3f(x,y+w,z+d-borderWidth);
			glVertex3f(x,y+w,z+d);
			
			glVertex3f(x,y,z);
			glVertex3f(x,y,z+borderWidth);
			glVertex3f(x,y+w,z+borderWidth);
			glVertex3f(x,y+w,z);
		}
			
		glEnd();
		glPopMatrix();
	}
}
