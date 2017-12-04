package fractus;

import java.util.Observable;

import calcul.Fractal;
import calcul.Julia;
import calcul.Mandelbrot;
import calcul.Multithreading;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class FractaleModele extends Observable {	


	Fractal fractal = new Mandelbrot();



	private String fractalType=fractal.getFractalType();


	public FractaleModele() {
	}	

	public void createFractale() {

		//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		long debut = System.currentTimeMillis();


		Multithreading t1 = new Multithreading(fractal.getPic_x(),fractal.getPic_y(),fractal);
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	/*	 Multithreading t2 = new Multithreading(fractal.getPic_x()/4,fractal.getPic_y(),this,fractalType);
		 try {
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		 Multithreading t3 = new Multithreading(2*fractal.getPic_x()/4,fractal.getPic_y(),this,fractalType);
		 try {
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		 Multithreading t4 = new Multithreading(3*fractal.getPic_x()/4,fractal.getPic_y(),this,fractalType);
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

	public void setCurrentColor(Color value) {
		fractal.setCurrentColor(value);	
		setChanged();
		notifyObservers();
	}

	public void setFractaleType(String type)  {
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

	public void setNewFractal(String type) {
		setFractaleType(type);
		switch (type) {
		case "Mandelbrot":
			fractal = new Mandelbrot();
			break;
		case "Julia" :
			fractal = new Julia();
			break;
		case "Buddhabrot":
			fractal = new Buddhabrot();
			break;
		}
		setChanged();
		notifyObservers();
	}









}
