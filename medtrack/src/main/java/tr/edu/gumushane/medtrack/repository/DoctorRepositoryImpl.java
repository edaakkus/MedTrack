package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.Doctor;

import java.util.List;
import java.util.Optional;

@Repository
public class DoctorRepositoryImpl implements DoctorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Doctor save(Doctor doctor) {
        if (doctor.getId() == null) {
            entityManager.persist(doctor);
            return doctor;
        }
        return entityManager.merge(doctor);
    }

    @Override
    public Optional<Doctor> findById(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        return Optional.ofNullable(doctor);
    }

    @Override
    public List<Doctor> findAll() {
        TypedQuery<Doctor> query = entityManager.createQuery("SELECT d FROM Doctor d", Doctor.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        if (doctor != null) {
            // Tek yönlü ilişki olduğu için Prescription'ları bulup doctor referansını null yap
            TypedQuery<tr.edu.gumushane.medtrack.entity.Prescription> query = entityManager.createQuery(
                "SELECT p FROM Prescription p WHERE p.doctor.id = :doctorId", 
                tr.edu.gumushane.medtrack.entity.Prescription.class);
            query.setParameter("doctorId", id);
            List<tr.edu.gumushane.medtrack.entity.Prescription> prescriptions = query.getResultList();
            prescriptions.forEach(p -> p.setDoctor(null));
            entityManager.remove(doctor);
        }
    }
}

