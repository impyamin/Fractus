package fractus;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class View extends Application {
	
	public int actPosX = 0 ;

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fractus");
		Group group = new Group();
		Scene scene = new Scene(group,600,800);
		
		Globe globe = new Globe();
		
		Button buttonRight = new Button("->");
		Button buttonLeft = new Button("<-");
		buttonRight.setLayoutX(40);
		group.getChildren().add(globe.getSphere());
		primaryStage.setHeight(1920);
		primaryStage.setWidth(1080);
		primaryStage.setScene(scene);
		group.getChildren().add(buttonLeft);
		group.getChildren().add(buttonRight);
		PhongMaterial phongMaterial = new PhongMaterial();
	    phongMaterial.setDiffuseMap(new Image("http://media.rtl.fr/online/image/2015/1221/7780964243_jean-marie-le-pen-quittant-le-tribunal-de-nanterre-en-juin-2015.jpg",8192 / 2d,4092 / 2d,true,true));
	    globe.getSphere().setMaterial(phongMaterial);
	    buttonRight.setOnAction((ActionEvent e)->{globe.getSphere().setRotate(actPosX);actPosX+=20;});
	    buttonLeft.setOnAction((ActionEvent e)->{globe.getSphere().setRotate(actPosX);actPosX-=20;});
	    
	   
		primaryStage.show();
		
	}
	
	
	
	
	

}
