package calcul;

import MVC.FractaleModele;
import MVC.FractaleModele.FracType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public abstract class Fractal {
	protected double zoom ;
	protected int max_it;
	protected int x,y;
	protected double x1,y1,x2,y2;
	protected Color currentColor;
	protected Color colorInside;		
	protected int xMax,yMax,xMin,yMin;
	protected FracType fracType;
	protected FractaleModele frac ;
	protected WritableImage image;
	protected int pic_x=2000;
	protected int pic_y=pic_x/2;

	//est override dans les classes héritantes
	public void run(){
	}

	//est override dans les classes héritantes
	public void setup(){		
	}
	
	public void reset(){
		setup();
	}
	public int getPic_x() {
		return pic_x;
	}

	public int getPic_y() {
		return pic_y;
	}

	public Color getCurrentColor() { 
		return currentColor;
	}

	public Color getColorInside() {
		return colorInside;
	}

	public FracType getFractalType() {
		return fracType;
	}

	public int getNbIteration() {
		return max_it;
	}

	public double getZoomingValue() {
		return zoom;
	}
	public WritableImage getImage() {
		return image;
	}

	public void setCurrentColor(Color value) {
		currentColor=value;	
	}

	public void setFractaleType(FracType type) {
		fracType=type;
	}

	public void setNbIteration(int nbiterations){
		max_it=nbiterations;

	}
	public void setInsideColor(Color value) {
		colorInside=value;
	}
	public void setZoom(String x) {		
		switch(x)
		{
		case "zoom" :
			zoom +=200;
			if(x1<-0.5)
			x1 +=0.1;
			
			if(y1<-0.9)
			y1 +=0.1;			

			break;			
		case "unzoom":
			zoom -=200;	
			x1 -=0.1;				
			y1 -=0.1;			
			break ;
		}					
	}




}
