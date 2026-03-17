package tr.edu.gumushane.medtrack.service;

import tr.edu.gumushane.medtrack.entity.Member;
import java.util.List;

public interface MemberService {
    Member saveMember(Member member);
    List<Member> getAllMembers();
    Member getMemberById(Long id);
    void deleteMember(Long id);
    Member getMemberByEmail(String email);
}

