package market.agri;

import market.model.Review;
import market.model.Post;
import market.repository.PostRepository;
import market.repository.ReviewRepository;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewAPI {

    ReviewRepository reviewRep = ReviewRepository.getInstance();
    PostRepository postRep = PostRepository.getInstance();

    @GetMapping("/reviews/{postId}")
    public List<Review> showcaseReviews(@PathVariable("postId") int postId) {
        
        //retorna los reviews del post para mostrarlos en la pág del post. En post.html debe mostrar los reveiws fetcheados de aquí 
        Post currentPost = postRep.findById(postId);
        List<Review> postReviews = currentPost.getReviews();
        return postReviews;
    }
    @PostMapping("/reviews")
    public Review createReview(@RequestBody Review review) {
        reviewRep.save(review);     
        //agregar cuando los post de la página tengan id
        //Post currentPost = postRep.findById(review.getId());
        //Vector<Review> reviews = currentPost.getReviews();
        //reviews.add(review);
        //currentPost.setReviews(reviews);
        //postRep.save(currentPost);
        return review;
    }
    /* prueba para postman
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
    "likes": -1
}
     */
}
