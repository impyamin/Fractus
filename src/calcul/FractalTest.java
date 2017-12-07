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
	private static final double zoomIncreased = 500 ;

	FractaleModele modele;
	FractaleControler  controleur;

	// initialisation des varibles modele et controleur
	@Before
	public void initial() {
		modele = new FractaleModele();
		controleur = new FractaleControler(modele);
	}

	//test des accesseurs
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
	@Test
	public void setZoomAndUnzoom() {
		controleur.setZoomPlus("zoom");		
		assertTrue(zoomIncreased==modele.getZoomingValue());
		controleur.setZoomPlus("unzoom");
		assertTrue(zoomDefault == modele.getZoomingValue());
		
	}




}

