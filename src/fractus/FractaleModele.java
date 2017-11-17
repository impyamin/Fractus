package fractus;

import java.util.Observable;

public class FractaleModele extends Observable {
	
	private int reso = 500 ;
	private Picture pic ;
	
	public FractaleModele() {
		pic = new Picture(reso,reso);
	}
	

}
