package fractus;

import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;

public class Globe {
	private Scene scene ;
	private Sphere sphere;
	private Rotate rotateX = new Rotate(0,Rotate.Z_AXIS);
	private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
	private double mousePosX,mousePosY = 0;

	public Globe(double radius, double defposX, double defposY, Scene scene){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
		sphere.getTransforms().addAll(rotateX,rotateY);
		this.scene = scene ;
	}
	
    public void handleRotationEvents() {
        scene.setOnMousePressed((MouseEvent me) -> {
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });

        
        scene.setOnMouseDragged((MouseEvent me) -> {
            double dx = (mousePosX - me.getSceneX()) ;
            double dy = (mousePosY - me.getSceneY());
            System.out.println(dx);
            System.out.println(dy);
            if (me.isPrimaryButtonDown() && contains(me.getSceneX(), me.getSceneY()))
        	 {
                rotateX.setAngle(rotateX.getAngle() - 
                    (dy / sphere.getRadius()*2 * 360) * (Math.PI / 180)*1.3);
                rotateY.setAngle(rotateY.getAngle() - 
                    (dx / sphere.getRadius()*2 * 360) * (Math.PI / 180)*1.3);
                
            }
            mousePosX = me.getSceneX();
            mousePosY = me.getSceneY();
        });
    }

	
	private boolean contains(double x, double y) {
		return sphere.getLayoutY()-sphere.getRadius() < y && y < sphere.getLayoutY() + sphere.getRadius() 
    			&& sphere.getLayoutX()-sphere.getRadius() < x && x < sphere.getLayoutX()+sphere.getRadius()	;
}
public Sphere getSphere(){
		return sphere;
		
	}
	

}
