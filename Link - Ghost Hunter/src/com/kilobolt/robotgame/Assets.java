package com.kilobolt.robotgame;

import com.kilobolt.framework.Image;
import com.kilobolt.framework.Music;
import com.kilobolt.framework.Sound;

public class Assets {

	public static Image down, down1, down2, up, left, left1, left2, right,
			right1, right2, g_up, g_down, g_down1, g_left, g_left1, g_right,
			g_right1, grass, block, ghost_dead, g_dead, g_dead1, g_dead2,
			g_dead3, g_dead4, g_dead5, g_dead6, g_dead7, g_dead8, g_dead9,
			walk_left, walk_right, walk_down, walk_up, sword_swing, heart,
			gameover, item_heart, item_bow, shuriken;
	// public static Music theme;
	public static Image splash;
	public static Image menu;
	public static Image cake;
	public static Music theme;

	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		theme = sampleGame.getAudio().createMusic("zelda.mp3");
		theme.setLooping(true);
		theme.setVolume(0.50f);
		theme.play();
	}

}