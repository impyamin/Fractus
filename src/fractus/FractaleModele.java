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
	
	public void createFractale() {

		//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		long debut = System.currentTimeMillis();
		 System.out.println("createFractale");
		 

		 Multithreading t1 = new Multithreading(0,0,250,500,this);
		 try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		 Multithreading t2 = new Multithreading(250,0,500,500,this);
		 try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
		 Multithreading t3 = new Multithreading(500,0,750,500,this);
		 try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		 Multithreading t4 = new Multithreading(750,0,1000,500,this);
		 try {
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		
	
		System.out.println(System.currentTimeMillis()-debut);

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
		setxOffset(zoomX);
		setyOffset(zoomY);
		//PixelReader pixrd = image.getPixelReader();
		//image = new WritableImage(pixrd,10,10,resoX,resoY);
		setChanged();
		notifyObservers();
	}
	public double getZoom() {
		return zoom ;
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
		setxOffset(10);
		setyOffset(10);		
		setChanged();
		notifyObservers();
	}

	public int getxOffset() {
		return xOffset;
	}

	public void setxOffset(int xOffset) {
		this.xOffset = xOffset;
	}

	public int getyOffset() {
		return yOffset;
	}

	public void setyOffset(int yOffset) {
		this.yOffset = yOffset;
	}
	







}
