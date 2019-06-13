package com.tasksquery.utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.tasksquery.models.TaskDTO;

public class ImgUtils
{
    enum ImgExtention
    {
        JPG, IMG, PNG;
    }

    public static boolean checkImgExtention(String imName)
    {
        for (ImgExtention val : ImgExtention.values())
            if (imName.indexOf(val.name().toLowerCase()) != -1)
                return true;
        return false;
    }

    final static Integer IMG_HEIGHT = 240;

    final static Integer IMG_WIDTH = 320;

    public static byte[] resizeImg(MultipartFile mpFile) throws IOException
    {

        InputStream io = new ByteArrayInputStream(mpFile.getBytes());
        BufferedImage inputImage = ImageIO.read(io);

        if (inputImage.getWidth() == IMG_WIDTH || inputImage.getHeight() == IMG_HEIGHT)
            return null;

        BufferedImage outputImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, inputImage.getType());

        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g2d.dispose();

        String typeFile = ImgExtention.JPG.name().toLowerCase();
        for (ImgExtention val : ImgExtention.values())
            if (mpFile.getContentType().indexOf(val.name().toLowerCase()) != -1)
                typeFile = val.name().toLowerCase();

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, typeFile, baos);
        baos.flush();
        byte[] resizedImg = baos.toByteArray();
        baos.close();
        return resizedImg;

    }

    public static byte[] saveImg(TaskDTO taskDTO, Boolean isTmp, String propertyValue) throws Exception
    {
        if (taskDTO == null)
            throw new Exception("Task is not created");

        MultipartFile mpFile = taskDTO.getImg();
        byte[] resizedImg = null;
        String nameImg = null;

        if (mpFile != null)
        {
            nameImg = mpFile.getOriginalFilename();
            if (!ImgUtils.checkImgExtention(nameImg))
                throw new Exception("You have to use picture with extention : png, img, jpg ");

            resizedImg = ImgUtils.resizeImg(mpFile);

            String filePath = String.format("%s/%s", propertyValue, nameImg);
            if (isTmp)
                FileUtils.cleanDirectory(new File(propertyValue));
            if (!new File(filePath).exists())
                FileUtils.writeByteArrayToFile(new File(filePath), resizedImg);

            taskDTO.setNameImg(nameImg);
        }
        return resizedImg;
    }

    public String getPathImg(byte[] arr)
    {
        return null;
    }
}
