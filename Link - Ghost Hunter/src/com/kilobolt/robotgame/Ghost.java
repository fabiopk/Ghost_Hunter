package com.kilobolt.robotgame;

import java.util.Iterator;
import java.util.Random;

import com.kilobolt.robotgame.Character.State;

public class Ghost extends Character {

	private int count_to_move;

	public Ghost() {
		int[][] mapa = GameScreen.getTilemap();
		Random position = new Random();
		int x = position.nextInt(7);
		int y = position.nextInt(7);
		for (int i = x; i < GameScreen.getRows(); i++) {
			for (int j = y; j < GameScreen.getColumns(); j++) {
				if (mapa[i][j] != 1) {
					this.xpos = i;
					ypos = j;
					return;
				}
			}
		}
		return;
	}

	public void randonMovement() {

		if (isAlive && count_to_move == 15) {
			move();
			count_to_move = 0;
		} else {
			count_to_move++;
		}
	}

	public void move() {
		Random dir = new Random();
		switch (dir.nextInt(7)) {
		case 0:
			this.moveDown();
			this.atack();
			break;

		case 1:
			this.moveUp();
			this.atack();
			break;

		case 2:
			this.moveLeft();
			this.atack();
			break;

		case 3:
			this.moveRight();
			this.atack();
			break;

		case 4:
			moveSameDirection();
			this.atack();
			break;

		case 5:
			moveSameDirection();
			this.atack();
			break;

		case 6:
			moveSameDirection();
			this.atack();
			break;

		}
	}

	public void moveSameDirection() {
		switch (state) {
		case Up:
			this.moveUp();
			break;

		case Down:
			this.moveDown();
			break;

		case Left:
			this.moveLeft();
			break;

		case Right:
			this.moveRight();
			break;
		}
	}

	public void kill() {
		GameScreen.cleanFrames();
		state = State.Dead;
		this.isAlive = false;
	}

	@Override
	public void atack() {
		
		switch (state) {

		case Up:
			if (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos - 1) == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Down:
			if (this.xpos == GameScreen.getLink().xpos
					&& (this.ypos + 1) == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Left:
			if ((this.xpos - 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		case Right:
			if ((this.xpos + 1) == GameScreen.getLink().xpos
					&& this.ypos == GameScreen.getLink().ypos) {
				GameScreen.getLink().kill();
			}
			break;

		default:
			break;

		}
	}
	


}
