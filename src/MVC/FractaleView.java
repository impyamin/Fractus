package MVC;

import java.util.Observable;
import java.util.Observer;

import Appli.Globe;
import MVC.FractaleModele.FracType;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class FractaleView implements Observer {
	//déclaration des variables gobales 
	private final double WINDOW_HEIGHT = 1920.;
	private final double WINDOW_WIDTH = 1080.;
	private final double RECT_HEIGHT = WINDOW_HEIGHT;
	private final double RECT_WIDTH = WINDOW_WIDTH/5;
	private final double BUTTON_SIZE = RECT_WIDTH/6;
	private final double BUTTON_Y = 100.;
	private final double SPHERE_RADIUS = 400.; 
	private final double SPHERE_X = RECT_WIDTH+(WINDOW_WIDTH-RECT_WIDTH)/2;
	private final double SPHERE_Y = WINDOW_WIDTH/2;
	private final double BUT_ZOOM_PLUS_X =0.70*((RECT_WIDTH)/2-BUTTON_SIZE/2);
	private final double BUT_ZOOM_MINUS_X = 0.15*((RECT_WIDTH)/2-BUTTON_SIZE/2);
	private final double BUT_PLAY_X =1.20*(RECT_WIDTH)/2-BUTTON_SIZE/2;
	private final double BUT_PAUSE_X =1.80*((RECT_WIDTH)/2-BUTTON_SIZE/2);
	private final double ELEMENT_HEIGHT=20.;
	private final double ELEMENT_X=20.;
	private final double FRACTALE_TYPE_Y=190.;
	private final double NB_ITERATION_Y=270.;
	private final double COLOR_PICKER_Y=350;
	private final double COLOR_PICKER_HEIGHT = 285.;
	private final double SHIFT=10.;
	private double mousePosX,mousePosY = 0;


	FractaleControler fracControl;
	Globe globe;

	//Création de la scène et de son contenu ( délacration des boutons, des textes, et des autres élements tel que la couleur ou le menu défilant)
	final Group group = new Group();
	final Scene scene = new Scene(group,600,800);
	final Rectangle rectangle = new Rectangle(0,0,RECT_WIDTH,RECT_HEIGHT);
	Button pauseButton = new Button("||");
	Button playButton = new Button("|>");
	Button zoomButtonPlus = new Button("+");
	Button zoomButtonMinus = new Button("-");
	Button resetButton = new Button("Reset");
	Button[] buttons = {zoomButtonPlus,zoomButtonMinus,pauseButton,playButton};
	double[] buttonX = {BUT_ZOOM_PLUS_X,BUT_ZOOM_MINUS_X,BUT_PAUSE_X,BUT_PLAY_X};
	ObservableList<FracType> options = 
			FXCollections.observableArrayList(
					FracType.Mandelbrot,
					FracType.Julia
					);
	final ComboBox<FracType> fractaleType = new ComboBox<FracType>(options);
	PhongMaterial phongMaterial = new PhongMaterial();
	final Label textIterWarningLabel = new Label();
	final Label nbIterationLabel = new Label();
	final Label fractaleTypeLabel = new Label("Fractale Type :");
	final Label colorLabel = new Label("Color :");
	final Label colorInsideLabel = new Label("Color inside :");
	TextField nbIteration = new TextField("Iteration number :");
	ColorPicker colorPicker = new ColorPicker();
	ColorPicker colorInsidePicker = new ColorPicker();


	public FractaleView(FractaleControler fracControl, Stage primaryStage) {
		globe = new Globe(SPHERE_RADIUS,SPHERE_X,SPHERE_Y);

		this.fracControl=fracControl;

		primaryStage.setTitle("Fractus				|				Tricha-Jolliet 	S3A");
		primaryStage.setHeight(WINDOW_HEIGHT);
		primaryStage.setWidth(WINDOW_WIDTH);
		primaryStage.setScene(scene);

		//Positionnement des éléments
		for(Button button : buttons)
		{
			button.setMinSize(BUTTON_SIZE,BUTTON_SIZE);
			button.setMaxSize(BUTTON_SIZE, BUTTON_SIZE);
			button.setLayoutY(BUTTON_Y);
		}
		for(int num=0;num<buttonX.length;num++)
			buttons[num].setLayoutX(buttonX[num]);

		fractaleTypeLabel.setLayoutY(FRACTALE_TYPE_Y-ELEMENT_HEIGHT);
		fractaleTypeLabel.setLayoutX(ELEMENT_X);
		fractaleType.setLayoutY(FRACTALE_TYPE_Y);
		fractaleType.setLayoutX(ELEMENT_X);
		fractaleType.setValue((FracType) options.toArray()[0]);
		nbIterationLabel.setLayoutX(ELEMENT_X);
		nbIterationLabel.setLayoutY(NB_ITERATION_Y-ELEMENT_HEIGHT);
		nbIteration.setLayoutX(ELEMENT_X);
		nbIteration.setLayoutY(NB_ITERATION_Y);
		nbIteration.setText(String.valueOf(fracControl.getNbIteration()));
		textIterWarningLabel.setLayoutX(ELEMENT_X);
		textIterWarningLabel.setLayoutY(NB_ITERATION_Y+ELEMENT_HEIGHT+SHIFT);

		colorLabel.setLayoutX(ELEMENT_X);
		colorLabel.setLayoutY(COLOR_PICKER_Y-ELEMENT_HEIGHT);		
		colorPicker.setLayoutX(ELEMENT_X);
		colorPicker.setLayoutY(COLOR_PICKER_Y);
		colorPicker.setValue(fracControl.getCurrentColor());


		colorInsideLabel.setLayoutX(ELEMENT_X);
		colorInsideLabel.setLayoutY(COLOR_PICKER_Y+COLOR_PICKER_HEIGHT-ELEMENT_HEIGHT);		
		colorInsidePicker.setLayoutX(ELEMENT_X);
		colorInsidePicker.setLayoutY(COLOR_PICKER_Y+COLOR_PICKER_HEIGHT);
		colorInsidePicker.setValue(fracControl.getColorInside());

		resetButton.setLayoutX(ELEMENT_X);
		resetButton.setLayoutY(COLOR_PICKER_Y+2*COLOR_PICKER_HEIGHT+4*SHIFT);


		rectangle.setFill(Color.GREY);

		//ajouts au groupe
		group.getChildren().add(globe.getSphere());
		group.getChildren().add(rectangle);
		group.getChildren().add(pauseButton);
		group.getChildren().add(playButton);
		group.getChildren().add(zoomButtonPlus);
		group.getChildren().add(zoomButtonMinus);
		group.getChildren().add(fractaleType);
		group.getChildren().add(nbIterationLabel);
		group.getChildren().add(nbIteration);
		group.getChildren().add(textIterWarningLabel);
		group.getChildren().add(fractaleTypeLabel);
		group.getChildren().add(colorPicker);
		group.getChildren().add(colorLabel); 
		group.getChildren().add(colorInsidePicker);
		group.getChildren().add(colorInsideLabel); 
		group.getChildren().add(resetButton);

		//application de la fractale
		phongMaterial.setDiffuseMap(fracControl.getImage());
		globe.getSphere().setMaterial(phongMaterial);

		//rotation de base 
		rotateAroundYAxis(globe.getSphere()).play();
		
		//gestion des évenements
		actionEventManagement();
		
		primaryStage.show();
	}


	//SOURCE : https://gist.github.com/jewelsea/7206287
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

			}catch (NumberFormatException ex){
				textIterWarningLabel.setText("Not an integer!");
			}
		});
		//color
		colorPicker.setOnAction((ActionEvent event)->{
			fracControl.setCurrentColor(colorPicker.getValue());
		});
		colorInsidePicker.setOnAction((ActionEvent event)->{
			fracControl.setInsideColor(colorInsidePicker.getValue());
		});
		//zoom
		zoomButtonPlus.setOnAction((ActionEvent e)->{
			fracControl.setZoom("zoom");
		});	
		zoomButtonMinus.setOnAction((ActionEvent e)->{
			fracControl.setZoom("unzoom");
		});
		// fractal type
		fractaleType.setOnAction((ActionEvent e)->{
			fracControl.setFractaleType(fractaleType.getValue());
		});
		// reset
		resetButton.setOnAction((ActionEvent e )->{
			fracControl.reset();
		});
		//click sur la souris
		scene.setOnMousePressed((MouseEvent me) -> {
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});

		//mouvement de la souris
		scene.setOnMouseDragged((MouseEvent me) -> {
			double dx = (mousePosX - me.getSceneX()) ;
			double dy = (mousePosY - me.getSceneY());

			if (me.isPrimaryButtonDown() && globe.contains(me.getSceneX(), me.getSceneY()))
			{
				globe.getRotateZ().setAngle(globe.getRotateZ().getAngle() - 
						(dy / globe.getSphere().getRadius()*4 * -360) * (Math.PI / 180));
				globe.getRotateY().setAngle(globe.getRotateY().getAngle() - 
						(dx / globe.getSphere().getRadius()*4 * -360) * (Math.PI / 180));
			}
			mousePosX = me.getSceneX();
			mousePosY = me.getSceneY();
		});
		
		//
		globe.getSphere().setOnScroll((ScrollEvent event)-> {			
			if(event.getDeltaY() < 0)
				fracControl.setZoom("unzoom");				
			else
				fracControl.setZoom("zoom");			
		});
	}


	//Mise à jour après réception de la notification venant de l'observable 
	@Override
	public void update(Observable arg0, Object arg1) {
		phongMaterial.setDiffuseMap(fracControl.getImage());
		colorInsidePicker.setValue(fracControl.getColorInside());
		colorPicker.setValue(fracControl.getCurrentColor());
		nbIteration.setText(String.valueOf(fracControl.getNbIteration()));
	}




}
