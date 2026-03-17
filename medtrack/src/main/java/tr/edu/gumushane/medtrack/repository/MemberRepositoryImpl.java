package tr.edu.gumushane.medtrack.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import tr.edu.gumushane.medtrack.entity.Member;

import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Member save(Member member) {
        if (member.getId() == null) {
            entityManager.persist(member);
            return member;
        }
        return entityManager.merge(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = entityManager.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public List<Member> findAll() {
        TypedQuery<Member> query = entityManager.createQuery("SELECT m FROM Member m", Member.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(Long id) {
        Member member = entityManager.find(Member.class, id);
        if (member != null) {
            // Inverse side'da DELETE işlemi için ilişkileri kırma
            if (member.getPrescription() != null) {
                member.getPrescription().setMember(null);
            }
            if (member.getRoles() != null) {
                member.getRoles().clear();
            }
            if (member.getHealthRecords() != null) {
                member.getHealthRecords().forEach(hr -> hr.setMember(null));
            }
            entityManager.remove(member);
        }
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        TypedQuery<Member> query = entityManager.createQuery(
            "SELECT m FROM Member m WHERE m.email = :email", Member.class);
        query.setParameter("email", email);
        List<Member> results = query.getResultList();
        return results.isEmpty() ? Optional.empty() : Optional.of(results.get(0));
    }
}

