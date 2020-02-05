package edu.virginia.engine.display;

import edu.virginia.engine.util.GameClock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;

public class AnimatedSprite extends Sprite {

    private ArrayList<Animation> animations;
    private String fileName;
    private ArrayList<BufferedImage> frames;
    private static final int DEFAULT_ANIMATION_SPEED = 1;

    private boolean playing;
    private int currentFrame;
    private int startFrame;
    private int endFrame;
    private GameClock gameClock;
    private int animationSpeed;

    public AnimatedSprite(String id, String fileName, Point position){
        super(id, fileName);
        this.animationSpeed = DEFAULT_ANIMATION_SPEED;
        this.gameClock = new GameClock();
        super.setPosition(position);
        this.animations = new ArrayList<Animation>();
        this.frames = new ArrayList<BufferedImage>();
        this.fileName = "";
        this.startFrame = 0;
        this.endFrame = 0;
        this.playing = true;
    }

    public void initGameClock() {
        if(this.gameClock == null) {
            this.gameClock = new GameClock();
        }
    }

    public int getAnimationSpeed() {
        return this.animationSpeed;
    }
    public void setAnimationSpeed(int speed) {
        this.animationSpeed = speed;
    }

    public ArrayList<Animation> getAnimations() {
        return this.animations;
    }
    public void setAnimations(ArrayList<Animation> animationList) {
        this.animations = animationList;
    }

    public void addAnimation(Animation animation) {
        this.animations.add(animation);
    }

    public Animation getAnimation(String id){
        for (int i=0; i<= endFrame; i++){
            if(((this.getAnimations()).get(i).getId()).equals(id)){
                return (this.getAnimations()).get(i);
            }
            else {
                return null;
            }
        }
        return this.getAnimations().get(0);
    }

    public int getStartFrame() {
        return this.startFrame;
    }

    public void setStartFrame(int frame){
        this.startFrame = frame;
    }

    public int getEndFrame(){
        return this.endFrame;
    }

    public void setEndFrame(int frame){
        this.endFrame = frame;
    }

    public int getCurrentFrame(){
        return this.currentFrame;
    }

    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public void draw(Graphics g) {
        if (playing && ((gameClock.getElapsedTime()) > (this.animationSpeed*15))) {
            if(currentFrame == endFrame) {
                this.currentFrame = startFrame;
            }
            else {
                this.currentFrame++;
            }
            this.setImage(animations.get(currentFrame).getDisplayImage());
            super.draw(g);
            gameClock.resetGameClock();
        }
    }

    public void animate(int startFrame, int endFrame){
        this.setStartFrame(startFrame);
        this.setEndFrame(endFrame);
    }

    public void animate(Animation a){
        this.setStartFrame(a.getStartFrame());
        this.setEndFrame(a.getEndFrame());
    }

    public void animate(String id) {
        Animation a = this.getAnimation(id);
        this.animate(a);
    }

    public void initializeFrames() {
        for(int i=0; i<=this.getEndFrame(); i++){
            BufferedImage img = this.readImage("" + this.getId() + "/walk_" + i + ".png");
            Animation a = new Animation(this.getId(), this.getStartFrame(), this.getEndFrame());
            a.setImage(img);
            this.addAnimation(a);
        }
    }

    public void stopAnimation() {
      setCurrentFrame(getStartFrame());
      setEndFrame(getStartFrame());

    }

    public void stopAnimation(int frameNumber) {
      setCurrentFrame(frameNumber);
      setStartFrame(getCurrentFrame());
      setEndFrame(getCurrentFrame());
    }
}
