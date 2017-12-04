package fractus;

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
		//pic_x = (x2 -x1) * zoom;
		//pic_y = (y2-y1)  *zoom ;
		image = new WritableImage(2000,1000);
		pic_x = 2000;
		pic_y = 1000;
	}
	@Override
	public void run(){
		System.out.println("run in mandelbrot");
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
		pic_x = (x2 -x1) * zoom;
		pic_y = (y2-y1)  *zoom ;
		image = new WritableImage((int)pic_x+1,(int)pic_y);
	}
}
