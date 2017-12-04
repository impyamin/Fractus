package fractus;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class FractaleControler {
	FractaleModele fracModele ;
	public FractaleControler(FractaleModele modele) {	
		fracModele = modele ;


	}
	public void setZoomPlus(int zoomX,int zoomY)
	{
		fracModele.setZoom(zoomX,zoomY,fracModele.getZoomingValue());
	}

	public void setZoomMinus(int zoomX, int zoomY) {
		fracModele.setZoom(zoomX,zoomY,1/fracModele.getZoomingValue());
	}

	public void setNbIteration(Integer it) {
		fracModele.setNbIteration(it);		
	}

	public void setFractaleType(String type) {
		fracModele.setNewFractal(type);
		}
	

	public void setInsideColor(Color value) {
		fracModele.setInsideColor(value);
	}
	public void setCurrentColor(Color value) {
		fracModele.setCurrentColor(value);		

	}
	public WritableImage getImage() {

		fracModele.createFractale();
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
