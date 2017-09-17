package com.yofungate.chunk;

import com.yofungate.block.Block;
import com.yofungate.render.BlockRenderer;
import com.yofungate.world.World;

public class Chunk {
	private Block[][][] blocks = new Block[16][16][256];
	public int x,z;
	private World owner;
	public Chunk(int _x,int _z, World _owner){
		this.x=_x;
		this.z=_z;
		this.owner=_owner;
	}
	
	public void setBlock(Block _b,int _x, int _y, int _z){
		blocks[_x][_z][_y] = _b;
	}
	public void renderChunk(){
		for(int _x=0;_x<16;_x++){
			for(int _z=0;_z<16;_z++){
				for(int _y=0;_y<256;_y++){
					if(blocks[_x][_z][_y]!=null){
						BlockRenderer.RenderBlockAt(blocks[_x][_z][_y],_x+x*16,_y,_z+z*16,owner.isBlockSpecial(_x+x*16,_y,_z+z*16));
						//System.out.print("(X:"+(_x+x*16)+",Y:"+_y+",Z:"+(_z+z*16)+",o:"+World.isBlockSpecial(_x+x*16,_y,_z+z*16)+")");
					}
				}
			}
		}
	}
	public void generate() {
		for(int _x=0;_x<16;_x++){
			for(int _z=0;_z<16;_z++){
				setBlock(Block.WOOD,_x,0,_z);
			}
		}
	}
}
