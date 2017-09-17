package com.yofungate.utils;

import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;

public class VARIABLES {
	public static Cursor defaultcursor;
	
	public static void init(){
		defaultcursor = Mouse.getNativeCursor();
	}
}
