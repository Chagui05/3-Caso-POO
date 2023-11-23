package market.agri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.Image;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
		
		Post post = new Post(1, LocalDate.now(), "el peor serrucho", cerrucho, "unUrl.com",revs, 2221);
		Post post1 = new Post(2, LocalDate.now(), "el serrucho", cerrucho, "unUrl.com",revs, 2222);
		Post post2 = new Post(3, LocalDate.now(), "el serrucho más normal", cerrucho, "unUrl.com",revs, 2223);
		Post post3 = new Post(4, LocalDate.now(), "el casi mejor serrucho", cerrucho, "unUrl.com",revs, 2224);
		Post post4 = new Post(5, LocalDate.now(), "el mejor serrucho", cerrucho, "unUrl.com",revs, 2225);
		
		postRep.save(post);
		postRep.save(post1);
		postRep.save(post2);
		postRep.save(post3);
		postRep.save(post4);

		reviewRep.save(rev);
		userRep.save(santi);
		
		List<Post> posts = postRep.findAll();
		System.out.println(posts.toString());
		
		
		System.out.println(reviewRep.findById(1020).getComment());
		System.out.println(userRep.findById(1234).getName());
		System.out.println(postRep.findById(2222).getDescription());
		
		System.out.println("PRUEBA DISTINTA");
		
		SingleUser nacho = new SingleUser("nacho", 111, 5);
		userRep.save(nacho);
		System.out.println(userRep.findById(111).getName());
		
		nacho.setName("manolo");
		userRep.save(nacho);
		
		System.out.println(userRep.findById(111).getName());
		
		System.out.println("PRUEBA DISTINTA");
		userRep.findAll().forEach(element -> System.out.println(element.getName()));
		
		
		
		
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
