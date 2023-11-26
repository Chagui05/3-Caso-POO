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
		redisDB.botonDeAutoDestrucion();
		
		SingleUser santi = new SingleUser("Santi", 1234, 4);
		SingleUser marco = new SingleUser("Marco",4444,1);
		SingleUser sebas = new SingleUser("Sebastián", 333, 3);
		SingleUser chris = new SingleUser("christian", 8080, 2);
		Review rev = new Review(santi, 5, "Me gusto mucho este producto", 1020);
		Tool cerrucho = new Tool("serrucho", 300, Condition.GOOD);
		
		Vector<Review> revs = new Vector<>();
		revs.add(rev);
		
		Post post = new Post(1, LocalDate.now(), "el peor serrucho", cerrucho, "https://i.pinimg.com/1200x/8b/03/1c/8b031cd4c0f0dc70cd229d5dcb1a7497.jpg",revs, 2122, santi);
		Post post1 = new Post(2, LocalDate.now(), "el serrucho", cerrucho, "https://preview.redd.it/ktxuj7clhbkb1.jpg?width=1179&format=pjpg&auto=webp&s=b1afe6e579a3c3416659d16b039824e7b0dde873",revs, 2222, marco);
		Post post2 = new Post(3, LocalDate.now(), "el serrucho más normal", cerrucho, "https://media.tenor.com/t3dLLNaI50oAAAAC/cat-cats.gif",revs, 2223, sebas);
		Post post3 = new Post(4, LocalDate.now(), "el casi mejor serrucho", cerrucho, "https://i1.sndcdn.com/artworks-zyYqA8D0BdfuyH28-WeeHrw-t500x500.jpg",revs, 2224, chris);
		Post post4 = new Post(5, LocalDate.now(), "el mejor serrucho", cerrucho, "https://us-tuna-sounds-images.voicemod.net/f3d61aaf-8fef-42a1-b4e5-784618b3b7cf-1676757488979.png",revs, 2225, santi);
		postRep.save(post);
		postRep.save(post1);
		postRep.save(post2);
		postRep.save(post3);
		postRep.save(post4);

		reviewRep.save(rev);
		userRep.save(santi);
		userRep.save(marco);
		userRep.save(sebas);
		userRep.save(chris);
		
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
