package edu.virginia.engine.display;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * A very basic display object for a java based gaming engine
 *
 * */
public class DisplayObject {

	/* All DisplayObject have a unique id */
	private String id;

	/* The image that is displayed by this object */
	private BufferedImage displayImage;

	private Point position;

	private Point pivotPoint;

	/* degrees of rotation of the object */
	private double rotation;

	private boolean visible;

	private float alpha;

	private float oldAlpha;

	private double scaleX;

	private double scaleY;

	private boolean hasbeenpressed;

	private DisplayObject parent;

	private Rectangle hitBox;

	private boolean hasPhysics;

	/**
	 * Constructors: can pass in the id OR the id and image's file path and
	 * position OR the id and a buffered image and position
	 */
	public DisplayObject(String id) {
		this.setId(id);
		this.position = new Point(0, 0);
		this.setPivotPoint(new Point(0, 0));
		this.setRotation(0);
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.hasbeenpressed = false;
		this.setHitBox(this.getPosition().x, this.getPosition().y, this.getUnscaledWidth(), this.getUnscaledHeight());
		this.hasPhysics = false;
	}

	public DisplayObject(String id, String fileName) {
		this.setId(id);
		this.setImage(fileName);
		this.position = new Point(0, 0);
		this.setPivotPoint(new Point(0, 0));
		this.setRotation(0);
		this.visible = true;
		this.alpha = 1.0f;
		this.oldAlpha = 0.0f;
		this.scaleX = 1.0;
		this.scaleY = 1.0;
		this.hasbeenpressed = false;
		this.hitBox = new Rectangle(this.getPosition().x, this.getPosition().y, this.getUnscaledWidth()/3, this.getUnscaledHeight()/3);
		//System.out.println(this.getHitBox());
		this.hasPhysics = false;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setPosition(Point p) {
		this.position = p;
		this.setHitBoxPos(p.x, p.y);
	}

	public Point getPosition() {
		return position;
	}

	public void setPivotPoint(Point p) {
		this.pivotPoint = p;
	}

	public Point getPivotPoint() {
		return pivotPoint;
	}

	public void setRotation(double rot) {
		this.rotation = rot;
	}

	public double getRotation() {
		return rotation;
	}

	public void setVisible(boolean isVisible) {
		this.visible = isVisible;
	}

	public boolean getVisible() {
		return visible;
	}

	public void setAlpha(float a) {
		this.alpha = a;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setOldAlpha(float oldA) {
		this.oldAlpha = oldA;
	}

	public float getOldAlpha() {
		return oldAlpha;
	}

	public void setScaleX(double x){
		this.scaleX = x;
		this.hitBox.width = (int)((this.hitBox.width * x) / this.scaleX);
	}

	public double getScaleX(){
		return scaleX;
	}

	public void setScaleY(double y){
		this.scaleY = y;
		this.hitBox.height = (int)((this.hitBox.height * y) / this.scaleY);
	}

	public double getScaleY(){
		return scaleY;
	}

	public void setHasbeenpressed(boolean pressed) {
		this.hasbeenpressed = pressed;
	}

	public boolean getHasbeenpressed() {
		return hasbeenpressed;
	}

	public void setParent(DisplayObject p) {
		this.parent = p;
	}

	public DisplayObject getParent() {
		return this.parent;
	}

	public Rectangle getHitBox() { return this.hitBox; }

	public void setHitBox(int minX, int minY, int w, int h){
		this.hitBox = new Rectangle(minX, minY, w, h);
	}

	public void setHitBoxPos(int minX, int minY) {
		this.hitBox.setLocation(minX, minY);
	}

	public boolean collidesWith(DisplayObject other) {
		if(other.getHitBox().intersects(this.getHitBox()))
			return true;
		return false;
	}

	public int movementDirection(Point prevLocation) {
		boolean left = this.getPosition().x > prevLocation.x;
		boolean right = this.getPosition().x < prevLocation.x;
		boolean up = this.getPosition().y > prevLocation.y;
		boolean down = this.getPosition().y < prevLocation.y;
		if(left && up) return 1; // repel left and up
		else if(left && down) return 2; // repel left and down
		else if(right && up) return 3; // repel right and up
		else if(right && down) return 4; // repel right and down
		else if(left) return 5; // repel left
		else if(right) return 6; // repel right
		else if(up) return 7; // repel up
		else return 8; // repel down
	}

	public boolean getHasPhysics() {
		return this.hasPhysics;
	}

	public void setHasPhysics(boolean p) {
		if (p == true)
			this.hasPhysics = true;
		else
			this.hasPhysics = false;
	}

	/**
	 * Returns the unscaled width and height of this display object
	 * */
	public int getUnscaledWidth() {
		if(displayImage == null) return 0;
		return displayImage.getWidth();
	}

	public int getUnscaledHeight() {
		if(displayImage == null) return 0;
		return displayImage.getHeight();
	}

	public BufferedImage getDisplayImage() {
		return this.displayImage;
	}

	public void setImage(String imageName) {
		if (imageName == null) {
			return;
		}
		displayImage = readImage(imageName);
		if (displayImage == null) {
			System.err.println("[DisplayObject.setImage] ERROR: " + imageName + " does not exist!");
		}
	}


	/**
	 * Helper function that simply reads an image from the given image name
	 * (looks in resources\\) and returns the bufferedimage for that filename
	 * */
	public BufferedImage readImage(String imageName) {
		BufferedImage image = null;
		try {
			String file = ("resources" + File.separator + imageName);
			image = ImageIO.read(new File(file));
		} catch (IOException e) {
			System.out.println("[Error in DisplayObject.java:readImage] Could not read image " + imageName);
			e.printStackTrace();
		}
		return image;
	}

	public void setImage(BufferedImage image) {
		if(image == null) return;
		displayImage = image;
	}


	/**
	 * Invoked on every frame before drawing. Used to update this display
	 * objects state before the draw occurs. Should be overridden if necessary
	 * to update objects appropriately.
	 * */
	protected void update(ArrayList<Integer> pressedKeys) {

	}

	/**
	 * Draws this image. This should be overloaded if a display object should
	 * draw to the screen differently. This method is automatically invoked on
	 * every frame.
	 * */
	public void draw(Graphics g) {

		if (displayImage != null) {

			/*
			 * Get the graphics and apply this objects transformations
			 * (rotation, etc.)
			 */
			Graphics2D g2d = (Graphics2D) g;
			applyTransformations(g2d);

			/* Actually draw the image, perform the pivot point translation here */
			g2d.drawImage(displayImage, this.position.x, this.position.y,
					(int) (getUnscaledWidth()),
					(int) (getUnscaledHeight()), null);

			/*
			 * undo the transformations so this doesn't affect other display
			 * objects
			 */
			reverseTransformations(g2d);
		}
	}

	/**
	 * Applies transformations for this display object to the given graphics
	 * object
	 * */
	protected void applyTransformations(Graphics2D g2d) {
		g2d.translate(this.position.x, this.position.y);
		g2d.rotate(Math.toRadians(this.getRotation()), this.pivotPoint.x, this.pivotPoint.y);
		g2d.scale(this.scaleX, this.scaleY);
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, curAlpha * this.alpha));
	}

	/**
	 * Reverses transformations for this display object to the given graphics
	 * object
	 * */
	protected void reverseTransformations(Graphics2D g2d) {
		g2d.translate(-(this.position.x), -(this.position.y));
		g2d.rotate(Math.toRadians(-(this.getRotation())));
		g2d.scale(1/(this.scaleX), 1/(this.scaleY));
		float curAlpha;
		this.oldAlpha = curAlpha = ((AlphaComposite)g2d.getComposite()).getAlpha();
		g2d.setComposite(AlphaComposite.getInstance(3, this.oldAlpha));
	}

	// Given a point in the local coordinate system, return its corresponding point in the global coordinate system.
	public Point localToGlobal(Point p) {
		DisplayObject parent = this.getParent();
		if (parent == null) {
			return p;
		}
		double newx = 0;
		double newy = 0;
		while(parent != null) {
			double addx = p.x + this.getPosition().x;
			double addy = p.y + this.getPosition().y;
			newx = (addx / this.getScaleX()) * parent.getScaleX();
			newy = (addy / this.getScaleY()) * parent.getScaleY();
		}
		p.x = (int)newx;
		p.y = (int)newy;
		p = parent.localToGlobal(p);
		return p;
	}

	// Given a point in the global coordinate system, return its corresponding point in the DisplayObject’s own coordinate system.
	public Point globalToLocal(Point p) {
		DisplayObject parent = this.getParent();
		if (parent == null) {
			return p;
		}
		double newx = -this.getPosition().x;
		double newy = -this.getPosition().y;
		while(parent != null) {
			newx += p.x * this.getScaleX() / parent.getScaleX();
			newy += p.y * this.getScaleY() / parent.getScaleY();
		}
		Point newp = new Point();
		newp.x = (int)newx;
		newp.y = (int)newy;
		newp = parent.globalToLocal(newp);
		return newp;
	}
}
