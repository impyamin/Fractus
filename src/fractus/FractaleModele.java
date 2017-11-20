package fractus;

import java.awt.Color;
import java.util.Observable;

public class FractaleModele extends Observable {	
	private Picture pic ;
	private int resoX = 1500 ;
	private int resoY = 600	 ; 
	int zoom = 250 ;

	public FractaleModele() {
		pic = new Picture(resoX,resoY);
	}	
	 
    public void createMandelBrot() {
    	double x1 = -2.1;
    	double x2 = 3.0;
    	double y1 = -1.2;
    	double y2 = 1.2;
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
					Color color = new Color(0,(int)i*255/max_it,0);
					pic.set(x, y, color);					
				}    			   			
    		}
    	}
    	
    }
    public  void setResolutionX(int reso) {
    	this.resoX = reso;
    	setChanged();
    	notifyObservers();
    }
    public  void setResolutionY(int reso) {
    	this.resoY = reso;
       	setChanged();
    	notifyObservers();
    }   
    
    public void setZoom(int zoom) {
    	this.zoom = zoom ;
    	createMandelBrot();
    }
    public int getZoom() {
    	return zoom;
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

}
