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

public class Julia extends Fractal{
	public Julia(){
		setup();
	}
	
	//parametrage de la fractale pour l'ensemble de Mandelbrot
	@Override
	public void setup(){
		fracType=FracType.Julia;
		zoom = 400 ;
		max_it = 150;
		x1 = -1;
		y1 = -1.2;
		currentColor = Color.VIOLET;
		colorInside = Color.BLACK;	
		image = new WritableImage(pic_x,pic_y);
	}
	
	//calcul de la fractale
	//SOURCE : https://zestedesavoir.com/tutoriels/329/dessiner-la-fractale-de-mandelbrot/
	@Override
	public void run(){
		for( x = xMin ; x < pic_x;x++)
		{
			for( y = yMin; y < pic_y ; y++) 
			{
				double c_r = 0.285;
				double c_i = 0.01;
				double z_r = x/((double)zoom)*(1+Math.PI/10)+x1;
				double z_i = y/((double)zoom)+y1;
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
				else if(i>0)
				{
					Color newColor = new Color(i*(currentColor.getRed())/max_it,i*(currentColor.getGreen())/max_it,i*(currentColor.getBlue())/max_it,1);		
					image.getPixelWriter().setColor(x, y,newColor);
				}  		

			}
		}
	}

}
