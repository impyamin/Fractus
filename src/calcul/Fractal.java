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
	protected int x;
	protected int y;
	protected double x1,x2;
	protected double y1,y2;
	protected Color currentColor;
	protected Color colorInside;		
	protected int xMax,yMax;
	protected int xMin,yMin;
	protected FracType fracType;
	protected FractaleModele frac ;
	protected int pic_x=2000;
	protected int pic_y=pic_x/2;
	protected WritableImage image;

	public Color mixColors(Color color1, Color color2, double percent){

		double redPart = color1.getRed() + (color2.getRed() - color1.getRed())*percent;
		double greenPart = color1.getGreen() + (color2.getGreen()-color1.getGreen())*percent;
		double bluePart = color1.getBlue() + (color2.getBlue()-color1.getBlue())*percent;

		return new Color(redPart, greenPart, bluePart,1);
	}

	public int getPic_x() {
		return pic_x;
	}

	public int getPic_y() {
		return pic_y;
	}
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

	public FracType getFractalType() {
		return fracType;
	}

	public int getNbIteration() {
		return max_it;
	}

	public double getZoomingValue() {
		return zoom;
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
	public void setZoom(String x) {		
		switch(x)
		{
		case "zoom" :
			zoom +=200;
			System.out.println("ZOOM" + zoom);
			System.out.println(" x1 FIRST STEP " + x1);
			System.out.println(" y1 FIRST STEP " + y1);
			System.out.println(" y2 FIRST STEP " + y2);
			System.out.println(" x2 FIRST STEP " + x2);		
			if(x1<-0.5)
			x1 +=0.1;
			
			if(y1<-0.9)
			y1 +=0.1;			
			break;			
		case "unzoom":
			zoom -=100;	
			x1 +=0.1;
			x2 -=0.1;	
			y1 +=0.1;
			y2 -=0.1;
			break ;
		}					
	}

	public void setInsideColor(Color value) {
		colorInside=value;

	}
	public WritableImage getImage() {
		return image;
	}


}
