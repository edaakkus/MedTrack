package tr.edu.gumushane.medtrack.repository;

import tr.edu.gumushane.medtrack.entity.User;
import java.util.List;

/**
 * Kullanıcı verilerine erişim arayüzü (Non-JPA).
 * Uygulama, bu arayüzü (örneğin JdbcTemplate kullanarak) implemente etmelidir.
 */
public interface UserRepository { // Repository'ler standart olarak arayüz (interface) olmalıdır.

    
    //Yeni bir kullanıcı kaydeder veya mevcut bir kullanıcıyı günceller.
    
    User save(User user);

    
    //Belirtilen ID'ye sahip kullanıcıyı getirir.
    
    User findById(Long id);

    
    //Tüm kullanıcıları listeler.
    
    List<User> findAll();


    //Belirtilen ID'ye sahip kullanıcıyı siler. Bu metod imzası, Service katmanındaki derleme hatasını çözmek için zorunludur.

    void deleteById(Long id);
}