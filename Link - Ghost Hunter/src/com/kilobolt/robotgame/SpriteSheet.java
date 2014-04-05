package com.kilobolt.robotgame;

import java.util.ArrayList;

import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Image;

public class SpriteSheet {

	private int frames;
	private int currentFrame;
	private long animTime;
	private long totalDuration;
	private int height, width;
	private int duration;
	private Image sheet;
	private boolean isOver;

	public SpriteSheet(Image image, int frames, int duration) {
		sheet = image;
		this.frames = frames;
		this.duration = duration;
		height = image.getHeight();
		width = image.getWidth();
		currentFrame = 0;
		totalDuration = frames * duration;
		animTime = 0;
		isOver = false;
	}

	public synchronized void update(long elapsedTime) {
		if (frames > 1) {
			animTime += elapsedTime;
			if (animTime >= totalDuration) {
				animTime = animTime % totalDuration;
				currentFrame = 0;
				isOver = true;

			}

			while (animTime > (currentFrame * duration)) {
				currentFrame++;

			}
		}
	}
	
	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean isOver) {
		this.isOver = isOver;
	}

	public void printSprite(Graphics g, int xpos, int ypos) {
		g.drawImage(sheet, xpos, ypos, (this.currentFrame * 120), 0,
				120, height);	
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

}
