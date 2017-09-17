package com.yofungate.block;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class Block {

	public static Block WOOD,BRICK;
	
	public static AxisAlignedBoundingBox AABB;

	public static Block blockList[] = new Block[4096];
	
	private String name, image;
	private Texture texture;

	public boolean needsRedraw=true;
	
	private static int id=0;

	public static void init(){
		WOOD = new Block(0,"Wood","wood.png");
		BRICK = new Block(1,"Bricks","brick.png");
	}
	
	public Block(int _id,String _name,String _image) {
		this.id = _id;
		this.name = _name;
		this.image = _image;
		this.texture = registerTexture();
		Block.blockList[_id] = this;
		System.out.println(Block.blockList[_id].getName()+"("+this.id+","+this.image+") Added");
	}

	public Texture registerTexture(){
		try {
			return TextureLoader.getTexture(this.getTextureExt(),new FileInputStream(new File("res/blocks/"+this.getTextureName()+this.getTextureExt())));
		} catch (FileNotFoundException e) {
			System.err.println("Texture 'res/blocks/"+this.getTextureName()+this.getTextureExt()+" Not Found");
		} catch (Exception e) {}
		return null;
	}
	public String getName() {
		return this.name;
	}
	public String getTextureName(){
		return this.image.replace(this.image.split("\\.")[this.image.split("\\.").length-1],"");
	}
	public String getTextureExt(){
		return this.image.split("\\.")[this.image.split("\\.").length-1];
	}
	public Texture getTexture(){
		return texture;
	}
	public AxisAlignedBoundingBox getAABB(){
		return AABB;
	}
	public void setAABB(float _x1,float _y1,float _z1,float _x2,float _y2, float _z2){
		this.AABB = new AxisAlignedBoundingBox(_x1,_y1,_z1,_x2,_y2,_z2);
	}

}
