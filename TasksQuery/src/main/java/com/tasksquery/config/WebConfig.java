package com.tasksquery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@ComponentScan("com.tasksquery")
public class WebConfig implements WebMvcConfigurer 
{
    
	/*
	 * @Bean(name = "simpleMappingExceptionResolver") public
	 * SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
	 * SimpleMappingExceptionResolver resolver = new MyMappingExceptionResolver();
	 * return resolver; }
	 */

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        if(!registry.hasMappingForPattern("/css/**"))
        {
            registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        }
        if(!registry.hasMappingForPattern("/javascripts/**"))
        {
            registry.addResourceHandler("/javascripts/**").addResourceLocations("classpath:/javascripts/");
        }
        if(!registry.hasMappingForPattern("/pictures/**"))
        {
            registry.addResourceHandler("/pictures/**").addResourceLocations("classpath:/pictures/");
        }
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver()
    {
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
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ViewResolver viewResolver()
    {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setCharacterEncoding("UTF-8");
        viewResolver.setViewNames(new String[] { "*"}); 
        viewResolver.setCache(false);
        return viewResolver;
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
      
        configurer.defaultContentType(MediaType.TEXT_HTML).
        mediaType("xml", MediaType.APPLICATION_XML).
        mediaType("json", MediaType.APPLICATION_JSON);
        /*configurer.defaultContentType(MediaType.APPLICATION_XML)*/;
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

}
