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
	private double x1 = -2.1;
	private double x2 = 3;
	private double y1 = -1.2;
	private double y2 = 1.2;
	private Color currentColor = Color.GREEN;
	private Color colorInside = Color.BLACK;	
	private double pic_x = (x2 -x1) * zoom;
	private double pic_y = (y2-y1)  *zoom ;

	private double zoomingValue=1.6;
	

	private WritableImage image = new WritableImage((int)pic_x+1,(int)pic_y);

	private String fractalType="Mandelbrot";


	public FractaleModele() {
	}	
	
	public void createFractale() {

		//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		long debut = System.currentTimeMillis();
		 System.out.println("createFractale");
		 

		 Multithreading t1 = new Multithreading(0,0,1000,500,this);
	 try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
				
		/* Multithreading t2 = new Multithreading(250,0,500,500,this);
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
		}*/
		 
		
	
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
			
		zoom+=50 ;		
		if(x1 < -0.5)
			x1 +=0.1;
		//x1-=0.01;
		if(y1 < -0.9)
		y1 +=.01;
		y2 -=0.2;		
		
		System.out.println("y2 ;   "+ y2);
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
		setY1(-1.2);
		setY2(1.2);
		currentColor = Color.GREEN;
		colorInside = Color.BLACK;			
		setChanged();
		notifyObservers();
	}

	public double getX1() {
		return x1;
	}
	
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY1() {
		return y1;
	}

	public void setY1(double y1) {
		this.y1 = y1;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}
	
	public double getPic_x() {
		return pic_x;
	}
	public double getPic_y() {
		return pic_y;
	}

	







}
