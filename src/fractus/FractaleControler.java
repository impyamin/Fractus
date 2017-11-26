package fractus;

public class FractaleControler {
	FractaleModele fracModele ;
	public FractaleControler(FractaleModele modele) {	
		fracModele = modele ;
		 
		
	}
	public void setZoom(int zoom)
	{
		fracModele.setZoom(zoom);
	}
	
	public void savePicture() {
		fracModele.createMandelBrot();
		fracModele.getPicture().save("Fractale.png");
	}

}
