package com.tasksquery.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class MyMappingExceptionResolver extends SimpleMappingExceptionResolver {

	public MyMappingExceptionResolver() 
	{
		setWarnLogCategory(MyMappingExceptionResolver.class.getName());
	}

	@Override
	protected ModelAndView doResolveException(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex) 
	{

		ModelAndView mav = null;
		ModelMap mp = null;

		if (req != null && req.getHeader("X-Requested-With") != null) {
			mav = new ModelAndView("ajaxError");
			mp = mav.getModelMap();
			mp.addAttribute("ajaxException", ex.getMessage());
		} else {
			mav = new ModelAndView("error");
			mp = mav.getModelMap();
			mp.addAttribute("error", ex.getMessage());
		}

		return mav;
	}

}