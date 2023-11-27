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
		
		UserApi uApi = new UserApi();

		SingleUser santi = new SingleUser("Santi", 1234, 4, "santi@gmail.com", uApi.encrypt("santiPass", 4));
		SingleUser marco = new SingleUser("Marco",4444,1, "marco@gmail.com", uApi.encrypt("marcoPass", 4));
		SingleUser sebas = new SingleUser("Sebastián", 333, 3, "sebas@gmail.com", uApi.encrypt("sebasPass", 4));
		SingleUser chris = new SingleUser("Christian", 8080, 2,"chris@gmail.com", uApi.encrypt("chrisPass", 4));
		Review rev = new Review(santi, 0, "Odio mucho este producto", 1020);
		Review rev1 = new Review(santi, 1, "Odio este producto", 1021);
		Review rev2 = new Review(santi, 2, "Este producto es muy normal", 1022);
		Review rev3 = new Review(santi, 4, "Me gustó este producto", 1023);
		Review rev4 = new Review(santi, 5, "Me gusto mucho este producto", 1024);
		Tool cerrucho = new Tool("serrucho", 300, Condition.GOOD);
		Tool pala = new Tool("pala", 550, Condition.REGULAR);
		Tool azada = new Tool("azada", 200, Condition.GREAT);
		
		Vector<Review> revs1 = new Vector<>();
		revs1.add(rev);
		revs1.add(rev2);
		revs1.add(rev4);


		Vector<Review> revs2 = new Vector<>();
		revs2.add(rev1);
		revs2.add(rev2);
		revs2.add(rev3);
		//!--!
		//!lista de posts en perfil, nombre de perfil, register, que inicio lleve al inicio en el burger menu y que al iniciar sesion se mantenga. en posts hay algo que se debe cambiar por el user actual
		Post post = new Post(1, LocalDate.now(), "El peor serrucho", cerrucho, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQZ0RFP4OtkbCqSzkGDC39I2HqY563zyRAttA&usqp=CAU",revs1, 2122, santi);
		Post post1 = new Post(2, LocalDate.now(), "Pala", pala, "https://upload.wikimedia.org/wikipedia/commons/a/ab/Pala_de_excavaci%C3%B3n.jpg",revs2, 2222, marco);
		Post post2 = new Post(3, LocalDate.now(), "Azada nueva", azada, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS81fEg_EH3FFnv5enookYqFZ7jbilJ6gs5_A&usqp=CAU",revs1, 2223, sebas);
		Post post3 = new Post(5, LocalDate.now(), "El mejor serrucho", cerrucho, "https://www.shutterstock.com/image-photo/saw-isolated-on-white-background-260nw-425591179.jpg",revs1, 2225, chris);
		postRep.save(post);
		postRep.save(post1);
		postRep.save(post2);
		postRep.save(post3);



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
