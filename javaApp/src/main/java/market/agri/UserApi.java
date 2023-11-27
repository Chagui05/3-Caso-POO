package market.agri;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public int registerUser(@RequestBody User user) {

        List<User> users = userRep.findAll();
        ArrayList<Integer> userIDs = new ArrayList<Integer>();

        for(User verif : users){
            if(user.getEmail().equals(verif.getEmail()))//si encuentra un correo ya existente reotrna falso indicando que ya se registroó en algún momento
                return 0;  
            userIDs.add(verif.getId());
        }

        user.setId(generateID(userIDs));
        user.setPassword(encrypt(user.getPassword(), 4));
        user.setName(removeAT(user.getEmail()));
        userRep.save(user);
        return user.getId();
    }

    

    @PostMapping("/login")//toma los datos del formulario de logIn, los busca en redis para ver si coinciden y retorna un valor booleano según sea el caso.
    public int loginUser(@RequestBody User user) {
        List<User> users = userRep.findAll();
        for(User verif : users)
            if(user.getEmail().equals(verif.getEmail()) && user.getPassword().equals(decrypt(verif.getPassword(), 4)))
                return verif.getId();                
            
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

    public int generateFourDigitNumber() {
        Random random = new Random();
        return 1000 + random.nextInt(9000); // Número aleatorio entre 1000 y 9999 (ambos inclusive)
    }

    // Función para obtener un número de 4 dígitos que no exista en la lista proporcionada
    public int generateID(List<Integer> numbers) {
        Set<Integer> set = new HashSet<>(numbers);

        int number;
        do {
            number = generateFourDigitNumber();
        } while (set.contains(number)); // Verifica si el número generado ya está en la lista

        return number;
    }
    public String removeAT(String input) {
        // Verificar si el string contiene el carácter '@'
        if (input.contains("@")) {
            // Obtener la posición del carácter '@'
            int atIndex = input.indexOf("@");

            // Obtener la parte del string antes del carácter '@' y retornarla
            return input.substring(0, atIndex);
        } else {
            // Si no se encuentra '@', retornar el string original
            return input;
        }
    }
}
