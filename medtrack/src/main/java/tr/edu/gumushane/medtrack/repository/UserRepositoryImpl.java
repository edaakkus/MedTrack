package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.User;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User save(User user) {
		if (user.getId() == null) {
			entityManager.persist(user);
			return user;
		}
		return entityManager.merge(user);
	}

	@Override
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
		return query.getResultList();
	}

	@Override
	public void deleteById(Long id) {
		User found = entityManager.find(User.class, id);
		if (found != null) {
			entityManager.remove(found);
		}
	}
}

