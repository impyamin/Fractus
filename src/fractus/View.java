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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	final double ELEMENT_SIZE=20.;
	final double ELEMENT_X=20.;
	final double FRACTALE_TYPE_X=250.;
	final double NB_ITERATION_X=370.;
	final double COLOR_PICKER_X=450;
	final double SHIFT=10.;
	
	

	final FractaleModele fracMod = new FractaleModele();
	final FractaleControler fracControl = new FractaleControler(fracMod);
	
	Group group = new Group();
	Scene scene = new Scene(group,600,800);
	Globe globe = new Globe(SPHERE_RADIUS,SPHERE_X,SPHERE_Y,scene);
	final Rectangle rectangle = new Rectangle(0,0,RECT_WIDTH,RECT_HEIGHT);
	Button pauseButton = new Button("||");
	Button playButton = new Button("|>");
	Button zoomButton = new Button("+");
	ObservableList<String> options = 
			FXCollections.observableArrayList(
					"Mandelbrot",
					"Option 2"
					);
	Button[] buttons = {zoomButton,pauseButton,playButton};
	double[] buttonX = {BUT_ZOOM_X,BUT_PAUSE_X,BUT_PLAY_X};
	final ComboBox fractaleType = new ComboBox(options);
	PhongMaterial phongMaterial = new PhongMaterial();
	
	final Label textIterWarningLabel = new Label();
	final Label nbIterationLabel = new Label();
	final Label fractaleTypeLabel = new Label();
	final Label colorLabel = new Label();
	TextField nbIteration = new TextField();
	ColorPicker colorPicker = new ColorPicker();
	
	

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

		fractaleTypeLabel.setText("Fractale Type :");
		fractaleTypeLabel.setLayoutY(FRACTALE_TYPE_X-ELEMENT_SIZE);
		fractaleTypeLabel.setLayoutX(ELEMENT_X);
		fractaleType.setLayoutY(FRACTALE_TYPE_X);
		fractaleType.setLayoutX(ELEMENT_X);
		fractaleType.setValue(options.toArray()[0]);

		nbIterationLabel.setText("Iteration number :");
		nbIterationLabel.setLayoutX(ELEMENT_X);
		nbIterationLabel.setLayoutY(NB_ITERATION_X-ELEMENT_SIZE);
		nbIteration.setLayoutX(ELEMENT_X);
		nbIteration.setLayoutY(NB_ITERATION_X);
		nbIteration.setText("50");
		textIterWarningLabel.setLayoutX(ELEMENT_X);
		textIterWarningLabel.setLayoutY(NB_ITERATION_X+ELEMENT_SIZE+SHIFT);
		
		colorLabel.setText("Color :");
		colorLabel.setLayoutX(ELEMENT_X);
		colorLabel.setLayoutY(COLOR_PICKER_X-ELEMENT_SIZE);		
		colorPicker.setLayoutX(ELEMENT_X);
		colorPicker.setLayoutY(COLOR_PICKER_X);
		colorPicker.setValue(Color.GREEN);


		rectangle.setFill(Color.GREY);

		group.getChildren().add(globe.getSphere());
		group.getChildren().add(rectangle);
		group.getChildren().add(pauseButton);
		group.getChildren().add(playButton);
		group.getChildren().add(zoomButton);
		group.getChildren().add(fractaleType);
		group.getChildren().add(nbIterationLabel);
		group.getChildren().add(nbIteration);
		group.getChildren().add(textIterWarningLabel);
		group.getChildren().add(fractaleTypeLabel);
		group.getChildren().add(colorPicker);
		group.getChildren().add(colorLabel); 


		fracControl.savePicture();


		phongMaterial.setDiffuseMap(new Image("file:Fractale.png",fracControl.getResoX() ,fracControl.getResoY(),false,false));


		globe.getSphere().setMaterial(phongMaterial);
		rotateAroundYAxis(globe.getSphere()).play();
		globe.handleRotationEvents();
		
		actionEventManagement();

		primaryStage.show();

	}



	private RotateTransition rotateAroundYAxis(Sphere s) {
		RotateTransition rotate = new RotateTransition(Duration.seconds(25), s);
		rotate.setFromAngle(360);
		rotate.setToAngle(0);
		rotate.setAxis(Rotate.Y_AXIS);
		rotate.setCycleCount(RotateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		pauseButton.setOnAction((ActionEvent e)->{rotate.pause();});
		playButton.setOnAction((ActionEvent e)-> {rotate.play();});

		return rotate;
	}
	
	private void actionEventManagement(){
		//Iteration's number 
		nbIteration.setOnAction((ActionEvent e) -> {
			try{
				Integer it = Integer.parseInt(nbIteration.getText());
				textIterWarningLabel.setText(null);	     
				fracControl.setNbIteration(it);
				fracControl.savePicture();
				phongMaterial.setDiffuseMap(new Image("file:Fractale.png",8000 ,8000,false,false));

			}catch (NumberFormatException ex){
				textIterWarningLabel.setText("Not an integer!");
			}
		});
		//Color
	    colorPicker.setOnAction((ActionEvent event)->{
            fracControl.setCurrentColor(colorPicker.getValue().getRed(),colorPicker.getValue().getGreen(),colorPicker.getValue().getBlue());
    		fracControl.savePicture();
    		phongMaterial.setDiffuseMap(new Image("file:Fractale.png",8000 ,8000,false,false));
            });
	    //zoom
		zoomButton.setOnAction((ActionEvent e)->{fracControl.setZoom(25);fracControl.savePicture();phongMaterial.setDiffuseMap(new Image("file:Fractale.png",0 ,0,false,false));});			
		//TO DO : FRACTALE TYPE
	}

	public static void main(String[] args)
	{
		Application.launch(args);
	}
	
}
