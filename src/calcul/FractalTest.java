package calcul;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import MVC.FractaleControler;
import MVC.FractaleModele;
import MVC.FractaleModele.FracType;
import javafx.scene.paint.Color;

public class FractalTest {

	private static final Color COLOR = Color.RED;
	private static final int ITERATION = 500;
	private static final FracType TYPE = FracType.Julia;
	private static final Color COLORINSIDE = Color.WHITE;
	private static final double zoomDefault = 400;
	private static final double zoomIncreased = 600 ;

	FractaleModele modele;
	FractaleControler  controleur;

	// initialisation des varibles modele et controleur
	@Before
	public void initial() {
		modele = new FractaleModele();
		controleur = new FractaleControler(modele);
	}
	//test des accesseurs/mutateurs
	@Test
	public void testSet() {
		controleur.setNbIteration(ITERATION);
		assertEquals(ITERATION, modele.getNbIteration());
		controleur.setCurrentColor(COLOR);
		assertEquals(COLOR,modele.getCurrentColor());
		controleur.setFractaleType(TYPE);
		assertEquals(TYPE,modele.getFractalType());
		controleur.setInsideColor(COLORINSIDE);;
		assertEquals(COLORINSIDE,modele.getColorInside());
	}	
	//test de l'incrémentation lors du zoom et du dezoom
	@Test
	public void testZoomAndUnzoom() {
		controleur.setZoomPlus("zoom");		
		assertTrue(zoomIncreased==modele.getZoomingValue());
		controleur.setZoomPlus("unzoom");
		assertTrue(zoomDefault == modele.getZoomingValue());		
	}
	
	//test de la  fonction de remise à defaut de la fractale
	@Test 
	public void testResetFractale() {
		FractaleModele mod = new FractaleModele();
		controleur.setNbIteration(150);
		controleur.setZoomPlus("zoom");
		controleur.reset();
		assertTrue(mod.getNbIteration()==modele.getNbIteration()
			   && mod.getZoomingValue()==modele.getZoomingValue());		
	}




}

