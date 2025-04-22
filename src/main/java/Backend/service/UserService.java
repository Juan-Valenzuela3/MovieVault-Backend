package Backend.service;

import Backend.entity.Users;
import Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Método para guardar usuario en la base de datos
    public Users saveUser(String email, String name) {
        // Verificar si el usuario ya existe
        if (userRepository.existsByEmail(email)) {
            throw new RuntimeException("El usuario ya existe");
        }

        Users newUser = Users.builder()
                .email(email)
                .name(name)
                .build();
        return userRepository.save(newUser);
    }

    public Users findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Users updateUser(Users user) {
        return userRepository.save(user);
    }
}
