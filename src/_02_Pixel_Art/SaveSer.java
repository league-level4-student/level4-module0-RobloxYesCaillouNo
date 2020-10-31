package _02_Pixel_Art;

import java.io.Serializable;

 
public class SaveSer implements Serializable{
	public Pixel[][] pixelz;

	public SaveSer(Pixel[][] pixls) {
		this.pixelz = pixls;
	}
}
