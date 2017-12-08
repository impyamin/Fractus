package calcul;

import MVC.FractaleModele;
import MVC.FractaleModele.FracType;
/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Multithreading extends Thread {


	FractaleModele fracMod;
	Fractal fractal;
	FracType fracType;

	public Multithreading(int x1,int y1,int x2,int y2,Fractal frac){
		//instantiation de la factale
		fractal=frac;
		fractal.xMin =x1;
		fractal.yMin =y1;
		fractal.pic_x = x2 ;
		fractal.pic_y = y2 ;
		fracType=fractal.getFractalType();
		start();
	}	

	public void run() {
		fractal.run();
		}




}

