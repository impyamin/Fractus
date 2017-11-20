package fractus;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
	Button PauseButton = new Button("||");
	Button playButton = new Button("|>");
	Button zoomButton = new Button("+");
	Globe globe = new Globe();
	Group group = new Group();
	Scene scene = new Scene(group,600,800);
	FractaleModele fracMod = new FractaleModele();

	public static void main(String[] args)
	{
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fractus");


		fracMod.createMandelBrot();
		FractaleControler fracControl = new FractaleControler(fracMod);
		fracControl.savePicture();
		zoomButton.setLayoutX(80);
		

		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Mandelbrot",
			        "Option 2"
			    );
		final ComboBox comboBox = new ComboBox(options);


		PauseButton.setLayoutX(40);
		comboBox.setLayoutY(30);

		group.getChildren().add(globe.getSphere());
		primaryStage.setHeight(1920);
		primaryStage.setWidth(1080);
		primaryStage.setScene(scene);
		
		group.getChildren().add(playButton);
		group.getChildren().add(PauseButton);
		group.getChildren().add(zoomButton);
		group.getChildren().add(comboBox);

		PhongMaterial phongMaterial = new PhongMaterial();
		//phongMaterial.setDiffuseColor(Color.WHITE);
		phongMaterial.setDiffuseMap(new Image("file:Fractale.png",1200 ,500,false,false));
		
		globe.getSphere().setMaterial(phongMaterial);
		
		
		//zoom.setOnAction((ActionEvent e)->{globe.getSphere().setTranslate(zoomValue);zoomValue+=50;});

		globe.getSphere().setVisible(true);
		

		rotateAroundYAxis(globe.getSphere()).play();


		primaryStage.show();

		
		
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
		PauseButton.setOnAction((ActionEvent e)->{rotate.pause();});
		playButton.setOnAction((ActionEvent e)-> {rotate.play();});
		zoomButton.setOnAction((ActionEvent e)->{fracMod.setZoom(250);});			

		return rotate;
	}




}
