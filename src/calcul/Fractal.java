package calcul;

import fractus.FractaleModele;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public abstract class Fractal {
	protected double zoom ;
	protected int max_it;
	protected int x;
	protected int y;
	protected double x1,x2;
	protected double y1,y2;
	protected Color currentColor;
	protected Color colorInside;		
	protected int xMax,yMax;
	protected int xMin,yMin;
	protected String fracType;
	protected FractaleModele frac ;
	protected int pic_x ;
	protected int pic_y;
	protected WritableImage image;
	
	public int getPic_x() {
		return pic_x;
	}

	public int getPic_y() {
		return pic_y;
	}



	private double zoomingValue=1.6;
	
	
	public void run(){
		System.out.println("not good");
		
		
	}
	public void reset(){
		
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public Color getColorInside() {
		return colorInside;
	}

	public String getFractalType() {
		return fracType;
	}

	public int getNbIteration() {
		return max_it;
	}
	
	public double getZoomingValue() {
		return zoomingValue;
	}

	public void setCurrentColor(Color value) {
		currentColor=value;	

	}

	public void setFractaleType(String type) {
		fracType=type;

	}

	public void setNbIteration(int nbiterations){
		max_it=nbiterations;

	}
	public void setZoom(int zoomX,int zoomY, double coeff) {

		zoom +=100;
		System.out.println(" ZOOMMM " + zoom);
		if(x1 < -0.5)
		{
			x1 +=0.1;
			x2+=0.01;			
		}
		if(y1 < -0.9)
		{
			y1 +=.01;
			y2 -=0.2;					
		}
		

	}



	public void setInsideColor(Color value) {
		colorInside=value;

	}
	public WritableImage getImage() {
		return image;
	}


}
