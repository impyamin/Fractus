package fractus;

import javafx.scene.shape.Sphere;

public class Globe {
	
	private Sphere sphere;
	
	

	public Globe(double radius, double defposX, double defposY){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
	}
	public Sphere getSphere(){
		return sphere;
		
	}


}
