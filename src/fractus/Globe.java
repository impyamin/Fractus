package fractus;

import com.sun.prism.TextureMap;

import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Sphere;

public class Globe {
	
	final double RADIUS = 500.;
	final int DEFPOS = 500;
	
	
	private Sphere sphere = new Sphere(RADIUS);

	public Globe(){
		sphere.setVisible(true);
		sphere.setLayoutX(DEFPOS);
		sphere.setLayoutY(DEFPOS);		
	}
	public Sphere getSphere(){
		return sphere;
		
	}


}
