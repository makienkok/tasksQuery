package com.tasksquery.controllers;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.webflow.engine.model.OutputModel;

import com.tasksquery.models.TaskDTO;

@Controller
public class ImgsController {

	@Value("${imgsDir}")
	String propertyValue;
	
	@Value("${tmpImgsDir}")
	String tmpImgsDir;
	
	@RequestMapping(value = {
			"/allimgs/{nameFolder}/{nameImg}/"
	}, method = RequestMethod.GET)
	public void submitNewTask(@PathVariable(name = "nameFolder") String nameFolder, @PathVariable String nameImg, HttpServletResponse response) throws Exception
	{
		
		File f = new File(String.format("%s/%s", propertyValue.indexOf(nameFolder)!=-1?propertyValue:tmpImgsDir, nameImg));
		
		InputStream io = new FileInputStream(f);
		OutputStream os = response.getOutputStream();
		int i =-1;
		while((i= io.read())!=-1)
			os.write(i);
		io.close();
		os.flush();
		os.close();
	}
	
}

