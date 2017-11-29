package fractus;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		FractaleModele modele = new FractaleModele();
		FractaleControler fracControl =new FractaleControler(modele);
		FractaleView view = new FractaleView(fracControl, primaryStage);
		modele.addObserver(view);
		
	}
	public static void main(String[] args)
	{
		Application.launch(args);
	}

}
