package market.agri;

import java.time.LocalDate;
import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;

import market.model.Condition;
import market.model.Post;
import market.model.Review;
import market.model.SingleUser;
import market.model.Tool;
import market.model.User;

public class UserApi {
    @GetMapping("/user")
	public User displayUser() {//no puede sacar los datos de redis

		SingleUser santi = new SingleUser("Santi", 1234, 4);
		Review rev = new Review(santi, 5, "Me gusto mucho este producto", 1020);
		Tool cerrucho = new Tool("serrucho", 300, Condition.GOOD);	
		Vector<Review> revs = new Vector<>();
        Vector<Post> posts = new Vector<>();
		revs.add(rev);
		Post post = new Post(1, LocalDate.now(), "el peor serrucho", cerrucho, "unUrl.com",revs, 2221);
		Post post4 = new Post(5, LocalDate.now(), "el mejor serrucho", cerrucho, "unUrl.com",revs, 2225);
        posts.add(post);
        posts.add(post4);

        santi.setPosts(posts);
		//============================================================== abajo lo único que va
		return santi;
	}
}
