package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role save(Role role) {
        if (role.getId() == null) {
            entityManager.persist(role);
            return role;
        }
        return entityManager.merge(role);
    }

    @Override
    public Optional<Role> findById(Long id) {
        Role role = entityManager.find(Role.class, id);
        return Optional.ofNullable(role);
    }

    @Override
    public List<Role> findAll() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            // Inverse side'da DELETE işlemi için ilişkileri kırma
            if (role.getMembers() != null) {
                role.getMembers().forEach(m -> {
                    if (m.getRoles() != null) {
                        m.getRoles().remove(role);
                    }
                });
            }
            entityManager.remove(role);
        }
    }

    @Override
    public Optional<Role> findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
            "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        List<Role> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
}

