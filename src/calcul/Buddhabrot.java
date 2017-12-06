package calcul;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2f;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Buddhabrot extends Fractal {
	public Buddhabrot() {
		// TODO Auto-generated constructor stub
		x1 = -2.1;
		x2 = 0.6;
		y1 = -1.2;
		y2 = 1.2;
		zoom = 100;
		int iteration_rouge = 100;
		int iteration_vert = 1000;
		int iteration_bleu = 10000;
		max_it = Math.max(iteration_rouge, Math.max(iteration_vert, iteration_bleu));
	}
	@Override
	public void run(){

		int image_x = (int) ((x2 - x1) * zoom);
		int image_y = (int) ((y2 - y1) * zoom);

		int pixels_rouge[][] = null;
		int pixels_vert[][] = null;
		int pixels_bleu[][] = null;
		for(int i=0;i<image_x;i++)
			for(int j=0;j<image_y;j++)
			{
				pixels_bleu[i][j]=0;
				pixels_rouge[i][j]=0;
				pixels_vert[i][j]=0;
			}
		// en théorie, on devrait faire une seul boucle dans laquelle on devrait prendre les coordonnées (x; y) au hasard.
		for(int x =0; x<image_x;x++)
		{
			for(int y=0;y<image_y;y++)
			{
				double c_r = x / zoom + x1;
				double c_i = y / zoom + y1;
				double z_r = 0;
				double z_i = 0;
				double i = 0;
				ArrayList<Vec2f> tmp_pixels = new ArrayList<Vec2f>() ;

				do
				{
					double tmp = z_r;
					z_r = z_r*z_r - z_i*z_i + c_r;
					z_i = 2*z_i*tmp + c_i;
					i++;
					Vec2f a = null;
					a.x=(float) ((z_r-x1)*zoom);
					a.y=(float) ((z_i-y1)*zoom);
					tmp_pixels.add(a);
				}				
				while(z_r*z_r + z_i*z_i < 4 && i < max_it);
			}
		}

//				si i < iteration_rouge
//				Pour les iteration_rouge premières valeurs pixel de tmp_pixels
//				si la case pixels_rouge[pixel[0]][pixel[1]] existe
//				on incrémente la case en question
//				finSi
//				finPour
//				finSi
//				si i < iteration_vert
//				Pour les iteration_vert premières valeurs pixel de tmp_pixels
//				si la case pixels_vert[pixel[0]][pixel[1]] existe
//				on incrémente la case en question
//				finSi
//				finPour
//				finSi
//				si i < iteration_bleu
//				Pour les iteration_bleu premières valeurs pixel de tmp_pixels
//				si la case pixels_bleu[pixel[0]][pixel[1]] existe
//				on incrémente la case en question
//				finSi
//				finPour
//				finSi
//				finPour
//				finPour
//
//				Pour chaque pixel de coordonnées (x; y) de l'image
//				Dessiner le pixel de coordonnées (x; y) avec la couleur rgb(min(pixels_rouge[x][y], 255), min(pixels_vert[x][y], 255), min(pixels_bleu[x][y], 255))
//				finPour

			}
			@Override
			public void reset(){

			}
		}