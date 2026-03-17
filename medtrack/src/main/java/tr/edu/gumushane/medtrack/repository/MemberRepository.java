package tr.edu.gumushane.medtrack.repository;

import tr.edu.gumushane.medtrack.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    List<Member> findAll();
    void deleteById(Long id);
    Optional<Member> findByEmail(String email);
}

