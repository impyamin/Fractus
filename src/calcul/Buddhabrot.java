package calcul;

import java.util.ArrayList;

import com.sun.javafx.geom.Vec2f;

import javafx.scene.paint.Color;

/****
 * 
 * 
 * @author JOLLIET Louis / TRICHA Yamin S3A
 *
 */

public class Buddhabrot extends Fractal {
	int iteration_rouge = 100;
	int iteration_vert = 1000;
	int iteration_bleu = 10000;
	public Buddhabrot() {
		// TODO Auto-generated constructor stub
		x1 = -2.1;
		x2 = 0.6;
		y1 = -1.2;
		y2 = 1.2;
		zoom = 100;

		max_it = Math.max(iteration_rouge, Math.max(iteration_vert, iteration_bleu));
	}
	@SuppressWarnings("null")
	@Override
	public void run(){
		int image_x = (int) ((x2 - x1) * zoom);
		int image_y = (int) ((y2 - y1) * zoom);

		int pixels_rouge[][]= new int[image_x][image_y];
		int pixels_vert[][] = new int[image_x][image_y];
		int pixels_bleu[][] = new int[image_x][image_y];
		for(int i=0;i<image_x;i++)
			for(int j=0;j<image_y;j++)
			{
				pixels_bleu[i][j]=0;
				pixels_rouge[i][j]=0;
				pixels_vert[i][j]=0;
			}
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
					Vec2f a= new Vec2f((float) ((z_r-x1)*zoom),(float) ((z_i-y1)*zoom));
					tmp_pixels.add(a);
				}				
				while(z_r*z_r + z_i*z_i < 4 && i < max_it);
				if(i<iteration_rouge)
					for(Vec2f pixel : tmp_pixels)
						if(pixel.x<=image_x && pixel.x>=0 && pixel.y<=image_y&& pixel.y>=0)
							pixels_rouge[(int) pixel.x][(int) pixel.y]++;

				if(i<iteration_vert)
					for(Vec2f pixel : tmp_pixels)
						if(pixel.x<=image_x && pixel.x>=0 && pixel.y<=image_y&& pixel.y>=0)
							pixels_vert[(int) pixel.x][(int) pixel.y]++;
				if(i<iteration_bleu)
					for(Vec2f pixel : tmp_pixels)
						if(pixel.x<=image_x && pixel.x>=0 && pixel.y<=image_y&& pixel.y>=0)
							pixels_bleu[(int) pixel.x][(int) pixel.y]++;
				
			}
		}
		for(int x =0; x<image_x;x++)
			for(int y=0;y<image_y;y++)
			{
				Color newColor = new Color(Math.min(pixels_rouge[x][y], 255), Math.min(pixels_vert[x][y], 255), Math.min(pixels_bleu[x][y], 255),1);
				image.getPixelWriter().setColor(x, y,newColor);

			}

	}
	@Override
	public void reset(){

	}
}