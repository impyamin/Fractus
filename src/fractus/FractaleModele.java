package fractus;

import java.awt.Color;
import java.util.Observable;

import javafx.scene.image.ImageView;


public class FractaleModele extends Observable {	
	ImageView img = new ImageView();
	
	private Picture pic ;
	private int resoX = 1020 ;
	private int resoY = 480; 
	private int zoom = 200 ;
	private int max_it = 50;
	private double x1 = -2.1;
	private double x2 = 3.0;
	private double y1 = -1.2;
	private double y2 = 1.2;
	private Color currentColor = Color.GREEN;
	private int xOffset =0;
	private int yOffset = 0;
	

	public FractaleModele() {
	}	
	 
    public void createMandelBrot() {

    	//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
    	pic = new Picture(resoX,resoY);
    	    	
    	//double imgY = (y2 - y1)*zoom;
    	//double imgX = (x2- x1)*zoom;
    	
    	for(int x = 0 ; x < 1020;x++)
    	{
    		for(int y = 0; y < 480 ; y++) 
    		{
    			double c_r = (x+xOffset)/((double)zoom)+x1 ;
    			double c_i = (y+yOffset)/((double)zoom)+y1 ;
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
					Color newColor = new Color(0,0,0);
					pic.set(x,y,newColor);
				}
				else
				{
					Color newColor = new Color((int)i*(currentColor.getRed())/max_it,(int)i*(currentColor.getGreen())/max_it,(int)i*(currentColor.getBlue())/max_it);
					pic.set(x, y, newColor);
					
				}    			   			
    			
    		}
    	}
    	
    	
    }
    public  void setResolutionX(double reso) {
    	this.resoX *= reso;
    	setChanged();
    	notifyObservers();
    }
    public  void setResolutionY(double reso) {
    	this.resoY *= reso;
       	setChanged();
    	notifyObservers();
    }   
    public void setNbIteration(int nbiterations){
    	max_it=nbiterations;
    }
    
    public void setZoom(int zoom) {
    	//setResolutionX(3);
    	//setResolutionY(3);
    	this.zoom *=2 ;
    	yOffset*=2;
    	xOffset*=2;
    	System.out.println("resolution  :"+ resoX + "   " + resoY);
    }
    
    public void incZoom(){
    	zoom +=250;
    	getPicture();    	
    }
    public int getZoom() {
    	return zoom;
    } 
    public int getNbIteration(){
    	return max_it;
    }
    public int getResoX() {
    	return resoX;
    }
    public int getResoY() {
    	return resoY;
    }
	public Picture getPicture() {			
		    return pic ;
	   }


	public void setCurrentColor(double red, double green, double blue) {
		currentColor=new Color((int)(red*255), (int)(green*255), (int)(blue*255));
		System.out.println(currentColor.getRed()+"."+currentColor.getGreen()+"."+currentColor.getBlue());
	}

	public Color getCurrentColor() {
		return currentColor;
	}
	

}
