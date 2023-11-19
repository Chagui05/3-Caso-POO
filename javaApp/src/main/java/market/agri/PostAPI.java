package market.agri;

import market.model.Condition;
import market.model.Post;
import market.model.Product;
import market.model.Review;
import market.model.SingleUser;
import market.model.Tool;
import market.repository.PostRepository;
import market.repository.PostRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

@RestController
public class PostAPI {
    PostRepository postRep = PostRepository.getInstance();
    @GetMapping("/posts")
	public List<Post> displayPosts() {//no puede sacar los datos de redis

		//============================================================== abajo lo único que va
		List<Post> posts = postRep.findAll();
		return posts;
	}
}



