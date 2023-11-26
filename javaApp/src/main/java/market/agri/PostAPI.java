package market.agri;

import market.model.Post;
import market.repository.PostRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PostAPI {
    PostRepository postRep = PostRepository.getInstance();
    @GetMapping("/posts")//muestra los posts en la pantalla principal sacandolos de redis
	public List<Post> displayPosts() {
		List<Post> posts = postRep.findAll();
		return posts;
	}
	@PostMapping("/posts")
	public List<Post> getPosts() {
		List<Post> posts = postRep.findAll();
		return posts;
	}

}

	

