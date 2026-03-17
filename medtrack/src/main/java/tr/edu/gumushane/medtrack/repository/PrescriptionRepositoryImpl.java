package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.Prescription;

import java.util.List;
import java.util.Optional;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Prescription save(Prescription prescription) {
        if (prescription.getId() == null) {
            entityManager.persist(prescription);
            return prescription;
        }
        return entityManager.merge(prescription);
    }

    @Override
    public Optional<Prescription> findById(Long id) {
        Prescription prescription = entityManager.find(Prescription.class, id);
        return Optional.ofNullable(prescription);
    }

    @Override
    public List<Prescription> findAll() {
        TypedQuery<Prescription> query = entityManager.createQuery("SELECT p FROM Prescription p", Prescription.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Prescription prescription = entityManager.find(Prescription.class, id);
        if (prescription != null) {
            // Inverse side'da DELETE işlemi için ilişkileri kırma
            if (prescription.getMember() != null) {
                prescription.getMember().setPrescription(null);
            }
            if (prescription.getMedications() != null) {
                prescription.getMedications().clear();
            }
            if (prescription.getDoctor() != null) {
                prescription.setDoctor(null);
            }
            entityManager.remove(prescription);
        }
    }
}

