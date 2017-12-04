package calcul;

import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Mandelbrot extends Fractal {

	public Mandelbrot() {
		fracType="Mandelbrot";
		zoom = 400 ;
		max_it = 50;
		x1 = -2.1;
		x2 = 3;
		y1 = -1.2;
		y2 = 1.2;
		currentColor = Color.GREEN;
		colorInside = Color.BLACK;	

		image = new WritableImage(pic_x,pic_y);

	}
	@Override
	public void run(){
		for( x = xMin ; x < pic_x;x++)
		{
			for( y = yMin; y < pic_y ; y++) 
			{
				double c_r = x/((double)zoom)*(1+Math.PI/10)+x1 ;
				double c_i = y/((double)zoom)+y1 ;
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
				while((Math.pow(z_r,2)) + (Math.pow(z_i,2)) < 4 && i <max_it);
				
				if(i == max_it) { 
					image.getPixelWriter().setColor(x,y,colorInside);
				}
				else
				{
					Color newColor = new Color(i*(currentColor.getRed())/max_it,i*(currentColor.getGreen())/max_it,i*(currentColor.getBlue())/max_it,1);		
					image.getPixelWriter().setColor(x, y,newColor);
				}  		
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
