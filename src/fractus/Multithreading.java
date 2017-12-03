package fractus;


import javafx.scene.paint.Color;

public class Multithreading extends Thread {



	private double zoom ;
	private int max_it = 50;
	private int x =0;
	private int y=0;
	private double x1;
	private double y1;
	private Color currentColor = Color.GREEN;
	private Color colorInside = Color.BLACK;		
	private int xMax,yMax;
	private int xMin,yMin;
	private String fracType;
	private FractaleModele frac ;
	private double pic_x; 
	private double pic_y; 

	Multithreading(int x,int y,int x2,int y2,FractaleModele fracMod){
		frac = fracMod;
		this.x1=frac.getX1();		
		this.y1=frac.getY1();
		xMax=x2;
		yMax=y2;
		xMin=x;
		yMin=y;
		zoom = frac.getZoom();		
		pic_x = frac.getPic_x();
		pic_y = frac.getPic_y();
		max_it = frac.getNbIteration();
		currentColor = frac.getCurrentColor();
		colorInside = frac.getColorInside();
		fracType=frac.getFractalType();
		System.out.println(" PICXXXXXXX " + pic_x);
		start();

	}	

	public void run() {

		System.out.println(yMin);
		if(fracType=="Mandelbrot")
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
						frac.getImage().getPixelWriter().setColor(x,y,colorInside);
					}
					else
					{
						Color newColor = new Color(i*(currentColor.getRed())/max_it,i*(currentColor.getGreen())/max_it,i*(currentColor.getBlue())/max_it,1);		
						frac.getImage().getPixelWriter().setColor(x, y,newColor);
					}  		

				}
			}
		else if(fracType=="Option 2")
			for(int x = 0 ; x < 1020;x++)
			{
				for(int y = 0; y < 480 ; y++) 
				{
					frac.getImage().getPixelWriter().setColor(x,y,colorInside);
				}
			}
	}




}

