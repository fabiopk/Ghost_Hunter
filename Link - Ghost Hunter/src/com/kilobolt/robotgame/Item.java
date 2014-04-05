package com.kilobolt.robotgame;

import java.util.Random;

public class Item {

	enum Type {
		Heart, Bow, Arrow
	}

	private int xpos, ypos;
	private Type type;
	private boolean onGround;

	public Item() { // Create a random item at a random location.
		onGround = true;
		boolean isEmpty = false;
		int x, y;
		int[][] map = GameScreen.getTilemap();
		Random rdn = new Random();
		while (!isEmpty) { // Check if the place is empty
			x = rdn.nextInt(15);
			y = rdn.nextInt(8);
			if (map[x][y] == 0) {
				isEmpty = false;
			}
			xpos = x;
			ypos = y;
		}

		x = rdn.nextInt(3); // using same varaible to get random type
		switch (x) {

		case 0:
			type = Type.Heart;
			break;

		case 1:
			type = Type.Bow;
			break;

		case 2:
			type = Type.Arrow;
			break;

		}
	}

	public Item(int x, int y) { // Create random item at position x, y
		xpos = x;
		ypos = y;
		onGround = true;

		Random rdn = new Random();

		x = rdn.nextInt(3); // using same varaible to get random type
		switch (x) {

		case 0:
			type = Type.Heart;
			break;

		case 1:
			type = Type.Bow;
			break;

		case 2:
			type = Type.Arrow;
			break;

		}
	}
	
	public void update() {
		if(GameScreen.getLink().getXpos() == this.xpos && GameScreen.getLink().getYpos() == this.ypos) {
			switch(type) {
			
			case Heart:
				GameScreen.getLink().setLife(GameScreen.getLink().getLife() +1);
				break;
				
			case Bow:
				GameScreen.getLink().setLife(GameScreen.getLink().getLife() +1);
				break;
				
			case Arrow:
				GameScreen.getLink().setLife(GameScreen.getLink().getLife() +1);
				break;
			}
			onGround = false;
		}
	}

	public int getXpos() {
		return xpos;
	}

	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	public int getYpos() {
		return ypos;
	}

	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isOnGround() {
		return onGround;
	}

	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}


}
