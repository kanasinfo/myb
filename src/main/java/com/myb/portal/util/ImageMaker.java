package com.myb.portal.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageMaker {
	
	private static int SCALE_DEFAULT = Image.SCALE_DEFAULT;

	public static enum ImageType{
		JPG("jpg"),PNG("png"),BMP("bmp");
		
		private String type;
		ImageType(String type){
			this.type=type;
		}
		public String toString(){
			return this.type;
		}
	}
	/**
	 * Scale an image from source input stream to and save it as a file.
	 * @param sourceImageStream, the input stream of the source image.
	 * @param maxWidth, max width
	 * @param maxHeight, max height
	 * @param filename, the output file name
	 * @param forceScale, true will force the scale of the source image.
	 * @param scaleAlgorithm, image scale algorithm 
	 * @throws IOException
	 * 
	 */
	public static void transform(InputStream sourceImageStream, ImageType imageType, int maxWidth, int maxHeight, 
			String filename, boolean forceScale, Integer scaleAlgorithm) throws IOException{
		if(scaleAlgorithm==null)scaleAlgorithm = SCALE_DEFAULT;
		BufferedImage src = ImageIO.read(sourceImageStream);
		int width;
		int height;
		if (forceScale){
			width = src.getWidth();
			height = src.getHeight();
			double srcRate = (double) width/height;
			double rate = (double) maxWidth/maxHeight;
			if (srcRate>rate){
				width = maxWidth;        
				height = (int) (width/srcRate);
			}else{
				height = maxHeight;
				width = (int) (height*srcRate);
			}
		}else{
			width = maxWidth;
			height = maxHeight;
		}
		Image image = src.getScaledInstance(width, height,scaleAlgorithm);
		BufferedImage tag;
		if(imageType.toString().equalsIgnoreCase("gif")||imageType.toString().equalsIgnoreCase("png"))
			tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
		else
			tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, imageType.toString(), new File(filename));
	}
	
	/**
	 * Scale an image from source input stream to and save it as a file.
	 * @param sourceImageStream, the input stream of the source image.
	 * @param maxWidth, max width
	 * @param maxHeight, max height
	 * @param filename, the output file name
	 * @param forceScale, true will force the scale of the source image, default true.
	 * @param scaleAlgorithm, image scale algorithm, default Image.SCALE_DEFAULT
	 * @throws IOException
	 * 
	 */
	public static void zoom(InputStream sourceImageStream, int maxWidth, int maxHeight, 
			String filename, Boolean forceScale, Integer scaleAlgorithm) throws IOException{
		if(scaleAlgorithm==null)scaleAlgorithm = SCALE_DEFAULT;
		if(forceScale==null)forceScale = true;
		BufferedImage src = ImageIO.read(sourceImageStream);
		int width;
		int height;
		if (forceScale){
			width = src.getWidth();
			height = src.getHeight();
			double srcRate = (double) width/height;
			double rate = (double) maxWidth/maxHeight;
			if (srcRate>rate){
				width = maxWidth;        
				height = (int) (width/srcRate);
			}else{
				height = maxHeight;
				width = (int) (height*srcRate);
			}
		}else{
			width = maxWidth;
			height = maxHeight;
		}
		Image image = src.getScaledInstance(width, height,scaleAlgorithm);
		String imageType = filename.substring(filename.lastIndexOf(".")+1);
		BufferedImage tag;
		if(imageType.equalsIgnoreCase("gif")||imageType.equalsIgnoreCase("png"))
			tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
		else
			tag = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics g = tag.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
		ImageIO.write(tag, imageType, new File(filename));
	}
	
	/**
	 * Scale and crop an image from source input stream to and save it as a file.
	 * @param sourceImageStream, the input stream of the source image.
	 * @param maxWidth, max width
	 * @param maxHeight, max height
	 * @param filename, the output file name
	 * @param scaleAlgorithm, image scale algorithm 
	 * @throws IOException
	 * 
	 */
	public static void zoomAndCropCenter(InputStream sourceImageStream, int maxWidth, int maxHeight, 
			String filename, Integer scaleAlgorithm) throws IOException{
		if(scaleAlgorithm==null)scaleAlgorithm = SCALE_DEFAULT;
		BufferedImage src = ImageIO.read(sourceImageStream);
		int width;
		int height;
		int x;
		int y;
		width = src.getWidth();
		height = src.getHeight();
		double srcRate = (double) width/height;
		double rate = (double) maxWidth/maxHeight;
		if (srcRate>rate){
			height = maxHeight;
			y = 0;
			width = (int) (height*srcRate);
			x = (int)((width-maxWidth)/2);
		}else{
			width = maxWidth;
			x = 0;
			height = (int) (width/srcRate);
			y = (int)((maxHeight-height)/2);
		}
		Image image = src.getScaledInstance(width, height,scaleAlgorithm);

		String imageType = filename.substring(filename.lastIndexOf(".")+1);
		BufferedImage tag;
		if(imageType.equalsIgnoreCase("gif")||imageType.equalsIgnoreCase("png"))
			tag = new BufferedImage(maxWidth, maxHeight,BufferedImage.TYPE_INT_ARGB);
		else
			tag = new BufferedImage(maxWidth, maxHeight,BufferedImage.TYPE_INT_RGB);
		
		Graphics g = tag.getGraphics();
		g.drawImage(image, -x, y, null);
		g.dispose();
		ImageIO.write(tag, imageType, new File(filename));
	}

}