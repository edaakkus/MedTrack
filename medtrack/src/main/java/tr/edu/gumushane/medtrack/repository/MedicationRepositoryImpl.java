package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.Medication;

import java.util.List;
import java.util.Optional;

@Repository
public class MedicationRepositoryImpl implements MedicationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Medication save(Medication medication) {
        if (medication.getId() == null) {
            entityManager.persist(medication);
            return medication;
        }
        return entityManager.merge(medication);
    }

    @Override
    public Optional<Medication> findById(Long id) {
        Medication medication = entityManager.find(Medication.class, id);
        return Optional.ofNullable(medication);
    }

    @Override
    public List<Medication> findAll() {
        TypedQuery<Medication> query = entityManager.createQuery("SELECT m FROM Medication m", Medication.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Medication medication = entityManager.find(Medication.class, id);
        if (medication != null) {
            // Inverse side'da DELETE işlemi için ilişkileri kırma
            if (medication.getPrescriptions() != null) {
                medication.getPrescriptions().forEach(p -> {
                    if (p.getMedications() != null) {
                        p.getMedications().remove(medication);
                    }
                });
            }
            entityManager.remove(medication);
        }
    }
}

