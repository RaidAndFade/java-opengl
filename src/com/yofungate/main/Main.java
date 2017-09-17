package com.yofungate.main;

import java.awt.Font;
import java.awt.event.KeyListener;

import org.lwjgl.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.TrueTypeFont;

import com.yofungate.block.Block;
import com.yofungate.game.Game;
import com.yofungate.render.Camera;
import com.yofungate.utils.CONSTANTS;
import com.yofungate.utils.VARIABLES;

import static org.lwjgl.opengl.GL11.*;

public class Main {
	public static final boolean debug = false;
	private static Camera cam;
	private static float rotation=0,x=0,y=0;
	private static long lastFrame;
	private static Game g;
	private static KeyListener listener;
	public static TrueTypeFont font;
	static Font awtFont = new Font("Times New Roman", Font.PLAIN, 24);
	
	public static void main(String... args) throws Exception{
		init();
		gameLoop();
	}

	private static void init() throws Exception {
		initGL();
		initGame();
	}

	private static void initGame() {
		g = new Game(cam);
	}

	private static void initGL() throws Exception {
		LibLoader.loadNatives();
		Display.setDisplayMode(new DisplayMode(800,600));
		Display.setInitialBackground(1F, 1F, 1F);
		Display.create();
		font = new TrueTypeFont(awtFont, false);
		cam = new Camera(70F,(float)Display.getWidth()/(float)Display.getHeight(),1F,1000F);
	}

	private static void gameLoop() {
		float x=0;

		while(!Display.isCloseRequested()){
			input();
			update();
			render();
			Display.sync(60);
		}
		Display.destroy();
		Keyboard.destroy();
		System.exit(0);
	}

	private static void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glLoadIdentity();
		cam.useView();
		g.render();
		Display.update();
	}

	private static void update() {
		g.update();
	}
	private static void input() {
		g.input();
	}
}
