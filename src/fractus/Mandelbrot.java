package fractus;

import java.awt.Color;
/*****************************************************************************
*Source : https://introcs.cs.princeton.edu/java/32class/Mandelbrot.java.html
*******************************************************************************/


public class Mandelbrot {
    int n   = 500;   // create n-by-n image
	Picture pic  = new Picture(800,500);
    // return number of iterations to check if c = a + ib is in Mandelbrot set
    public static int mand(Complex z0, int max) {
        Complex z = z0;
        for (int t = 0; t < max; t++) {
            if (z.abs() > 2.0) return t;
            z = z.times(z).plus(z0);
        }
        return max;
    }
 
   /* public Mandelbrot()  {
        double xc   = -0.5;
        double yc   = 0;
        double size = 2;

        int max = 150;   // maximum number of iterations
 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double x0 = xc - size/2 + size*i/n;
                double y0 = yc - size/2 + size*j/n;
                Complex z0 = new Complex(x0, y0);
                int gray = max - mand(z0, max);
                Color color = new Color(gray, gray, gray);
                pic.set(i, n-1-j, color);
                System.out.println("Calcul en cours ... \n");
            }
        }
 //       picture.show();
    }*/
    
    public  Mandelbrot() {
    	double x1 = -2.1;
    	double x2 = 3.0;
    	double y1 = -1.2;
    	double y2 = 1.2;
    	int zoom = 120 ;
    	int max_it = 50;
    	
    	
    	double imgX = (x2 - x1)*zoom;
    	double imgY = (y2 - y1)*zoom;
    	
    	for(int x = 0 ; x < imgX;x++)
    	{
    		for(int y = 0; y < imgY ; y++) 
    		{
    			double c_r = x/(double)zoom +x1 ;
    			double c_i = y/(double)zoom +y1 ;
    			double z_r = 0;
    			double z_i = 0;
    			double i = 0;
    			   			
    			
    			do
    			{
    				double tmp = z_r ;
        			z_r = z_r*z_r - z_i*z_i + c_r ;
        			z_i= 2*z_i*tmp + c_i;
        			++i ;
    			}
    			while((z_r*z_r) + (z_i*z_i) < 4 && i <max_it);
    			
    			System.out.println(y);
    			
				if(i == max_it) {
					Color color = new Color(0,0,0);
					pic.set(x,y,color);
				}
				else
				{
					Color color = new Color(0,0,(int)i*255/max_it);
					pic.set(x, y, color);
					
				}
    			
    			
    		}
    	}
    }
   public Picture getPicture() {
	   pic.save("Fractale.png");
	   return pic ;
   }
    
}