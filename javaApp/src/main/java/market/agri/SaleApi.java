package market.agri;



import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import market.control.SaleControl;
import market.model.Post;
import market.model.Sale;
import market.model.SaleRequest;
import market.repository.PostRepository;
import market.repository.SaleRepository;

@RestController
public class SaleApi {
    SaleControl saleCont = new SaleControl(SaleRepository.getInstance());   
    PostRepository postCont = PostRepository.getInstance();
    
    @PostMapping("/salemade/{postId}")//guarda la venta hecha
    public int registerUser(@PathVariable("postId") int postId, @RequestBody SaleRequest pSale) { 	
    	Post post = postCont.findById(postId);
    	int id = saleCont.findAll().size();
    	System.out.println(post.getDate());
    	Sale sale = new Sale(post, id,pSale.getQuantity() , post.getUser());	
    	saleCont.save(sale);
    	System.out.println("Sale registerd");
        return id;
    }
}
