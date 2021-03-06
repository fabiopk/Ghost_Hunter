package com.kilobolt.robotgame;

import java.util.ArrayList;
import java.util.Iterator;

public class Arrow {

	private int xpos, ypos, xspeed, yspeed, ybonus, xbonus;
	private boolean hit;

	public Arrow(int x, int y) {
		xspeed = 9;
		yspeed = 9;
		hit = false;
		xpos = x;
		ypos = y;
	}

	public void update() {
		int[][] map = GameScreen.getTilemap();

		xbonus += xspeed;
		ybonus += yspeed;

		if (xbonus <= -120) {
			xpos -= 1;
			xbonus = 0;
			try {
				switch (map[xpos - 1][ypos]) {
				case 0:
					// Do nothing
					break;

				case 1:
					hit = true;
					break;

				case 2:
					// Do nothing
					break;
				case 3:
					killGhost();
					hit = true;
					break;

				}
			} catch (Exception e) {
				this.hit = true;
			}
		}

		if (xbonus >= 120) {
			xpos += 1;
			xbonus = 0;

			try {
				switch (map[xpos + 1][ypos]) {
				case 0:
					// Do nothing
					break;

				case 1:
					hit = true;
					break;

				case 2:
					// Do nothing
					break;
				case 3:
					killGhost();
					hit = true;
					break;

				}
			} catch (Exception e) {
				this.hit = true;
			}
		}

		if (ybonus <= -120) {
			ypos -= 1;
			ybonus = 0;

			try {
				switch (map[xpos][ypos - 1]) {
				case 0:
					// Do nothing
					break;

				case 1:
					hit = true;
					break;

				case 2:
					// Do nothing
					break;
				case 3:
					killGhost();
					hit = true;
					break;

				}
			} catch (Exception e) {
				this.hit = true;
			}
		}

		if (ybonus >= 120) {
			ypos += 1;
			ybonus = 0;

			try {
				switch (map[xpos][ypos + 1]) {
				case 0:
					// Do nothing
					break;

				case 1:
					hit = true;
					break;

				case 2:
					// Do nothing
					break;
				case 3:
					killGhost();
					hit = true;
					break;

				}
			} catch (Exception e) {
				this.hit = true;
			}
		}
	}

	private void killGhost() {
		Iterator<Ghost> its = GameScreen.getGhosts().iterator();
		while (its.hasNext()) {
			Ghost ghost = its.next();
			its.remove();
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

	public int getXspeed() {
		return xspeed;
	}

	public void setXspeed(int xspeed) {
		this.xspeed = xspeed;
	}

	public int getYspeed() {
		return yspeed;
	}

	public void setYspeed(int yspeed) {
		this.yspeed = yspeed;
	}

	public int getYbonus() {
		return ybonus;
	}

	public void setYbonus(int ybonus) {
		this.ybonus = ybonus;
	}

	public int getXbonus() {
		return xbonus;
	}

	public void setXbonus(int xbonus) {
		this.xbonus = xbonus;
	}

	public boolean isHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

}