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
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;


public class View extends Application {

	final double WINDOW_HEIGHT = 1920.;
	final double WINDOW_WIDTH = 1080.;
	final double RECT_HEIGHT = WINDOW_HEIGHT;
	final double RECT_WIDTH = WINDOW_WIDTH/5;
	final double BUTTON_SIZE = RECT_WIDTH/6;
	final double BUTTON_Y = 150.;
	final double SPHERE_RADIUS = 400.; 
	final double SPHERE_X = RECT_WIDTH+(WINDOW_WIDTH-RECT_WIDTH)/2;
	final double SPHERE_Y = WINDOW_WIDTH/2;
	final double BUT_ZOOM_X =0.25*((RECT_WIDTH)/2-BUTTON_SIZE/2);
	final double BUT_PLAY_X =(RECT_WIDTH)/2-BUTTON_SIZE/2;
	final double BUT_PAUSE_X =1.75*((RECT_WIDTH)/2-BUTTON_SIZE/2);

	// 
	//Inutile ?
	public int actPosX = 0 ;
	public int zoomValue = 0;
	//
	//

	Group group = new Group();
	//
	//comprends p��
	Scene scene = new Scene(group,600,800);
	//
	//
	Globe globe = new Globe(SPHERE_RADIUS,SPHERE_X,SPHERE_Y);
	final Rectangle rectangle = new Rectangle(0,0,RECT_WIDTH,RECT_HEIGHT);
	Button pauseButton = new Button("||");
	Button playButton = new Button("|>");
	Button zoomButton = new Button("+");
	FractaleModele fracMod = new FractaleModele();
	ObservableList<String> options = 
			FXCollections.observableArrayList(
					"Mandelbrot",
					"Option 2"
					);
	Button[] buttons = {zoomButton,pauseButton,playButton};
	double[] buttonX = {BUT_ZOOM_X,BUT_PAUSE_X,BUT_PLAY_X};
	final ComboBox comboBox = new ComboBox(options);
	PhongMaterial phongMaterial = new PhongMaterial();




	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Fractus				|				Tricha-Jolliet 	S3A");
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setScene(scene);

		for(Button button : buttons)
		{
			button.setMinSize(BUTTON_SIZE,BUTTON_SIZE);
			button.setLayoutY(BUTTON_Y);
		}
		for(int num=0;num<buttonX.length;num++)
			buttons[num].setLayoutX(buttonX[num]);



		comboBox.setLayoutY(250);
		comboBox.setLayoutX(20);
		

		rectangle.setFill(Color.GREY);

		group.getChildren().add(globe.getSphere());
		group.getChildren().add(rectangle);
		group.getChildren().add(pauseButton);
		group.getChildren().add(playButton);
		group.getChildren().add(zoomButton);
		group.getChildren().add(comboBox);


		fracMod.createMandelBrot();
		FractaleControler fracControl = new FractaleControler(fracMod);
		fracControl.savePicture();


		phongMaterial.setDiffuseMap(new Image("file:Fractale.png",1200 ,500,false,false));
		//phongMaterial.setDiffuseColor(Color.WHITE);

		globe.getSphere().setMaterial(phongMaterial);
		globe.getSphere().setVisible(true);
		rotateAroundYAxis(globe.getSphere()).play();
		//zoom.setOnAction((ActionEvent e)->{globe.getSphere().setTranslate(zoomValue);zoomValue+=50;});

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
		pauseButton.setOnAction((ActionEvent e)->{rotate.pause();});
		playButton.setOnAction((ActionEvent e)-> {rotate.play();});
		zoomButton.setOnAction((ActionEvent e)->{fracMod.setZoom(25 );fracMod.getPicture();phongMaterial.setDiffuseMap(new Image("file:Fractale.png",8000 ,8000,false,false));});			

		return rotate;
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}



}
