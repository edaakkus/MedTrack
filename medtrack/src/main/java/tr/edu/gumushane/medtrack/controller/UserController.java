package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import tr.edu.gumushane.medtrack.entity.User;
import tr.edu.gumushane.medtrack.service.UserService;
import tr.edu.gumushane.medtrack.config.GreetingProvider;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    @Lazy
    @Qualifier("fancyGreeting")
    private GreetingProvider greetingProvider;

    // Basit deger donduren endpoint: @Value ile AppConfig'ten beslenen bean'i kullanir
    @GetMapping("/greet/{name}")
    public String greet(@PathVariable String name) {
        return greetingProvider.greet(name);
    }

    // Tüm kullanıcıları getir
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // ID'ye göre kullanıcı getir
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Yeni kullanıcı oluştur
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    // Kullanıcı güncelle (PUT)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setId(id);
        return userService.saveUser(user);
    }

    // Kullanıcı kısmi güncelle (PATCH) - sadece ad güncellemesi örneği
    @PatchMapping("/{id}/name")
    public User patchUserName(@PathVariable Long id, @RequestBody String name) {
        User existing = userService.getUserById(id);
        existing.setName(name);
        return userService.saveUser(existing);
    }

    // Kullanıcı sil
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}