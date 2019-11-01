package nlp.frba.utn.documents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticPathConfig {
	
	@Autowired
	Environment env;
	
	@Configuration
	public class StaticResourceConfiguration implements WebMvcConfigurer {
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/" + env.getProperty("store.local.external-access") + "**").addResourceLocations("file:" + env.getProperty("store.local.absolute-path"));
	    }
	}
	
}
