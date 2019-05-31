package com.tasksquery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

@ComponentScan(basePackages = { "com.tasksquery.controllers" })
@Configuration
public class CoreConfig
{

    
    
    /*@Bean
    @Lazy(false)
    ApplicationContextAware applicationContext()
    {
        return new SpringApplicationContext();
    }*/
    
	/*
	 * @Bean
	 * 
	 * @Scope("singleton")
	 * 
	 * @Lazy(false)
	 * 
	 * @Qualifier(value="shop") public ShopProperties settings2() throws
	 * FileNotFoundException, IOException { ShopProperties props = new
	 * ShopProperties(); return props; }
	 */

	/*
	 * @Bean
	 * 
	 * @Scope("singleton")
	 * 
	 * @Lazy(false) public SqlSessionFactory buildSqlSessionFactory() throws
	 * IOException { String resource = "/confs/iBatis.xml"; InputStream inpStr =
	 * Resources.getResourceAsStream(resource);
	 * logger.info("The iBatis settings of Punchout was realized.");
	 * 
	 * return new SqlSessionFactoryBuilder().build(inpStr); }
	 */

    
    
   

    }
