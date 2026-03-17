package tr.edu.gumushane.medtrack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.edu.gumushane.medtrack.entity.User;
import tr.edu.gumushane.medtrack.repository.UserRepository;
import tr.edu.gumushane.medtrack.service.UserService;
import tr.edu.gumushane.medtrack.exception.NotFoundException;

import java.util.List;
// java.util.Optional import'u kullanılmadığı için kaldırılmıştır.

/**
 * UserService arayüzünün implementasyonudur.
 * Tüm Repository metod çağrılarının (save, findAll, findById, deleteById)
 * UserRepository arayüzünde tanımlanmış olduğu varsayılmaktadır.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public User getUserById(Long id) {
        User found = userRepository.findById(id);
        if (found == null) {
            throw new NotFoundException("Kullanıcı bulunamadı: " + id);
        }
        return found;
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteUser(Long id) {
        // Silme işleminden önce kaydın varlığını kontrol etmek için findById çağrılır.
        User user = userRepository.findById(id);
        
        if (user != null) {
            // UserRepository arayüzündeki metod imzası sayesinde bu satır artık derlenecektir.
            userRepository.deleteById(id); 
        }
    }
}
