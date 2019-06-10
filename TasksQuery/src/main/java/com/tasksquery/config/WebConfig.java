package com.tasksquery.config;

import java.io.File;

import javax.servlet.ServletContext;
import javax.validation.Validator;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.FileCleanerCleanup;
import org.apache.commons.io.FileCleaningTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan("com.tasksquery")
public class WebConfig implements WebMvcConfigurer {

	

	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean(name = "simpleMappingExceptionResolver")
    public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver()
    {
        SimpleMappingExceptionResolver resolver = new MyMappingExceptionResolver();
        return resolver;
    }

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/css/**")) {
			registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		}
		if (!registry.hasMappingForPattern("/scripts/**")) {
			registry.addResourceHandler("/scripts/**").addResourceLocations("classpath:/scripts/");
		}
		if (!registry.hasMappingForPattern("/pictures/**")) {
			registry.addResourceHandler("/pictures/**").addResourceLocations("classpath:/pictures/");
		}
		if (!registry.hasMappingForPattern("/imgs/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/imgs/");
		}
		if (!registry.hasMappingForPattern("/tmpImgs/**")) {
			registry.addResourceHandler("/static/**").addResourceLocations("classpath:/tmpImgs/");
		}
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(applicationContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setOrder(0);
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode(TemplateMode.HTML);

		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	public ViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setCharacterEncoding("UTF-8");
		viewResolver.setViewNames(new String[] { "*" });
		viewResolver.setCache(false);
		return viewResolver;
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

		configurer.defaultContentType(MediaType.TEXT_HTML).mediaType("xml", MediaType.APPLICATION_XML).mediaType("json",
				MediaType.APPLICATION_JSON);
		/* configurer.defaultContentType(MediaType.APPLICATION_XML) */;
	}

	/*
	 * @Bean public MessageSource messageSource() {
	 * ReloadableResourceBundleMessageSource messageSource = new
	 * ReloadableResourceBundleMessageSource();
	 * 
	 * messageSource.setBasenames("/WEB-INF/messages/messages");
	 * messageSource.setUseCodeAsDefaultMessage(true);
	 * messageSource.setDefaultEncoding("UTF-8"); // # -1 : never reload, 0 always
	 * reload messageSource.setCacheSeconds(0); return messageSource; }
	 */

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() throws Exception {
		int MAX_UPLOAD_SIZE = 30 * 1024 * 1024;// 30 megabytes
		CommonsMultipartResolver mr = new CommonsMultipartResolver();
		mr.setMaxUploadSize(MAX_UPLOAD_SIZE);
		mr.setMaxUploadSizePerFile(MAX_UPLOAD_SIZE / 2);
		mr.setUploadTempDir(new FileSystemResource("src/main/webapp/WEB-INF/tmp"));

		return mr;
	}

	public static DiskFileItemFactory newDiskFileItemFactory(ServletContext context, File repository) {
		FileCleaningTracker fileCleaningTracker = FileCleanerCleanup.getFileCleaningTracker(context);
		DiskFileItemFactory factory = new DiskFileItemFactory(DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD, repository);
		factory.setFileCleaningTracker(fileCleaningTracker);
		return factory;
	}

	/*
	 * @Bean public Validator validator() { return new LocalValidatorFactoryBean();
	 * }
	 */
}
