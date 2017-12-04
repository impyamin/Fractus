package fractus;



import java.util.Observable;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FractaleModele extends Observable {	


	Fractal fractal = new Mandelbrot();



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

	public Color getCurrentColor() {
		return fractal.getCurrentColor();
	}

	public Color getColorInside() {
		return fractal.getColorInside();
	}

	public String getFractalType() {
		return fractalType;
	}

	public int getNbIteration() {
		return fractal.getNbIteration();
	}

	public double getZoomingValue() {
		return fractal.getZoomingValue();
	}

	public WritableImage getImage() {
		return fractal.getImage();

	}
	public double getZoom() {
		return fractal.getZoom();
	}


	public void setCurrentColor(Color value) {
		fractal.setCurrentColor(value);	
		setChanged();
		notifyObservers();
	}

	public void setFractaleType(String type) {
		fractalType=type;
		setChanged();
		notifyObservers();
	}

	public void setNbIteration(int nbiterations){
		fractal.setNbIteration(nbiterations);;
		setChanged();
		notifyObservers();
	}

	public void setZoom(int zoomX,int zoomY, double coeff) {
		fractal.setZoom(zoomX, zoomY, coeff);
		setChanged();
		notifyObservers();
	}



	public void setInsideColor(Color value) {
		fractal.setInsideColor(value);
		setChanged();
		notifyObservers();
	}


	public void reset() {
		fractal.reset();
		setChanged();
		notifyObservers();
	}



	public Fractal getFractal() {
		return fractal;
	}









}
