package com.otaviotarelho.curso.services;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.otaviotarelho.curso.services.exceptions.FileException;

@Service
public class ImageService {

	private static final String JPG = "jpg";
	private static final String PNG = "png";
	
	public BufferedImage getJpgImageFromFile(MultipartFile uploadedFile) {
		try {
			String ext = FilenameUtils.getExtension(uploadedFile.getOriginalFilename());
			
			if(!PNG.equalsIgnoreCase(ext) && !JPG.equalsIgnoreCase(ext)) {
				throw new FileException("Somente imagens PNG e JPG são permitidas");
			}
			
			BufferedImage img;
				img = ImageIO.read(uploadedFile.getInputStream());
			
			if(PNG.equals(ext)) {
				img = pngToJpg(img);
			}
			return img;
		} catch (IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}

	private BufferedImage pngToJpg(BufferedImage img) {
		BufferedImage jpgImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
		jpgImage.createGraphics().drawImage(img, 0, 0,Color.WHITE, null);
		return jpgImage;
	}
	
	public InputStream getImputStrem(BufferedImage img, String extension) {
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(img, extension, os);
			return new ByteArrayInputStream(os.toByteArray());
		} catch(IOException e) {
			throw new FileException("Erro ao ler arquivo");
		}
	}
	
	public BufferedImage cropImagem(BufferedImage image) {
		int min = image.getHeight() <= image.getWidth() ? image.getHeight() :image.getWidth();
		return Scalr.crop(image, 
						(image.getWidth()/2) - (min/2),
						(image.getHeight()/2) - (min/2),
						min, min);
	}
	
	public BufferedImage resizeImage(BufferedImage image, int size) {
		return Scalr.resize(image, Scalr.Method.QUALITY, size);
	}
}
