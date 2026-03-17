package tr.edu.gumushane.medtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tr.edu.gumushane.medtrack.entity.Role;
import tr.edu.gumushane.medtrack.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @PutMapping("/{id}")
    public Role updateRole(@PathVariable Long id, @RequestBody Role role) {
        role.setId(id);
        return roleService.saveRole(role);
    }

    @PatchMapping("/{id}/name")
    public Role patchRoleName(@PathVariable Long id, @RequestBody String name) {
        Role existing = roleService.getRoleById(id);
        existing.setName(name);
        return roleService.saveRole(existing);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
    }
}

