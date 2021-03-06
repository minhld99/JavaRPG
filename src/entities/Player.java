package entities;

import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Player extends Entity {
	ImageView imageView;
	
    int count = 3;
    int columns = 3;
    int offsetX = 0;
	int offsetY = 0;
    int width = 32;
    int height = 32;
    int velocity = 2;
	public SpriteAnimation animation;
	HealthBar healthBar;
	private static final double healthMax = 150;
	double health;
	
//	private boolean isFacingRight = false;
//    private boolean isFacingDown = true;
//    private boolean isFacingUp = false;
//    private boolean isFacingLeft = false;

    public ArrayList<Entity> collisions = new ArrayList<>();
    
    public Player(ImageView imageView) {
    	this.setLayoutX(0);
		this.setLayoutY(0);
    	this.imageView = imageView;
    	this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));
    	animation = new SpriteAnimation(imageView, Duration.millis(300), count, columns, offsetX, offsetY, width, height);
    	getChildren().addAll(imageView);
    	this.health = 100;
    	healthBar = new HealthBar();
    	this.getChildren().add(this.healthBar);
    	healthBar.relocate(x + 2 + (imageView.getBoundsInLocal().getWidth()-healthBar.getBoundsInLocal().getWidth())/2, y - healthBar.getBoundsInLocal().getHeight()/2);
    }
    /** ============================= GETTERS ============================== **/

	public int getOffsetX() {
		return offsetX;
	}

	public int getOffsetY() {
		return offsetY;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public double getHealth() {
		return health;
	}
	
	public double getRelativeHealth() {
		return getHealth() / healthMax;
	}
	
//	public boolean isFacingRight() {
//        boolean facing = false;
//        if (isFacingRight && !isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
//        return facing;
//    }
//
//    public boolean isFacingLeft() {
//        boolean facing = false;
//        if (!isFacingRight && !isFacingDown && !isFacingUp && isFacingLeft) facing = true;
//        return facing;
//    }
//
//    public boolean isFacingDown() {
//        boolean facing = false;
//        if (!isFacingRight && isFacingDown && !isFacingUp && !isFacingLeft) facing = true;
//        return facing;
//    }
//
//    public boolean isFacingUp() {
//        boolean facing = false;
//        if (!isFacingRight && !isFacingDown && isFacingUp && !isFacingLeft) facing = true;
//        return facing;
//    }
//
//    // diagonal
//    public boolean isFacingUpRight() {
//        boolean facing = false;
//        if (isFacingRight && isFacingUp) facing = true;
//
//        return facing;
//    }
//
//    public boolean isFacingUpLeft() {
//        boolean facing = false;
//        if (isFacingLeft && isFacingUp) facing = true;
//
//        return facing;
//    }
//
//    public boolean isFacingDownRight() {
//        boolean facing = false;
//        if (isFacingRight && isFacingDown) facing = true;
//
//        return facing;
//    }
//
//    public boolean isFacingDownLeft() {
//        boolean facing = false;
//        if (isFacingDown && isFacingLeft) facing = true;
//
//        return facing;
//    }
//    
    /** =========================== SETTERS =========================== **/
    
    public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
    
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}

    // face setters
//    public void setFaceRight() {
//        isFacingRight = true;
//        isFacingDown = false;
//        isFacingUp = false;
//        isFacingLeft = false;
//    }
//
//    public void setFaceLeft() {
//        isFacingRight = false;
//        isFacingDown = false;
//        isFacingUp = false;
//        isFacingLeft = true;
//    }
//
//    public void setFaceDown() {
//        isFacingRight = false;
//        isFacingDown = true;
//        isFacingUp = false;
//        isFacingLeft = false;
//    }
//
//    public void setFaceUp() {
//        isFacingRight = false;
//        isFacingDown = false;
//        isFacingUp = true;
//        isFacingLeft = false;
//    }
//
//    // diagonal faces
//    public void setFaceUpRight() {
//        isFacingRight = true;
//        isFacingDown = false;
//        isFacingUp = true;
//        isFacingLeft = false;
//    }
//
//    public void setFaceUpLeft() {
//        isFacingRight = false;
//        isFacingDown = false;
//        isFacingUp = true;
//        isFacingLeft = true;
//    }
//
//    public void setFaceDownRight() {
//        isFacingRight = true;
//        isFacingDown = true;
//        isFacingUp = false;
//        isFacingLeft = false;
//    }
//
//    public void setFaceDownLeft() {
//        isFacingRight = false;
//        isFacingDown = true;
//        isFacingUp = false;
//        isFacingLeft = true;
//    }
	
    /** ==================== COLLISION ============================ **/

    public void addCollision(Entity gameObject2D) {
        collisions.add(gameObject2D);
    }

    public void addCollision(ArrayList<Entity> gameObject2D) {
        collisions.addAll(gameObject2D);
    }

//    public String getVerticalCollision() {
//        String collided = "NONE";

//         //look through the list of collisions for this entities
//        for(Entity e : collisions) {
//            // vertical check
//            if(this.isFacingUp) {
//                if (c.getMaxY() >= this.getLayoutY() && this.getLayoutY() + this.getHeight() > c.getMaxY()
//                        && ((this.getLayoutX() >= c.getMinX() && this.getLayoutX() + this.getWidth() <= c.getMaxX()) ||
//                        (this.getLayoutX() > c.getMinX() && this.getLayoutX() < c.getMaxX()) ||
//                        (this.getLayoutX() > c.getMinX() && this.getLayoutX() + this.getWidth() < c.getMaxX()) ||
//                        (this.getLayoutX() + this.getWidth() > c.getMinX() && this.getLayoutX() + this.getWidth() < c.getMaxX()) )) {
//
//                    collided = "UP";
//                    System.out.println(": Collided Upward");
//                }
//            }

//            else if(this.isFacingDown) {
//                if ( this.getLayoutY() + this.getHeight() >= c.getMinY() && this.getLayoutY() < c.getMinY()
//                        && ((this.getLayoutX() >= c.getMinX() && this.getLayoutX() + this.getWidth() <= c.getMaxX()) ||
//                        (this.getLayoutX() > c.getMinX() && this.getLayoutX() < c.getMaxX()) ||
//                        (this.getLayoutX() > c.getMinX() && this.getLayoutX() + this.getWidth() < c.getMaxX()) ||
//                        (this.getLayoutX() + this.getWidth() > c.getMinX() && this.getLayoutX() + this.getWidth() < c.getMaxX()) )) {
//
//                    collided = "DOWN";
//                    System.out.println(": Collided downward");
//                }
//            }
//        }
//        return collided;
//    }

//    public String getHorizontalCollision() {
//        // horizontal check
//        String collided = "NONE";
//
//        // look through the list of collisions for this entities
//        for (Entity e : collisions) {
//            if (this.isFacingRight) {
//                if (this.getLayoutX() + this.getWidth() >= c.getMinX() && c.getMinX() > this.getLayoutX()
//                        && ((this.getLayoutY() >= c.getMinY() && this.getLayoutY() + this.getHeight() < c.getMaxY()) ||
//                        (this.getLayoutY() > c.getMinY() && this.getLayoutY() < c.getMaxY()) ||
//                        (this.getLayoutY() > c.getMinY() && this.getLayoutY() + this.getHeight() < c.getMaxY()) ||
//                        (this.getLayoutY() + this.getHeight() > c.getMinY() && this.getLayoutY() + this.getHeight() < c.getMaxY()))) {
//
//                    collided = "RIGHT";
//                    System.out.println(": Collided rightward");
//                }
//                
//            } else if (this.isFacingLeft) {
//                if (this.getLayoutX() <= c.getMaxX() && c.getMaxX() < this.getLayoutX() + this.getWidth()
//                        && ((this.getLayoutY() >= c.getMinY() && this.getLayoutY() + this.getHeight() <= c.getMaxY()) ||
//                        (this.getLayoutY() > c.getMinY() && this.getLayoutY() < c.getMaxY()) ||
//                        (this.getLayoutY() > c.getMinY() && this.getLayoutY() + this.getHeight() < c.getMaxY()) ||
//                        (this.getLayoutY() + this.getHeight() > c.getMinY() && this.getLayoutY() + this.getHeight() < c.getMaxY()))) {
//
//                    collided = "LEFT";
//                    System.out.println(": Collided leftward");
//                }
//            }
//        }
//        return collided;
//    }

    public void moveX(int x) {
		boolean right = x>0?true:false;
		for(int i = 0; i < Math.abs(x); i ++) {
			if(right) {if (this.getTranslateX()<64*32) this.setTranslateX(this.getTranslateX() + 1);}
			else {if(this.getTranslateX()>0) this.setTranslateX(this.getTranslateX()-1);}
			
		}
	}

	public void moveY(int y) {
		boolean down = y>0?true:false;
		for(int i = 0; i < Math.abs(y); i ++) {
			if(down) {if (this.getTranslateY() < 64*20) this.setTranslateY(this.getTranslateY() + 1);}
			else {if (this.getTranslateY()>0) this.setTranslateY(this.getTranslateY()-1);}
			
		}
	}
    
    public boolean checkEntityCollision(float xOffset, float yOffset) { // kiem tra va cham cua thuc the
        for(Entity e: collisions){ // nhan danh sach cac thuc the 
        	if (e.getName().equals("tree")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX() + 1, e.getLayoutY() + 1, e.width - 1 , e.height - 4);
            	if(entityBound.intersects((this.getTranslateX()+ 10 + xOffset), (this.getTranslateY() + 25 + yOffset), this.getWidth()-20, this.getHeight() - 28)){ // co giao cat
                        return true;
            	}
                    
            }
        	if (e.getName().equals("rock")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width, e.height);
            	if(entityBound.intersects((this.getTranslateX()+10 + xOffset), (this.getTranslateY() + 20 + yOffset), this.getWidth()-20, this.getHeight()-20)){ // co giao cat
                        return true;
            	}
        	}
        	if (e.getName().equals("water")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width, e.height);
            	if(entityBound.intersects((this.getTranslateX()+8 + xOffset), (this.getTranslateY() + 16 + yOffset), this.getWidth()/2, this.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        	
        	if (e.getName().equals("monster")) {
        		Rectangle entityBound = new Rectangle(e.getTranslateX() + e.getLayoutX()+14, e.getTranslateY()+e.getLayoutY()+5, e.getWidth()-25, e.getHeight()-15);
        		//System.out.println("monster "+(e.getTranslateX()+ e.getLayoutX()-15) + " " + (e.getTranslateY()+e.getLayoutY()-2) + " "+ (e.getWidth()/2)+" "+ (e.getHeight()-3));
            	if(entityBound.intersects((this.getTranslateX() + xOffset*2), (this.getTranslateY()  + yOffset*2), this.getWidth(), this.getHeight())){ // co giao cat
            		//System.out.println("player "+(this.getTranslateX() + xOffset) + " " + (this.getTranslateY()  + yOffset ) + " "+ this.getWidth()/2+" "+ this.getHeight()/2);
            		//update(-1);
                    return true;
            	}
        	}
        	if (e.getName().equals("house")) {
        		Rectangle entityBound = new Rectangle(e.getLayoutX(), e.getLayoutY(), e.width, e.height);
            	if(entityBound.intersects((this.getTranslateX()+8 + xOffset), (this.getTranslateY() + 10 + yOffset), this.getWidth()/2, this.getHeight()/2)){ // co giao cat
                        return true;
            	}
        	}
        }
        return false; // khong co va cham
    }
    
    public void update(int i) {
    	this.health += i;
    	if (this.health <= 0) {
    		setActive(false);
    		die();
    	}
    	healthBar.setValue(getRelativeHealth());
    }
	private void die() {
		this.animation.play();
		this.animation.setOffsetX(32*3);
		this.animation.setOffsetY(32*7);
		
	}
}