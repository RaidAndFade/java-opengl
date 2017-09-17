package com.yofungate.world;

import javax.vecmath.Point3d;

import com.yofungate.block.Block;
import com.yofungate.chunk.Chunk;

public class World {
	private Chunk[][] chunks = new Chunk[16][16];
	public static Point3d specialBlock;
	public static int gennedX=0,gennedZ=0;
	public void genChunk(){
		if(gennedX==16){gennedX=0;gennedZ++;}
		chunks[gennedX][gennedZ] = new Chunk(gennedX,gennedZ,this);		
		chunks[gennedX++][gennedZ].generate();
	}
	public void renderWorld(){
		for(int _x=0;_x<16;_x++){
			for(int _z=0;_z<16;_z++){
				if(_x<=gennedX&&_z<=gennedZ&&chunks[_x][_z]!=null){
					chunks[_x][_z].renderChunk();
				}
			}
		}
	}
	public void setBlock(int _x, int _y, int _z, Block _b){
		try{
		if(chunks[(int)_x/16][(int)_z/16] == null)chunks[(int)_x/16][(int)_z/16] = new Chunk((int)_x/16,(int)_z/16,this);	
		chunks[(int)_x/16][(int)_z/16].setBlock(_b, _x-(int)(_x/16), _y, _z-(int)(_z/16));
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void breakBlock(int _x, int _y, int _z){
		try{
     		chunks[(int)_x/16][(int)_z/16].setBlock(null, _x-(int)(_x/16), _y, _z-(int)(_z/16));
		}catch(Exception e){e.printStackTrace();}
	}
	
	public boolean isBlockSpecial(int _x,int _y, int _z) {
		//System.out.println("((X:"+_x+",Y:"+_y+",Z:"+_z+"),(X:"+specialBlock.x+",Y:"+specialBlock.y+",Z:"+specialBlock.z+")");
		if(specialBlock == null){return false;}
		if(specialBlock.x==_x&&specialBlock.y==_y&&specialBlock.z==_z)
			return true;
		return false;
	}
	public World() {
		specialBlock = new Point3d();
		genChunk();
	}
}
