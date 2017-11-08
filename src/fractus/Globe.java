package fractus;

import javafx.scene.shape.Sphere;

public class Globe {
	
	final double RADIUS = 480.;
	final int DEFPOSX = 620;
	final int DEFPOSY = 620 ;
	
	private Sphere sphere = new Sphere(RADIUS);

	public Globe(){
		sphere.setVisible(true);
		sphere.setLayoutX(DEFPOSX);
		sphere.setLayoutY(DEFPOSX);
		
	}
	public Sphere getSphere(){
		return sphere;
	}


}
