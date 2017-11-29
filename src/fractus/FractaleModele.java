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
	private int x,y;
	private double x1 = -2.1;
	private double x2 = 3.0;
	private double y1 = -1.2;
	private double y2 = 1.2;
	private Color currentColor = Color.GREEN;
	private Color colorInside = Color.BLACK;	
	private int xOffset =10;
	private int yOffset = 10;

	private String fractalType="Mandelbrot";
	

	public FractaleModele() {
	}	
	 
    public void createMandelBrot() {

    	//https://stackoverflow.com/questions/14097559/zooming-in-on-mandelbrot-set-fractal-in-java
    	pic = new Picture(resoX,resoY);
    	    	
    	//double imgY = (y2 - y1)*zoom;
    	//double imgX = (x2- x1)*zoom;
    	
    	for( x = 0 ; x < 1020;x++)
    	{
    		for( y = 0; y < 480 ; y++) 
    		{
    			double c_r = (x+xOffset)/((double)zoom)+x1 ;
    			double c_i = (y+yOffset)/((double)zoom)+y1 ;
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
    			
    		//	System.out.println(" " ) ; 
  				
				if(i == max_it) {

					pic.set(x,y,colorInside);
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
    
    public void setZoom(int zoomX,int zoomY) {
    	
    	zoom *=1.6 ;
    	//yOffset*=2;
    	//xOffset*=2;
    	x = zoomX;
    	y = zoomY;
    	System.out.println("OFFSETS  :"+ xOffset + "   " + yOffset + " zoom : " + zoom);
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
	}

	public Color getCurrentColor() {
		return currentColor;
	}

	public void setFractaleType(String type) {
		fractalType=type;
	}

	public String getFractalType() {
		return fractalType;
	}

	public void setInsideColor(double red, double green, double blue) {
		colorInside=new Color((int)(red*255), (int)(green*255), (int)(blue*255));
	}


	

}
