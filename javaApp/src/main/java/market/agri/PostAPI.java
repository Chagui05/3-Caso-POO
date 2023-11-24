package market.agri;

import market.model.Post;
import market.repository.PostRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class PostAPI {
    PostRepository postRep = PostRepository.getInstance();
    @GetMapping("/posts")
	public List<Post> displayPosts() {
		List<Post> posts = postRep.findAll();
		return posts;
	}
}

	

