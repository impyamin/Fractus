package calcul;

import fractus.FractaleModele;

public class Multithreading extends Thread {


	FractaleModele fracMod;
	Fractal fractal;
	String fracType;

	public Multithreading(int x,int y,Fractal frac){

		fractal=frac;
		fracType=fractal.getFractalType();
		start();
	}	

	public void run() {
		fractal.run();
		}




}

