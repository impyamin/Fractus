package calcul;


import MVC.FractaleModele.FracType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Mandelbrot extends Fractal {

	private int resY;
	private int resX;	

	public Mandelbrot() {	
		setup();
	}

	//parametrage de la fractale pour l'ensemble de Mandelbrot
	@Override
	public void setup()
	{
		fracType=FracType.Mandelbrot;
		zoom = 400 ;
		max_it = 50;
		x1 = -2.1;
		x2 = 3;
		y1 = -1.2;
		y2 = 1.2;
		currentColor = Color.GREEN;
		colorInside = Color.BLACK;	
		image = new WritableImage(2000,1000);
	}
	//calcul de la fractale
	//SOURCE : https://zestedesavoir.com/tutoriels/329/dessiner-la-fractale-de-mandelbrot/
	@Override
	public void run(){

		for( x = xMin ; x < pic_x;x++)
			for( y = yMin; y < pic_y; y++) 
			{
				double c_r =x/(double)zoom*(1+Math.PI/10)+x1;
				double c_i =y/(double)zoom+y1 ;
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
				while((Math.pow(z_r,2)) + (Math.pow(z_i,2)) < 4  && i <max_it);

				if(i!=max_it)
				{
					Color newColor = new Color(currentColor.getRed()*i/max_it,currentColor.getGreen()*i/max_it,currentColor.getBlue()*i/max_it,1);
					image.getPixelWriter().setColor(x, y,newColor);
				} 
	
				else
					image.getPixelWriter().setColor(x,y,colorInside);
			}
		
	}

}
