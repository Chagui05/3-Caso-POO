package market.agri;

import market.model.Review;
import market.model.Post;
import market.repository.PostRepository;
import market.repository.ReviewRepository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Vector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewAPI {

    ReviewRepository reviewRep = ReviewRepository.getInstance();
    PostRepository postRep = PostRepository.getInstance();

    @GetMapping("/reviews/{postId}")//obtiene la información de las reviews de un post, utilizando el id único que deberían tener los posts. Muestra la lista reviews de un post    
    public Post showcaseReviews(@PathVariable("postId") int postId) {
        //retorna los reviews del post para mostrarlos en la pág del post. En post.html debe mostrar los reveiws fetcheados de aquí 
        Post currentPost = postRep.findById(postId);
        return currentPost;
    }
    //Toma los valores del formulario para crear una review y los guarda en redis
    @PostMapping("/reviews/{postId}")
    public Review createReview(@RequestBody Review review, @PathVariable int postId) {
        reviewRep.save(review);     
        Post currentPost = postRep.findById(postId);
        Vector<Review> reviews = currentPost.getReviews();
        reviews.add(review);
        currentPost.setReviews(reviews);
        postRep.save(currentPost);
        return review;
    }
    /* prueba para postman. esto es lo que debería tomar del formulario.
     {
    "user": {
            "type": "singleUser",
            "name": "Marco",
            "id": 4444,
            "userRating": 1.0,
            "allRatings": null,
            "wallet": 0.0,
            "commision": 100.00
        },
    "id": 333,
    "rating": 2.3,
    "allRatings": [1.2,3],
    "comment": "mal producto",
    "likes": 
}
     */
}