package market.agri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Image;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Vector;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import market.model.Condition;
import market.model.Post;
import market.model.Product;
import market.model.Review;
import market.model.SingleUser;
import market.model.Tool;
import market.model.User;
import market.repository.ConnectDBRepository;
import market.repository.PostRepository;
import market.repository.ReviewRepository;
import market.repository.UserRepository;

@SpringBootApplication
public class AgriApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriApplication.class, args);
		

		ConnectDBRepository redisDB = ConnectDBRepository.getInstance();
		UserRepository userRep = UserRepository.getInstance();
		ReviewRepository reviewRep = ReviewRepository.getInstance();
		PostRepository postRep = PostRepository.getInstance();
		
		SingleUser santi = new SingleUser("Santi", 1234, 4);
		Review rev = new Review(santi, 5, "Me gusto mucho este producto", 1020);
		
		Tool cerrucho = new Tool("cerru", 300, Condition.GOOD);
		
		Vector<Review> revs = new Vector<>();
		revs.add(rev);
		
		Post post = new Post(5, LocalDate.now(), "el mejor cerrucho", cerrucho, "unUrl.com",revs, 2222);
		
		reviewRep.save(rev);
		userRep.save(santi);
		postRep.save(post);
		
		System.out.println(reviewRep.findById(1020).getComment());
		System.out.println(userRep.findById(1234).getName());
		System.out.println(postRep.findById(2222).getDescription());
		redisDB.botonDeAutoDestrucion();
		

		
		
		
		
		
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
}
