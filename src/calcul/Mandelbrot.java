package calcul;

import java.util.ArrayList;

import MVC.FractaleModele.FracType;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Mandelbrot extends Fractal {
	public ArrayList<Color> couleurs = new ArrayList<Color>();


	public Mandelbrot() {
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


		couleurs.add(Color.rgb(66, 30, 15));
		couleurs.add(Color.rgb(25, 7, 26));
		couleurs.add(Color.rgb(9, 1, 47));
		couleurs.add(Color.rgb(4, 4, 73));
		couleurs.add(Color.rgb(0, 7, 100));
		couleurs.add(Color.rgb(12, 44, 138));
		couleurs.add(Color.rgb(24, 82, 177));
		couleurs.add(Color.rgb(57, 125, 209));
		couleurs.add(Color.rgb(134, 181, 229));
		couleurs.add(Color.rgb(211, 236, 248));
		couleurs.add(Color.rgb(241, 233, 191));
		couleurs.add(Color.rgb(248, 201, 95));
		couleurs.add(Color.rgb(255, 170, 0));
		couleurs.add(Color.rgb(204,128,0));
		couleurs.add(Color.rgb(153,87,0));
		couleurs.add(Color.rgb(106,52,3));


	}
	@Override
	public void run(){
		for( x = xMin ; x < pic_x;x++)
		{
			for( y = yMin; y < pic_y ; y++) 
			{
				double c_r = (x+x2)/((double)zoom)*(1+Math.PI/10)+x1 ;
				double c_i = (y+y2)/((double)zoom)+y1 ;
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
					Color newColor = mixColors(couleurs.get((int)Math.floor(i)%15), couleurs.get(((int)Math.floor(i)%15)+1), i%1);		
					image.getPixelWriter().setColor(x, y,newColor);
				}  	
				if(i < max_it) { 
					double log_zn = Math.log( Math.pow(x,2) + Math.pow(y,2) ) / 2;
					double nu = Math.log( log_zn / Math.log(2) ) / Math.log(2);
					i = i + 1 - nu;
				}
	
				else
					image.getPixelWriter().setColor(x,y,colorInside);
			}
		}
	}
	@Override
	public void reset(){
		zoom = 400;
		max_it = 50;
		x1 = -2.1;
		x2 = 3;
		y1 = -1.2;
		y2 = 1.2;
		currentColor = Color.GREEN;
		colorInside = Color.BLACK;	
		image = new WritableImage((int)pic_x+1,(int)pic_y);
	}
}
