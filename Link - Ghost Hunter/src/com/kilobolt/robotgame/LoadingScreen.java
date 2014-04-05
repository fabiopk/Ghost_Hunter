package com.kilobolt.robotgame;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {

		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.up = g.newImage("up.png", ImageFormat.ARGB4444);
		Assets.down = g.newImage("down.png", ImageFormat.ARGB4444);
		Assets.down1 = g.newImage("down1.png", ImageFormat.ARGB4444);
		Assets.down2 = g.newImage("down2.png", ImageFormat.ARGB4444);
		Assets.right = g.newImage("right.png", ImageFormat.ARGB4444);
		Assets.right1 = g.newImage("right1.png", ImageFormat.ARGB4444);
		Assets.right2 = g.newImage("right2.png", ImageFormat.ARGB4444);
		Assets.left = g.newImage("left.png", ImageFormat.ARGB4444);
		Assets.left1 = g.newImage("left1.png", ImageFormat.ARGB4444);
		Assets.left2 = g.newImage("left2.png", ImageFormat.ARGB4444);

		Assets.g_up = g.newImage("ghost_up.png", ImageFormat.ARGB4444);
		Assets.g_down = g.newImage("g_down.png", ImageFormat.ARGB4444);
		Assets.g_down1 = g.newImage("g_down1.png", ImageFormat.ARGB4444);
		Assets.g_right = g.newImage("g_right.png", ImageFormat.ARGB4444);
		Assets.g_right1 = g.newImage("g_right1.png", ImageFormat.ARGB4444);
		Assets.g_left = g.newImage("g_left.png", ImageFormat.ARGB4444);
		Assets.g_left1 = g.newImage("g_left1.png", ImageFormat.ARGB4444);
		
		Assets.g_dead = g.newImage("g_dead.png", ImageFormat.ARGB4444);
		Assets.g_dead1 = g.newImage("g_dead1.png", ImageFormat.ARGB4444);
		Assets.g_dead2 = g.newImage("g_dead2.png", ImageFormat.ARGB4444);
		Assets.g_dead3 = g.newImage("g_dead3.png", ImageFormat.ARGB4444);
		Assets.g_dead4 = g.newImage("g_dead4.png", ImageFormat.ARGB4444);
		Assets.g_dead5 = g.newImage("g_dead5.png", ImageFormat.ARGB4444);
		Assets.g_dead6 = g.newImage("g_dead6.png", ImageFormat.ARGB4444);
		Assets.g_dead7 = g.newImage("g_dead7.png", ImageFormat.ARGB4444);
		Assets.g_dead8 = g.newImage("g_dead8.png", ImageFormat.ARGB4444);
		Assets.g_dead9 = g.newImage("g_dead9.png", ImageFormat.ARGB4444);
		
		Assets.walk_left = g.newImage("walk_left.png", ImageFormat.ARGB4444);
		Assets.walk_right = g.newImage("walk_right.png", ImageFormat.ARGB4444);
		Assets.walk_down = g.newImage("walk_down.png", ImageFormat.ARGB4444);
		Assets.walk_up = g.newImage("walk_up.png", ImageFormat.ARGB4444);
		
		Assets.sword_swing = g.newImage("sword_swing.png", ImageFormat.ARGB4444);
		Assets.item_heart = g.newImage("item_heart.png", ImageFormat.ARGB4444);
		Assets.item_bow = g.newImage("item_bow.png", ImageFormat.ARGB4444);
		
		Assets.grass = g.newImage("grass.png", ImageFormat.RGB565);
		Assets.block = g.newImage("block.png", ImageFormat.RGB565);
		Assets.cake = g.newImage("cake.png", ImageFormat.RGB565);
		Assets.heart = g.newImage("heart.png", ImageFormat.RGB565);
		Assets.gameover = g.newImage("gameover.png", ImageFormat.RGB565);
		Assets.shuriken = g.newImage("shuriken.png", ImageFormat.RGB565);
		// This is how you would load a sound if you had one.
//		 Assets.click = game.getAudio().createSound("explode.ogg");

		game.setScreen(new MainMenuScreen(game));

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
}