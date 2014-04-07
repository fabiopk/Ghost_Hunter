package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import android.graphics.Color;
import android.graphics.Paint;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;
import com.kilobolt.framework.Input.TouchEvent;
import com.kilobolt.framework.Screen;
import com.kilobolt.robotgame.Character.State;

public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

	GameState state = GameState.Running;

	// Variable Setup

	public static Image down, down1, down2, up, left, left1, left2, right,
			right1, right2, g_up, g_down, g_down1, g_left, g_left1, g_right,
			g_right1, grass, cake, g_dead, g_dead1, g_dead2, g_dead3, g_dead4,
			g_dead5, g_dead6, g_dead7, g_dead8, g_dead9, walk_left, walk_right,
			walk_down, walk_up, sword_swing, heart, item_heart, item_bow,
			shuriken;

	Paint paint, paint2;
	private static int rows = 16;
	private static int columns = 9;
	private static int[][] tilemap;
	private static Character link;
	private Animation a_down, a_left, a_right, ag_right, ag_left, ag_down;
	private static ArrayList<Ghost> ghosts;
	private static ArrayList<Arrow> arrows;
	static Animation ag_dead;
	private SpriteSheet w_left, w_right, w_down, w_up;

	private int timer;

	private ArrayList<Item> items;

	private static SpriteSheet sword;

	public static ArrayList<Ghost> getGhosts() {
		return ghosts;
	}

	public void setGhosts(ArrayList<Ghost> ghosts) {
		this.ghosts = ghosts;
	}

	public GameScreen(Game game) {
		super(game);

		// Initialize game objects here

		createTilemap();
		link = new Character();

		ghosts = new ArrayList<Ghost>();
		addGhost();
		addGhost();
		addGhost();
		addGhost();

		items = new ArrayList<Item>();
		arrows = new ArrayList<Arrow>();
		dropItem(5, 5);

		up = Assets.up;
		down = Assets.down;
		down1 = Assets.down1;
		down2 = Assets.down2;
		left = Assets.left;
		left1 = Assets.left1;
		left2 = Assets.left2;
		right = Assets.right;
		right1 = Assets.right1;
		right2 = Assets.right2;
		g_left = Assets.g_left;
		g_left1 = Assets.g_left1;
		g_right = Assets.g_right;
		g_right1 = Assets.g_right1;
		g_up = Assets.g_up;
		g_down = Assets.g_down;
		g_down1 = Assets.g_down1;
		g_dead = Assets.g_dead;
		g_dead1 = Assets.g_dead1;
		g_dead2 = Assets.g_dead2;
		g_dead3 = Assets.g_dead3;
		g_dead4 = Assets.g_dead4;
		g_dead5 = Assets.g_dead5;
		g_dead6 = Assets.g_dead6;
		g_dead7 = Assets.g_dead7;
		g_dead8 = Assets.g_dead8;
		g_dead9 = Assets.g_dead9;
		walk_left = Assets.walk_left;
		walk_right = Assets.walk_right;
		walk_down = Assets.walk_down;
		walk_up = Assets.walk_up;
		sword_swing = Assets.sword_swing;
		heart = Assets.heart;
		item_heart = Assets.item_heart;
		item_bow = Assets.item_bow;
		shuriken = Assets.shuriken;

		// Defining a paint object
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);

		// Add animations
		a_down = new Animation();
		a_down.addFrame(down, 1250);
		a_down.addFrame(down1, 50);
		a_down.addFrame(down2, 50);
		a_down.addFrame(down1, 50);

		a_left = new Animation();
		a_left.addFrame(left, 1250);
		a_left.addFrame(left1, 50);
		a_left.addFrame(left2, 50);
		a_left.addFrame(left1, 50);

		a_right = new Animation();
		a_right.addFrame(right, 1250);
		a_right.addFrame(right1, 50);
		a_right.addFrame(right2, 50);
		a_right.addFrame(right1, 50);

		ag_right = new Animation();
		ag_right.addFrame(g_right, 150);
		ag_right.addFrame(g_right1, 150);

		ag_left = new Animation();
		ag_left.addFrame(g_left, 150);
		ag_left.addFrame(g_left1, 150);

		ag_down = new Animation();
		ag_down.addFrame(g_down, 150);
		ag_down.addFrame(g_down1, 150);

		ag_dead = new Animation();
		ag_dead.addFrame(g_dead, 60);
		ag_dead.addFrame(g_dead1, 60);
		ag_dead.addFrame(g_dead2, 60);
		ag_dead.addFrame(g_dead3, 60);
		ag_dead.addFrame(g_dead4, 60);
		ag_dead.addFrame(g_dead5, 60);
		ag_dead.addFrame(g_dead6, 60);
		ag_dead.addFrame(g_dead7, 60);
		ag_dead.addFrame(g_dead9, 60);
		ag_dead.addFrame(g_dead8, 700);
		ag_dead.addFrame(g_dead8, 50);

		w_left = new SpriteSheet(walk_left, 8, 40);
		w_right = new SpriteSheet(walk_right, 8, 40);
		w_down = new SpriteSheet(walk_down, 9, 40);
		w_up = new SpriteSheet(walk_up, 9, 40);
		sword = new SpriteSheet(sword_swing, 6, 30);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

	}

	public static int[][] getTilemap() {
		return tilemap;
	}

	public void setTilemap(int[][] tilemap) {
		this.tilemap = tilemap;
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.

		// if (state == GameState.Ready)
		// updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		// if (state == GameState.Paused)
		// updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!

		if (touchEvents.size() > 0)
			state = GameState.Running;
	}

	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

		if (!link.isAlive) {
			state = GameState.GameOver;
		}

		for (Ghost gst : ghosts) { // Clean map before ghost move, so it is zero
									// again
			tilemap[gst.getXpos()][gst.getYpos()] = 0;
		}
		for (Ghost gst : ghosts) {
			gst.update();
			gst.randonMovement();
		}
		for (Ghost gst : ghosts) {
			tilemap[gst.getXpos()][gst.getYpos()] = 3; // New position is now =
														// 3 (not acessible to
														// walk)
		}

		// This is identical to the update() method from our Unit 2/3 game.
		tilemap[link.getXpos()][link.getYpos()] = 0;
		animate();
		link.update();

		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {

				if (inBounds(event, 0, 540, 480, 540)) {
					link.moveLeft();
				} else if (inBounds(event, 1440, 540, 480, 540)) {
					link.moveRight();
				} else if (inBounds(event, 480, 0, 960, 540)) {
					link.moveUp();
				} else if (inBounds(event, 480, 540, 960, 540)) {
					link.moveDown();
				} else if (inBounds(event, 0, 0, 480, 540)) {
					link.atack();
				} else if (inBounds(event, 1440, 0, 480, 540)) {
					arrows.add(link.shoot());
				}

				if (timer >= 100) {
					addGhost();
					timer = 0;
				} else {
					timer++;
				}
			}
			tilemap[link.getXpos()][link.getYpos()] = 2;
		}

		Iterator itr = items.iterator();
		while (itr.hasNext()) {
			Item item = (Item) itr.next();
			item.update();
			if (!item.isOnGround()) {
				itr.remove();
			}
		}

		Iterator itr2 = arrows.iterator();
		while (itr2.hasNext()) {
			Arrow arrow = (Arrow) itr2.next();
			arrow.update();
			if (arrow.isHit()) {
				itr2.remove();
			}
		}

	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 1920, 1080)) {
					nullify();
					game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		// Print background [grass + cakes]
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {

				int mod_i = 120 * i;
				int mod_j = 120 * j;

				switch (tilemap[i][j]) {
				case 0:
					g.drawImage(Assets.grass, mod_i, mod_j);
					break;
				case 1:
					g.drawImage(Assets.grass, mod_i, mod_j);
					g.drawImage(Assets.cake, mod_i, mod_j);
					break;

				default:
					g.drawImage(Assets.grass, mod_i, mod_j);
					break;

				}
			}
		}

		// Print Link
		int mod_i = 120 * link.getXpos();
		int mod_j = 120 * link.getYpos();
		//
		// Draw character
		switch (link.getState()) {
		case Right:
			if (link.isMoving) {
				w_right.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_right.getImage(), mod_i, mod_j);
			}
			break;

		case Left:
			if (link.isMoving) {
				w_left.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_left.getImage(), mod_i, mod_j);
			}
			break;

		case Up:
			if (link.isMoving) {
				w_up.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(Assets.up, mod_i + link.xbonus, mod_j + link.ybonus);
			}
			break;

		case Down:
			if (link.isMoving) {
				w_down.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			} else {
				g.drawImage(a_down.getImage(), mod_i, mod_j);
			}
			break;

		case SwordAttack:
			sword.printSprite(g, mod_i + link.xbonus, mod_j + link.ybonus);
			break;
		}

		// Print Ghosts

		for (Ghost gst : ghosts) {

			mod_i = 120 * gst.getXpos();
			mod_j = 120 * gst.getYpos();

			switch (gst.getState()) {
			case Right:
				g.drawImage(ag_right.getImage(), mod_i + gst.xbonus, mod_j
						+ gst.ybonus);

				break;

			case Left:
				g.drawImage(ag_left.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Up:
				g.drawImage(ag_down.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Down:
				g.drawImage(ag_down.getImage(), gst.xbonus + mod_i, mod_j
						+ gst.ybonus);
				break;

			case Dead:
				g.drawImage(ag_dead.getImage(), mod_i, mod_j);
				break;

			case Invisible:
				// Derp, nothing here!
				break;
			}
		}

		for (Item itn : items) {

			mod_i = 120 * itn.getXpos();
			mod_j = 120 * itn.getYpos();

			switch (itn.getType()) {
			case Heart:
				g.drawImage(item_heart, mod_i, mod_j);
				break;

			case Bow:
				g.drawImage(item_bow, mod_i, mod_j);
				break;

			case Arrow:
				g.drawImage(item_heart, mod_i, mod_j);
				break;
			}

			}

		for (Arrow arrow : arrows) {
			mod_i = 120 * arrow.getXpos();
			mod_j = 120 * arrow.getYpos();
			g.drawImage(shuriken, mod_i + arrow.getXbonus(),
					mod_j + arrow.getYbonus());

		}

		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	public static SpriteSheet getSword() {
		return sword;
	}

	public void setSword(SpriteSheet swordM) {
		sword = swordM;
	}

	public void animate() {

		if (link.isMoving && !(link.getState() == State.SwordAttack)) {
			w_left.update(10);
			w_right.update(10);
			w_down.update(10);
			w_up.update(10);
		} else if (link.getState() == State.SwordAttack) {
			sword.update(10);
		} else {
			a_down.update(10);
			a_left.update(10);
			a_right.update(10);
		}
		for (Ghost gst : ghosts) {
			if (gst.isAlive) {
				ag_right.update(10);
				ag_left.update(10);
				ag_down.update(10);
			} else {
				ag_dead.update(10);
			}

		}
		Iterator<Ghost> iterator = ghosts.iterator();
		while (iterator.hasNext()) {
			Ghost gts = iterator.next();
			if (!gts.isAlive && ag_dead.getCurrentFrame() == 10) {
				tilemap[gts.xpos][gts.ypos] = 0;
				ag_dead.update(1);
				iterator.remove();
				dropItem(gts.getXpos(), gts.getYpos());
				ag_dead.setCurrentFrame(0);
			}
		}
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		up = null;
		down = null;
		left = null;
		right = null;
		g_up = null;
		g_down = null;
		g_left = null;
		g_right = null;
		grass = null;
		cake = null;

		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		g.drawString("Tap to Start.", 400, 240, paint);

	}

	private void drawRunningUI() {
		int offset = 20;
		Graphics g = game.getGraphics();
		for (int i = 0; i < link.getLife(); i++) {
			g.drawImage(Assets.heart, offset, 20);
			offset += heart.getWidth() + 10;
		}
		// g.drawImage(Assets.button, 0, 350, 0, 65, 65, 65);
		// g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);
		// g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);

	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.gameover, 0, 0);

	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		// TODO Auto-generated method stub
		game.setScreen(new MainMenuScreen(game));

	}

	public void createTilemap() {
		int freq = 5;

		tilemap = new int[rows][columns];

		rows = tilemap.length;
		columns = tilemap[1].length;

		Random r = new Random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (r.nextInt(5) == 0) {
					tilemap[i][j] = 1;
				} else {
					tilemap[i][j] = 0;
				}
			}
		}
	}

	public static int getRows() {
		return rows;
	}

	public static void setRows(int rows) {
		GameScreen.rows = rows;
	}

	public static int getColumns() {
		return columns;
	}

	public static void setColumns(int columns) {
		GameScreen.columns = columns;
	}

	public SpriteSheet getW_left() {
		return w_left;
	}

	public void setW_left(SpriteSheet w_left) {
		this.w_left = w_left;
	}

	public void cleanCurrentFrames() {
		w_left.setCurrentFrame(0);

	}

	public void atackGhost() {
		for (Ghost gst : ghosts) {

			switch (link.state) {

			case Down:
				if (link.xpos == gst.xpos && (link.ypos + 1) == gst.ypos) {
					gst.kill();
				}
				break;

			case Up:
				if (link.xpos == gst.xpos && (link.ypos - 1) == gst.ypos) {
					gst.kill();
				}
				break;

			case Right:
				if ((link.xpos + 1) == gst.xpos && link.ypos == gst.ypos) {
					gst.kill();
				}
				break;

			case Left:
				if ((link.xpos - 1) == gst.xpos && link.ypos == gst.ypos) {
					gst.kill();
				}
				break;

			}
		}
	}

	public static void cleanFrames() {
		ag_dead.update(50);
		ag_dead.setCurrentFrame(0);
	}

	public static Character getLink() {
		return link;
	}

	public void setLink(Character link) {
		this.link = link;
	}

	public void addGhost() {
		Ghost generated = new Ghost();
		ghosts.add(generated);
	}

	public void dropItem(int x, int y) {
		Item item = new Item(x, y);
		items.add(item);
	}

}