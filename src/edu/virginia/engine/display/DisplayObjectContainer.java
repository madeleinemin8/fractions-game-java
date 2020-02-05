package edu.virginia.engine.display;

import java.lang.reflect.Array;
import java.awt.*;
import java.util.ArrayList;

public class DisplayObjectContainer extends DisplayObject {
    ArrayList<DisplayObject> childrenList;

    public DisplayObjectContainer (String id) {
        super(id);
        childrenList = new ArrayList<DisplayObject>();
    }

    public DisplayObjectContainer (String id, String fileName) {
        super(id, fileName);
        childrenList = new ArrayList<DisplayObject>();
    }

    // add a child to the end of the ArrayList
    public void addChild(DisplayObject child) {
        this.childrenList.add(child);
    }

    // add a child at index i
    public void addChildAtIndex(DisplayObject child, int i) {
        this.childrenList.add(i, child);
    }

    // remove child with a specific id
    public void removeChild(String id) {
        for(int i=0; i<this.childrenList.size(); i++) {
            if(this.childrenList.get(i).getId().compareTo(id) == 0){
                this.removeByIndex(i);
            }
        }
    }

    // remove child at index i
    public void removeByIndex(int i) {
        this.childrenList.remove(i);
    }

    // remove all the children from the ArrayList
    public void removeAll() {
        for(int i=0; i<this.childrenList.size(); i++) {
            this.removeByIndex(0);
        }
    }

    // returns true if child is in childrenList
    public boolean contains(DisplayObject child) {
        if(this.childrenList.contains(child))
            return true;
        return false;
    }

    public ArrayList<DisplayObject> getChildren() {
        return this.childrenList;
    }

    public void draw(Graphics2D graphObj){
        super.draw(graphObj); //Draw myself

        applyTransformations(graphObj); // Apply MY transformations to my children

        for (int i=0; i<getChildren().size(); i++) //Draw each of my children
            getChildren().get(i).draw(graphObj);

        reverseTransformations(graphObj); //Reverse my transformations
    }

}
