package gabrielmagm.historiatrivia_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gabrielmagm.historiatrivia_backend.models.UserModel;
import gabrielmagm.historiatrivia_backend.services.Interfaces.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public ResponseEntity <?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity <?> getUserById(@PathVariable Long id) {
        var user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/add")
    public ResponseEntity <?> createUser(@RequestBody UserModel user) {
        try {
            var newUser = userService.createUser(user);
            return ResponseEntity.ok(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity <?> updateUser(@PathVariable Long id, @RequestBody UserModel user) {
        try {
            var updatedUser = userService.updateUser(id, user);
            if (updatedUser == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(updatedUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> deleteUser(@PathVariable Long id) {
        boolean deletedUser = userService.deleteUser(id);
        if (!deletedUser) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
    
    
    
}
