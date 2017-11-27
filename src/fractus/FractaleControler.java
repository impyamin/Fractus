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
		switch (fracModele.getFractalType()) {
		case "Mandelbrot":
			fracModele.createMandelBrot();
			break;
		case "Option 2" :
			break;
		default:
			break;
		}
		fracModele.getPicture().save("Fractale.png");
	}
	public int getResoY() {
		return fracModele.getResoY();
	}
	public int getResoX() {
		return fracModele.getResoX();
	}
	public void setNbIteration(Integer it) {
		fracModele.setNbIteration(it);		
	}
	public void setCurrentColor(double red, double green, double blue) {
		fracModele.setCurrentColor(red, green, blue);		
	}
	public void setFractaleType(String type) {
		fracModele.setFractaleType(type);
	}

}
