package com.yofungate.render;

import static org.lwjgl.opengl.GL11.*;

import com.yofungate.block.Block;

public class BlockRenderer {
	public static void RenderBlockAt(Block b, int _x, int _y, int _z, boolean outlined) {
		DrawMisc.drawTexturedBOX(_x*8,_y*8,_z*8,8,8,8,outlined,b.getTexture());
	}
}
