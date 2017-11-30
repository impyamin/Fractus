package fractus;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;


public class Globe {
	private Sphere sphere;
	private Rotate rotateX = new Rotate(0,Rotate.X_AXIS);
	private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);

	public Globe(double radius, double defposX, double defposY){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
		sphere.getTransforms().addAll(rotateX,rotateY);

	}

	public void handleRotationEvents() {
		
	}


	public boolean contains(double x, double y) {
		return sphere.getLayoutY()-sphere.getRadius() < y && y < sphere.getLayoutY() + sphere.getRadius() 
		&& sphere.getLayoutX()-sphere.getRadius() < x && x < sphere.getLayoutX()+sphere.getRadius()	;
	}

	public void handleScroll() {

	}

	public Sphere getSphere(){
		return sphere;

	}

	public Rotate getRotateX() {
		return rotateX;
	}

	public Rotate getRotateY() {
		return rotateY;
	}


}
