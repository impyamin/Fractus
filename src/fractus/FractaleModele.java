package fractus;

import java.awt.Color;
import java.util.Observable;

public class FractaleModele extends Observable {	
	private Picture pic ;
	private int resoX = 1300 ;
	private int resoY = 600; 
	int zoom = 200 ;
	int max_it = 50;
	public Color currentColor = Color.GREEN;


	public FractaleModele() {
		pic = new Picture(resoX,resoY);
	}	
	 
    public void createMandelBrot() {
    	double x1 = -2.1;
    	double x2 = 3.0;
    	double y1 = -1.2;
    	double y2 = 1.2;

    	    	
    	double imgX = (x2- x1)*zoom;
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
    	createMandelBrot();
    	System.out.println("nombre d'itérations : "+ max_it);


    }
    
    public void setZoom(int zoom) {
    	setResolutionX(3);
    	setResolutionY(3);
    	this.zoom += zoom ;
    	createMandelBrot();
    	//getPicture();
    	System.out.println("resolution  :"+ resoX + "   " + resoY);
    }
    
    public void incZoom(){
    	zoom +=250;
    	createMandelBrot();
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
		createMandelBrot();
		System.out.println(currentColor.getRed()+"."+currentColor.getGreen()+"."+currentColor.getBlue());
	}

	public Color getCurrentColor() {
		return currentColor;
	}
	

}
