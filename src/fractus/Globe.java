package fractus;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Globe {
	private Scene scene ;
	private Sphere sphere;
	private Rotate rotateZ = new Rotate(0,Rotate.Z_AXIS);
	private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
	private double mousePosZ,mousePosY = 0;

	public Globe(double radius, double defposX, double defposY, Scene scene){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
		sphere.getTransforms().addAll(rotateZ,rotateY);
		this.scene = scene ;
	}
	public void handleRotationEvents() {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosZ = sphere.getLayoutX();
            mousePosY = sphere.getLayoutY();
        });
        	
        scene.setOnMouseDragged((MouseEvent me) -> {
        	System.out.println("YPOS : " + sphere.getLayoutY());
        	System.out.println("MOUSE : " + me.getSceneY());
        	if(sphere.getLayoutY()-sphere.getRadius() < me.getSceneY() 
        			&& me.getSceneY() < sphere.getLayoutY() + sphere.getRadius() 
        			&& sphere.getLayoutX()-sphere.getRadius() < me.getSceneX()
        			&& me.getSceneX()< sphere.getLayoutX()+sphere.getRadius())
        	{
            double dx = (me.getX()-sphere.getLayoutX()) ;
            double dy = (me.getY() - sphere.getLayoutY());
            if (me.isPrimaryButtonDown()) {
               rotateZ.setAngle(rotateZ.getAngle() - 
                   (dy / sphere.getRadius() * 360) * (Math.PI / 180));
                rotateY.setAngle(rotateY.getAngle() - 
                   (dx / sphere.getRadius()*  360) * (Math.PI / 180));
            }
            mousePosZ = me.getSceneX();
            mousePosY = me.getSceneY();
        	}
        });
    }
	
	public Sphere getSphere(){
		return sphere;
		
	}


}
