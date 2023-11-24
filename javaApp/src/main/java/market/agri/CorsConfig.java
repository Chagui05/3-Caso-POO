package market.agri;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/posts")
                .allowedOrigins("*") // Agrega aquí los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/reviews/{postId}")
                .allowedOrigins("*") // Agrega aquí los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/reviews")
                .allowedOrigins("*") // Agrega aquí los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/user/{userID}")
                .allowedOrigins("*") // Agrega aquí los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        registry.addMapping("/register")
                .allowedOrigins("*") // Agrega aquí los orígenes permitidos
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
        
    }
}


