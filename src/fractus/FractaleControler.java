package fractus;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FractaleControler {
	FractaleModele fracModele ;
	public FractaleControler(FractaleModele modele) {	
		fracModele = modele ;


	}
	public void setZoom(int zoomX,int zoomY)
	{
		fracModele.setZoom(zoomX,zoomY);
	}


	public double getResoY() {
		return fracModele.getResolutionY();
	}
	public double getResoX() {
		return fracModele.getResolutionX();
	}
	public void setNbIteration(Integer it) {
		fracModele.setNbIteration(it);		
	}

	public void setFractaleType(String type) {
		fracModele.setFractaleType(type);
	}

	public void setInsideColor(Color value) {
		fracModele.setInsideColor(value);
	}
	public void setCurrentColor(Color value) {
		fracModele.setCurrentColor(value);		
		
	}
	public WritableImage getImage() {
		switch (fracModele.getFractalType()) {
		case "Mandelbrot":
			fracModele.createMandelBrot();
			break;
		case "Option 2" :
			fracModele.createOtherFractale();
			break;
		default:
			break;
		}
		return fracModele.getImage();
	}
	public void reset() {
		fracModele.reset();
	}
	public Color getCurrentColor() {
		return fracModele.getCurrentColor();
	}
	public Color getColorInside() {
		return fracModele.getColorInside();
	}
	public int getNbIteration() {
		return fracModele.getNbIteration();
	}

	
	

}
