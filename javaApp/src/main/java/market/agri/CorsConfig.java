package market.agri;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/reviews/{postId}")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/reviews")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/user/{userID}")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/register")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/salemade/{postId}")
                .allowedOrigins("*")  
                .allowedMethods("GET", "POST", "PUT", "DELETE") 
                .allowedHeaders("*");
        registry.addMapping("/login")
                .allowedOrigins("*") 
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        
    }
}


