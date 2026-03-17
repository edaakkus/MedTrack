package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.HealthRecord;

import java.util.List;

@Repository
public class HealthRecordRepositoryImpl implements HealthRecordRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public HealthRecord save(HealthRecord record) {
        if (record.getId() == null) {
            entityManager.persist(record);
            return record;
        }
        return entityManager.merge(record);
    }

    @Override
    public HealthRecord findById(Long id) {
        return entityManager.find(HealthRecord.class, id);
    }

    @Override
    public List<HealthRecord> findAll() {
        TypedQuery<HealthRecord> query = entityManager.createQuery("SELECT h FROM HealthRecord h", HealthRecord.class);
        return query.getResultList();
    }

    @Override
    public List<HealthRecord> findByMemberId(Long memberId) {
        TypedQuery<HealthRecord> query = entityManager.createQuery(
                "SELECT h FROM HealthRecord h WHERE h.member.id = :memberId", HealthRecord.class);
        query.setParameter("memberId", memberId);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        HealthRecord record = entityManager.find(HealthRecord.class, id);
        if (record != null) {
            entityManager.remove(record);
        }
    }

    @Override
    public List<HealthRecord> findLast7DaysByMemberId(Long memberId) {
        TypedQuery<HealthRecord> query = entityManager.createQuery(
                "SELECT h FROM HealthRecord h WHERE h.member.id = :memberId " +
                "AND h.recordDate >= :sevenDaysAgo ORDER BY h.recordDate ASC", 
                HealthRecord.class);
        query.setParameter("memberId", memberId);
        query.setParameter("sevenDaysAgo", java.time.LocalDate.now().minusDays(6));
        return query.getResultList();
    }

    @Override
    public void deleteLast7DaysByMemberId(Long memberId) {
        TypedQuery<HealthRecord> query = entityManager.createQuery(
                "SELECT h FROM HealthRecord h WHERE h.member.id = :memberId " +
                "AND h.recordDate >= :sevenDaysAgo", 
                HealthRecord.class);
        query.setParameter("memberId", memberId);
        query.setParameter("sevenDaysAgo", java.time.LocalDate.now().minusDays(6));
        List<HealthRecord> records = query.getResultList();
        for (HealthRecord record : records) {
            entityManager.remove(record);
        }
    }
}

