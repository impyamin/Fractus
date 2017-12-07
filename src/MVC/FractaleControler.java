package MVC;

import MVC.FractaleModele.FracType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class FractaleControler {
	FractaleModele fracModele ;
	public FractaleControler(FractaleModele modele) {	
		fracModele = modele ;


	}
	public void setZoomPlus(String act)
	{
		fracModele.setZoom(act);
	}

	
	public void setNbIteration(Integer it) {
		fracModele.setNbIteration(it);		
	}

	public void setFractaleType(FracType type) {
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
