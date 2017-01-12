	package pl.pioro;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@EnableAutoConfiguration
@EntityScan(basePackages = {"pl.pioro.model"})
@EnableJpaRepositories(basePackages = {"pl.pioro.dao","pl.pioro.service"})
@EnableTransactionManagement
@EnableScheduling
public class UploadApplicationConfiguration extends WebMvcConfigurerAdapter{

	  @Value("${spring.datasource.url}")
	  private String databaseUrl;
	
	  @Bean
	    public ViewResolver getViewResolver() {
	        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	        resolver.setPrefix("/WEB-INF/jsp/");
	        resolver.setSuffix(".jsp");
	        return resolver;
	    }
	  
	    @Override  
        public void addResourceHandlers(ResourceHandlerRegistry registry) {  
                registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");  
        }  
	    
	    
	    @Bean
	    public DataSource dataSource() {
	            DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
	            dataSourceBuilder.driverClassName("org.sqlite.JDBC");
	            dataSourceBuilder.url(databaseUrl);
	            return dataSourceBuilder.build();   
	    }
	    
	    
	    
}
