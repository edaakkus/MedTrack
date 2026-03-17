package tr.edu.gumushane.medtrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.gumushane.medtrack.entity.Member;
import tr.edu.gumushane.medtrack.repository.MemberRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Member not found with email: " + email));

        if (!member.getEnabled()) {
            throw new UsernameNotFoundException("Member is disabled: " + email);
        }

        Collection<GrantedAuthority> authorities = member.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
            .collect(Collectors.toList());

        return User.builder()
            .username(member.getEmail())
            .password(member.getPassword())
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(!member.getEnabled())
            .build();
    }
}

