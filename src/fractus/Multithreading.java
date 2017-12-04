package fractus;



public class Multithreading extends Thread {


	FractaleModele fracMod;
	Fractal fractal;
	String fracType;

	Multithreading(int x,int y,int x2,int y2,FractaleModele mod){
		fracMod = mod;
		fractal=fracMod.getFractal();
		fracType=fractal.getFractalType();
		start();
	}	

	public void run() {
		System.out.println("run in multithreading");
		System.out.println(fracType);
		fractal.run();
		}




}

