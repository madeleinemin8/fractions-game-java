package edu.virginia.engine.display;

import java.util.ArrayList;

public class Animation extends Sprite {

    //private String id;
    private int startFrame;
    private int endFrame;

    public Animation (String animType, int start, int end) {
        super(animType);
        //this.id = animType;
        this.startFrame = start;
        this.endFrame = end;
    }

    public int getStartFrame() {
        return this.startFrame;
    }

    public void setStartFrame(int start) {
        this.startFrame = start;
    }

    public int getEndFrame() {
        return this.endFrame;
    }

    public void setEndFrame(int end) {
        this.startFrame = end;
    }

}
