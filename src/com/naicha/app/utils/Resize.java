package com.naicha.app.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resize {
	BufferedImage bufImage;
	int width;
	int height;

	public Resize() {
	}

	public Resize(String srcPath) {
//		this.width = width;
//		this.height = height;
		try {
			this.bufImage = ImageIO.read(new File(srcPath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage rize(BufferedImage srcBufImage, int widths,int heights) {
		double sy,sx;
		int height,width;
		if(srcBufImage.getWidth() <= srcBufImage.getHeight()){
			 width= widths;
			 sx = (double) width / srcBufImage.getWidth();
			 sy=sx;
			 height  = (int) (srcBufImage.getHeight() * sx);
		}else{
			 height= heights;
			 sy = (double) height / srcBufImage.getHeight();
			 sx=sy;
			 width  = (int) (srcBufImage.getWidth() * sy);
		}

		BufferedImage bufTarget = null;
		int type = srcBufImage.getType();
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width,
					height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else
			bufTarget = new BufferedImage(width, height, type);

		Graphics2D g = bufTarget.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufImage,
				AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return bufTarget;
	}

	public static void main(String[] args) {
		
		String srcPath =null;
    	File file = new File("/Users/guxiong/Desktop/2014-11-03/");
    	File[] files = file.listFiles();
    	if(files!=null){
    		for(File f:files){
    			System.out.println(f.getPath());
    			srcPath = f.getPath();
    			Resize resize = new Resize(srcPath);
    			BufferedImage image =Resize.rize(resize.bufImage,100,100);
    			try {
    				String fileName = f.getName();
    				File ff= new File("small/2014-11-03/"+fileName);
    				if(!ff.exists()){
    					ff.mkdirs();
    				}
					ImageIO.write(image, "gif", ff);
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	}
    	
    	
//		String srcPath ="test/F4FEC832BF0F3F96ED0C9F3A653EC670.png";
//		int width = 391;
////		int height = 472;
//		Resize resize = new Resize(srcPath);
//		BufferedImage image =Resize.rize(resize.bufImage,391,451);
//		
//		try {
//			ImageIO.write(image, "gif", new File("test.gif"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	public BufferedImage getBufImage() {
		return bufImage;
	}

	public void setBufImage(BufferedImage bufImage) {
		this.bufImage = bufImage;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}