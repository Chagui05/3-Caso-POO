package market.agri;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import market.model.User;
import market.repository.UserRepository;

@RestController
public class UserApi {
	UserRepository userRep = UserRepository.getInstance();

    @GetMapping("/user/{userID}")// para la pantalla de perfil, toma todos los datos de redis y los muestra en el apartado de perfil
    public User showcaseUser(@PathVariable("userID") int userID) {
		User currentUser = userRep.findById(userID);
        return currentUser;
    }

	@PostMapping("/register")//toma los datos de la pantalla de login y los guarda en redis
    public User registerUser(@RequestBody User user) {
		userRep.save(user);
        return user;
    }
    /* prueba para postman
    {
    "type": "singleUser",
    "name": "Marco",
    "id": 4444,
    "userRating": 4.0,
    "allRatings": null,
    "wallet": 0.0,
    "posts": null,
    "commision": 0.04
}

     */
}
