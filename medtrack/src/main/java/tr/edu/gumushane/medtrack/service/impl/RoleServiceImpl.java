package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Role;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.RoleRepository;
import tr.edu.gumushane.medtrack.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Role getRoleById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Role not found with id: " + id));
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Role getRoleByName(String name) {
        return roleRepository.findByName(name)
            .orElseThrow(() -> new NotFoundException("Role not found with name: " + name));
    }
}

