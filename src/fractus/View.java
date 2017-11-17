package fractus;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View extends Application {

	public int actPosX = 0 ;
	public int zoomValue = 0;
	Group group = new Group();
	Scene scene = new Scene(group,600,800);
	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fractus");


		Globe globe = new Globe();
		Mandelbrot mandle = new Mandelbrot();
		mandle.getPicture();
		//Image img = new Image();

		Button buttonRight = new Button("->");
		Button buttonLeft = new Button("<-");
		Button zoom = new Button("+");
		buttonRight.setLayoutX(40);
		zoom.setLayoutX(80);
		group.getChildren().add(globe.getSphere());
		primaryStage.setHeight(1920);
		primaryStage.setWidth(1080);
		primaryStage.setScene(scene);
		group.getChildren().add(buttonLeft);
		group.getChildren().add(buttonRight);
		group.getChildren().add(zoom);
		PhongMaterial phongMaterial = new PhongMaterial();
		//phongMaterial.setDiffuseColor(Color.WHITE);
		phongMaterial.setDiffuseMap(new Image("file:Fractale.png",1500 ,1500,true,true));
		
		globe.getSphere().setMaterial(phongMaterial);
		
		buttonRight.setOnAction((ActionEvent e)->{globe.getSphere().setRotate(actPosX);actPosX+=20;});
		buttonLeft.setOnAction((ActionEvent e)->{globe.getSphere().setRotate(actPosX);actPosX-=20;});
		//zoom.setOnAction((ActionEvent e)->{globe.getSphere().setTranslate(zoomValue);zoomValue+=50;});

		globe.getSphere().setVisible(true);
		

		rotateAroundYAxis(globe.getSphere()).play();


		primaryStage.show();

		//drawFractale(300,200,200);
		
		
	}

	/*public void drawFractale(int x,int y, float rayon)
	{
		Circle circle = new Circle(x,y,rayon);

		circle.setFill(Color.WHITE);
		circle.setStroke(Color.BLACK);
		if(rayon > 300)
			rayon *=0.75f;

		group.getChildren().add(circle);
		if(group.getChildren().size() < 0)
			drawFractale(x+10,y+20,rayon);

	}*/

	private RotateTransition rotateAroundYAxis(Sphere s) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(25), s);
		rotate.setFromAngle(360);
		rotate.setToAngle(0);
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.setCycleCount(RotateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);

		return rotate;
	}




}
