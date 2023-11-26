package market.agri;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
	@PostMapping("/register")//toma los datos del formulario de registro y los guarda en redis
    public User registerUser(@RequestBody User user) {
        user.setPassword(encrypt(user.getPassword(), 4));
		userRep.save(user);
        return user;
    }
    @PostMapping("/login")//toma los datos del formulario de logIn y los busca en redis para ver si son correctos.
    public User loginUser(@RequestBody User user) {
        List<User> users = userRep.findAll();
        for(User verif : users)
            if(user.getEmail()==verif.getEmail()&&decrypt(user.getPassword(), 4)==decrypt(verif.getPassword(), 4))
                return verif;
        return null;//si retorna null es que no se encontró el usuario en la base de datos. debe crear una cuenta.
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
    }*/

    public static String encrypt(String text, int shiftKey) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char)(((int)text.charAt(i) + shiftKey - 65) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char)(((int)text.charAt(i) + shiftKey - 97) % 26 + 97);
                result.append(ch);
            } else {
                result.append(text.charAt(i));
            }
        }

        return result.toString();
    }
    public static String decrypt(String text, int shiftKey) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                char ch = (char)(((int)text.charAt(i) - shiftKey - 65 + 26) % 26 + 65);
                result.append(ch);
            } else if (Character.isLowerCase(text.charAt(i))) {
                char ch = (char)(((int)text.charAt(i) - shiftKey - 97 + 26) % 26 + 97);
                result.append(ch);
            } else {
                result.append(text.charAt(i));
            }
        }

        return result.toString();
    }
}
