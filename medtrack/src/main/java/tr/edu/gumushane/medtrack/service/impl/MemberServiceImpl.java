package tr.edu.gumushane.medtrack.service.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Member;
import tr.edu.gumushane.medtrack.exception.NotFoundException;
import tr.edu.gumushane.medtrack.repository.MemberRepository;
import tr.edu.gumushane.medtrack.service.MemberService;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional // Veritabanında değişiklik yapıyor (save işlemi)
    public Member saveMember(Member member) {
        // Parolayı hashle (yeni kayıt veya parola değişikliği durumunda)
        if (member.getId() == null) {
            // Yeni member, parolayı hashle
            member.setPassword(passwordEncoder.encode(member.getPassword()));
        } else {
            // Mevcut member - parola değişmiş mi kontrol et
            Member existing = memberRepository.findById(member.getId()).orElse(null);
            if (existing != null) {
                // Eğer parola hashlenmiş formatta değilse (bcrypt hash'i $2a$ ile başlar)
                // veya mevcut paroladan farklıysa, yeni parolayı hashle
                String newPassword = member.getPassword();
                String existingPassword = existing.getPassword();
                
                if (newPassword != null && !newPassword.equals(existingPassword)) {
                    // Parola değişmiş, hashle
                    if (!newPassword.startsWith("$2a$") && !newPassword.startsWith("$2b$")) {
                        member.setPassword(passwordEncoder.encode(newPassword));
                    }
                } else if (newPassword == null || newPassword.isEmpty()) {
                    // Parola boş, mevcut parolayı koru
                    member.setPassword(existingPassword);
                }
            } else {
                // Member bulunamadı, yeni gibi davran
                member.setPassword(passwordEncoder.encode(member.getPassword()));
            }
        }
        return memberRepository.save(member);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Member not found with id: " + id));
    }

    @Override
    @Transactional // DELETE metodu - veritabanında değişiklik yapıyor
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    // GET metodu - @Transactional yok (sadece okuma)
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
            .orElseThrow(() -> new NotFoundException("Member not found with email: " + email));
    }
}

