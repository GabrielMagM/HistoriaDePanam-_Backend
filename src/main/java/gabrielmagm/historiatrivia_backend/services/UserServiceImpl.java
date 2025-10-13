package gabrielmagm.historiatrivia_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gabrielmagm.historiatrivia_backend.repository.TypeUserRepository;
import gabrielmagm.historiatrivia_backend.repository.UserRepository;
import gabrielmagm.historiatrivia_backend.services.Interfaces.UserService;
import gabrielmagm.historiatrivia_backend.models.TypeUserModel;
import gabrielmagm.historiatrivia_backend.models.UserModel;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TypeUserRepository typeUserRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return (List<UserModel>) userRepository.findAll();
    }


    @Override
    public UserModel getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserModel createUser(UserModel user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("El correo ya está en uso");
        }

        if(user.getTypeUser() == null) {
            TypeUserModel normalType = typeUserRepository.findByName("NORMAL");
            if (normalType ==null) {
                throw new IllegalStateException("El tipo de usuario 'NORMAL' no existe en la base de datos");
            } 
            user.setTypeUser(normalType);
        }else {
             // Validar que el tipo de usuario exista
            Long typeId = user.getTypeUser().getId();
            TypeUserModel type = typeUserRepository.findById(typeId)
                    .orElseThrow(() -> new IllegalArgumentException("El tipo de usuario no existe"));
            user.setTypeUser(type);
        }

        return userRepository.save(user);
    }

    @Override
    public UserModel updateUser(Long id, UserModel user) {
        UserModel existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            return null;
        }
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());
        existingUser.setTypeUser(user.getTypeUser());
        return userRepository.save(existingUser);
    }

    @Override
    public UserModel delete(Long id) {
        UserModel user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;    
    }
}
    
