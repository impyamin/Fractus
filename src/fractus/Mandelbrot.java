package fractus;

import java.awt.Color;
/*****************************************************************************
*Source : https://introcs.cs.princeton.edu/java/32class/Mandelbrot.java.html
*******************************************************************************/


public class Mandelbrot {
    int n   = 500;   // create n-by-n image
	Picture pic  = new Picture(1000,300);
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
   
}