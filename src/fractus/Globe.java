package fractus;

import com.sun.prism.TextureMap;

import javafx.scene.shape.Sphere;

public class Globe {
	
	final double RADIUS = 500.;
	final int DEFPOSX = 500;
	final int DEFPOSY = 500 ;
	
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
