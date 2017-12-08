package Appli;

import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Globe {
	private Sphere sphere;
	private Rotate rotateZ = new Rotate(0,Rotate.Z_AXIS);
	private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
	
	
	//instanciation de la sphère 
	public Globe(double radius, double defposX, double defposY){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
		sphere.getTransforms().addAll(rotateZ,rotateY);

	}

	public boolean contains(double x, double y) {
		return sphere.getLayoutY()-sphere.getRadius() < y && y < sphere.getLayoutY() + sphere.getRadius() 
		&& sphere.getLayoutX()-sphere.getRadius() < x && x < sphere.getLayoutX()+sphere.getRadius()	;
	}
	
	public Sphere getSphere(){
		return sphere;

	}

	public Rotate getRotateZ() {
		return rotateZ;
	}

	public Rotate getRotateY() {
		return rotateY;
	}


}
