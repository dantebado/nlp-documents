package nlp.frba.utn.documents.config;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Autowired
	Environment env;
	
	@Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("nlp.frba.utn.documents.controllers"))
          .paths(PathSelectors.any())
          .build()
          .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "NLP Documents", 
	      "API de Documentos para el proyecto de NLP de UTN.FRBA", 
	      null, 
	      null, 
	      new Contact("Hernán Borré", "", "borre.hernan@gmail.com"), 
	      null,
	      null,
	      Collections.emptyList());
	}
	
	@Configuration
	public class StaticResourceConfiguration extends WebMvcConfigurerAdapter {
	    @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/" + env.getProperty("store.local.external-access") + "**").addResourceLocations("file:/" + env.getProperty("store.local.absolute-path"));
	    }
	}
}
