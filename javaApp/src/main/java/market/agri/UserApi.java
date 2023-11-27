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
        //System.out.println(encrypt(currentUser.getPassword(), 4) + " " + decrypt(encrypt(currentUser.getPassword(), 4), 4));
        return currentUser;
    }
	@PostMapping("/register")//toma los datos del formulario de registro y los guarda en redis
    public User registerUser(@RequestBody User user) {
        user.setPassword(encrypt(user.getPassword(), 4));
		userRep.save(user);
        return user;
    }
    @PostMapping("/login")//toma los datos del formulario de logIn, los busca en redis para ver si coinciden y retorna un valor booleano según sea el caso.
    public int loginUser(@RequestBody User user) {
        List<User> users = userRep.findAll();
        System.out.println(user.getEmail() + " " + user.getPassword() + "\n------------------");

        for(User verif : users)
            if(user.getEmail().equals(verif.getEmail()) && user.getPassword().equals(decrypt(verif.getPassword(), 4))){
                System.out.println("coincidencia encontrada " + verif.getId());
                return verif.getId();                
            }
        System.out.println("usuario no encontrado");
        return 0;
    }

    public String encrypt(String text, int shiftKey) {
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
    public String decrypt(String text, int shiftKey) {
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
