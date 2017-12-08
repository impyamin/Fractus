package MVC;

import java.util.Observable;

import calcul.Buddhabrot;
import calcul.Fractal;
import calcul.Julia;
import calcul.Mandelbrot;
import calcul.Multithreading;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class FractaleModele extends Observable {	
	Fractal fractal = new Mandelbrot();
	private FracType fractalType=fractal.getFractalType();
	public enum FracType {
		Mandelbrot,Julia,Buddhabrot
	}

	public void createFractale() {
		//SOURCE : https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
		Multithreading t1 = new Multithreading(0,0,2000,1000,fractal);
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}	
	//action privée donc pas de setChanged() et de notifyObserver()
	private void setFractaleType(FracType type)  {
		fractalType=type;
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
	
	public Fractal getFractal() {
		return fractal;
	}


	public void setCurrentColor(Color value) {
		fractal.setCurrentColor(value);	
		//Notification pour que l'update de l'observer soit déclenché
		setChanged();
		notifyObservers();
	}

	public void setNbIteration(int nbiterations){
		fractal.setNbIteration(nbiterations);;
		setChanged();
		notifyObservers();
	}
	public void setZoom(String act) {
		fractal.setZoom(act);
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
