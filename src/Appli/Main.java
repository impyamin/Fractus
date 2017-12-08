package Appli;

import MVC.FractaleControler;
import MVC.FractaleModele;
import MVC.FractaleView;
import javafx.application.Application;
import javafx.stage.Stage;
/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Création du Modele-Vue-Controleur
		FractaleModele modele = new FractaleModele();
		FractaleControler fracControl =new FractaleControler(modele);
		FractaleView view = new FractaleView(fracControl, primaryStage);
		//Ajout de l'observer
		modele.addObserver(view);
		
	}
	public static void main(String[] args)
	{
		Application.launch(args);
	}

}
