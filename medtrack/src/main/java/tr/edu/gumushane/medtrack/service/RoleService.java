package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.Role;
import java.util.List;

public interface RoleService {
    Role saveRole(Role role);
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void deleteRole(Long id);
    Role getRoleByName(String name);
}

