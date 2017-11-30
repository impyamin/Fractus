package fractus;



import java.util.Observable;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FractaleModele extends Observable {	

	private int resoY = 500; 
	private int resoX = 2*resoY ;
	private int zoom = 200 ;
	private int max_it = 50;
	private int x,y;
	private double x1 = -2.1;
	private double x2 = 3.0;
	private double y1 = -1.2;
	private double y2 = 1.2;
	private Color currentColor = Color.GREEN;
	private Color colorInside = Color.BLACK;	
	private int xOffset =10;
	private int yOffset = 10;
	private double zoomingValue=1.6;
	

	private WritableImage image = new WritableImage(resoX,resoY);

	private String fractalType="Mandelbrot";


	public FractaleModele() {
	}	
	
	public void createMandelBrot() {

		//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		long debut = System.currentTimeMillis();
		 System.out.println("createMandelbrot");
		 
		/* new Thread(new Runnable() {
			    @Override public void run() {*/

		for( x = 0 ; x < resoX;x++)
		{
			for( y = 0; y < resoY ; y++) 
			{
				double c_r = (x+xOffset)/((double)zoom)*(1+Math.PI/10)+x1 ;
				double c_i = (y+yOffset)/((double)zoom)+y1 ;
				double z_r = 0;
				double z_i = 0;
				double i = 0;   

				do
				{
					double tmp = z_r ;
					z_r = Math.pow(z_r,2) - Math.pow(z_i,2) + c_r ;
					z_i= 2*z_i*tmp + c_i;
					++i ;
				}
				while((Math.pow(z_r,2)) + (Math.pow(z_i,2)) < 4 && i <max_it);


				if(i == max_it) 
					image.getPixelWriter().setColor(x,y,colorInside);

				else
				{
					Color newColor = new Color(i*(currentColor.getRed())/max_it,i*(currentColor.getGreen())/max_it,i*(currentColor.getBlue())/max_it,1);	
					
					image.getPixelWriter().setColor(x, y,newColor);

				}  			   			
			}
		}
		        /*}
		    }
		).start();*/
		System.out.println(System.currentTimeMillis()-debut);

	}


	public void createOtherFractale() {
		for(int x = 0 ; x < 1020;x++)
		{
			for(int y = 0; y < 480 ; y++) 
			{
				image.getPixelWriter().setColor(x,y,colorInside);
			}
		}
	}


	public double getResolutionY() {
		return resoY;
	}

	public double getResolutionX() {
		return resoX;
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public Color getColorInside() {
		return colorInside;
	}

	public String getFractalType() {
		return fractalType;
	}

	public int getNbIteration() {
		return max_it;
	}
	
	public double getZoomingValue() {
		return zoomingValue;
	}
	
	public WritableImage getImage() {
		return image;

	}

	public  void setResolutionX(double reso) {
		this.resoX *= reso;
		setChanged();
		notifyObservers();
	}

	public  void setResolutionY(double reso) {
		this.resoY *= reso;
		setChanged();
		notifyObservers();
	}   

	public void setCurrentColor(Color value) {
		currentColor=value;	
		setChanged();
		notifyObservers();
	}

	public void setFractaleType(String type) {
		fractalType=type;
		setChanged();
		notifyObservers();
	}

	public void setNbIteration(int nbiterations){
		max_it=nbiterations;
		setChanged();
		notifyObservers();
	}

	public void setZoom(int zoomX,int zoomY, double coeff) {
		zoom *=coeff ;
		xOffset = zoomX;
		yOffset=zoomY;
		//PixelReader pixrd = image.getPixelReader();
		//image = new WritableImage(pixrd,10,10,resoX,resoY);
		setChanged();
		notifyObservers();
	}

	public void setInsideColor(Color value) {
		colorInside=value;
		setChanged();
		notifyObservers();
	}


	public void reset() {
		resoY = 480; 
		zoom = 200 ;
		max_it = 50;
		x1 = -2.1;
		y1 = -1.2;
		y2 = 1.2;
		currentColor = Color.GREEN;
		colorInside = Color.BLACK;	
		xOffset =10;
		yOffset = 10;		
		setChanged();
		notifyObservers();
	}







}
