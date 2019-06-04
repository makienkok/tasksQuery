package com.tasksquery.utils;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class ImgUtils
{
	public Path multipartFileToFile(MultipartFile multipart, Path dir) throws IOException
	{
		Path filepath = Paths.get(dir.toString(), multipart.getOriginalFilename());
		multipart.transferTo(filepath);
		return filepath;
	}

	enum ImgRetention
	{
		JPG, IMG, PNG;
	}

	boolean checkImgRetention(Path filePath)
	{
		for (ImgRetention val : ImgRetention.values())
			if (filePath.getFileName().endsWith(val.name()))
				return true;
		return false;
	}
	
	final static Integer IMG_HEIGHT = 240;
	final static Integer IMG_WIDTH = 320;
	
	private static BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }

}
