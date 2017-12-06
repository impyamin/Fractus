package MVC;

import java.util.Observable;

import calcul.Buddhabrot;
import calcul.Fractal;
import calcul.Julia;
import calcul.Mandelbrot;
import calcul.Multithreading;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FractaleModele extends Observable {	


	Fractal fractal = new Mandelbrot();



	private FracType fractalType=fractal.getFractalType();

	public enum FracType {
		Mandelbrot,Julia,Buddhabrot
	}

	public FractaleModele() {
	}	

	public void createFractale() {

		//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		long debut = System.currentTimeMillis();


		Multithreading t1 = new Multithreading(0,0,500,1000,fractal);
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		Multithreading t2 = new Multithreading(0,0,1000,1000,fractal);
		try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		Multithreading t3 = new Multithreading(0,0,1500,750,fractal);
		try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Multithreading t4 = new Multithreading(0,0,2000,1000,fractal);
		try {
			t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}



		System.out.println(System.currentTimeMillis()-debut);

	}

	public Color getCurrentColor() {
		return fractal.getCurrentColor();
	}

	public Color getColorInside() {
		return fractal.getColorInside();
	}

	public FracType getFractalType() {
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

	public void setCurrentColor(Color value) {
		fractal.setCurrentColor(value);	
		setChanged();
		notifyObservers();
	}

	public void setFractaleType(FracType type)  {
		fractalType=type;
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

	public void setNewFractal(FracType type) {
		setFractaleType(type);
		switch (type) {
		case Mandelbrot:
			fractal = new Mandelbrot();
			break;
		case Julia :
			fractal = new Julia();
			break;
		case Buddhabrot:
			fractal = new Buddhabrot();
			break;
		}
		setChanged();
		notifyObservers();
	}









}
