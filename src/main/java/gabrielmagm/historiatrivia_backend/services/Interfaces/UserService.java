package gabrielmagm.historiatrivia_backend.services.Interfaces;

import java.util.List;

import gabrielmagm.historiatrivia_backend.models.UserModel;

public interface UserService {
    List<UserModel> getAllUsers();

    UserModel getUserById(Long id);
    UserModel createUser(UserModel user);
    UserModel updateUser(Long id, UserModel user);
    boolean deleteUser(Long id);
}
