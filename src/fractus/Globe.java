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
	private Scene scene ;
	private Sphere sphere;
	private Rotate rotateX = new Rotate(0,Rotate.X_AXIS);
	private Rotate rotateY = new Rotate(0,Rotate.Y_AXIS);
	private double mousePosX,mousePosY = 0;
	FractaleModele fracMod = new FractaleModele();
	FractaleControler fracControl= new FractaleControler(fracMod);
	PhongMaterial phongMaterial = new PhongMaterial();
	public Globe(double radius, double defposX, double defposY, Scene scene,WritableImage image){

		sphere=new Sphere(radius);
		sphere.setLayoutX(defposX);
		sphere.setLayoutY(defposY);		
		sphere.setVisible(true);
		sphere.getTransforms().addAll(rotateX,rotateY);
		this.scene = scene ;
		phongMaterial.setDiffuseMap(image);
		sphere.setMaterial(phongMaterial);
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
						(dy / sphere.getRadius()*4 * -360) * (Math.PI / 180));
				rotateY.setAngle(rotateY.getAngle() - 
						(dx / sphere.getRadius()*4 * -360) * (Math.PI / 180));

			}
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});
	}


	private boolean contains(double x, double y) {
		return sphere.getLayoutY()-sphere.getRadius() < y && y < sphere.getLayoutY() + sphere.getRadius() 
		&& sphere.getLayoutX()-sphere.getRadius() < x && x < sphere.getLayoutX()+sphere.getRadius()	;
	}

	public void handleScroll() {
		System.out.println("EZZZZ");
		sphere.setOnScroll((ScrollEvent event)-> {			
			fracControl.setZoomPlus((int)event.getDeltaX(),(int)event.getDeltaY());
			System.out.println("EVENTTTTT " + event.getSceneX());
			phongMaterial.setDiffuseMap(fracControl.getImage());
			sphere.setMaterial(phongMaterial);
			
		});
	}

	public Sphere getSphere(){
		return sphere;

	}


}
