package tr.edu.gumushane.medtrack.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.gumushane.medtrack.entity.Member;
import tr.edu.gumushane.medtrack.entity.Role;
import tr.edu.gumushane.medtrack.repository.MemberRepository;
import tr.edu.gumushane.medtrack.repository.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private boolean alreadySetup = false;

    @Override
    @Transactional
    public void onApplicationEvent(@NonNull ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        init();
        alreadySetup = true;
    }

    private void init() {
        // Rolleri oluştur
        Role adminRole = roleRepository.findByName("ADMIN").orElse(null);
        if (adminRole == null) {
            adminRole = new Role("ADMIN");
            roleRepository.save(adminRole);
        }

        Role doctorRole = roleRepository.findByName("DOCTOR").orElse(null);
        if (doctorRole == null) {
            doctorRole = new Role("DOCTOR");
            roleRepository.save(doctorRole);
        }

        Role patientRole = roleRepository.findByName("PATIENT").orElse(null);
        if (patientRole == null) {
            patientRole = new Role("PATIENT");
            roleRepository.save(patientRole);
        }

        // Admin kullanıcısı oluştur
        if (!memberRepository.findByEmail("admin@medtrack.com").isPresent()) {
            Member admin = new Member();
            admin.setName("Admin User");
            admin.setEmail("admin@medtrack.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEnabled(true);
            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            admin.setRoles(adminRoles);
            memberRepository.save(admin);
        }

        // Doctor kullanıcısı oluştur
        if (!memberRepository.findByEmail("doctor@medtrack.com").isPresent()) {
            Member doctor = new Member();
            doctor.setName("Doctor User");
            doctor.setEmail("doctor@medtrack.com");
            doctor.setPassword(passwordEncoder.encode("doctor123"));
            doctor.setEnabled(true);
            Set<Role> doctorRoles = new HashSet<>();
            doctorRoles.add(doctorRole);
            doctor.setRoles(doctorRoles);
            memberRepository.save(doctor);
        }

        // Patient kullanıcısı oluştur
        if (!memberRepository.findByEmail("patient@medtrack.com").isPresent()) {
            Member patient = new Member();
            patient.setName("Patient User");
            patient.setEmail("patient@medtrack.com");
            patient.setPassword(passwordEncoder.encode("patient123"));
            patient.setEnabled(true);
            Set<Role> patientRoles = new HashSet<>();
            patientRoles.add(patientRole);
            patient.setRoles(patientRoles);
            memberRepository.save(patient);
        }

        // Basit kimlik doğrulama için "user/password" kullanıcısı (gereksinimlerde belirtilen)
        if (!memberRepository.findByEmail("user").isPresent()) {
            Member simpleUser = new Member();
            simpleUser.setName("Simple User");
            simpleUser.setEmail("user");
            simpleUser.setPassword(passwordEncoder.encode("password"));
            simpleUser.setEnabled(true);
            Set<Role> allRoles = new HashSet<>();
            allRoles.add(adminRole);
            allRoles.add(doctorRole);
            allRoles.add(patientRole);
            simpleUser.setRoles(allRoles);
            memberRepository.save(simpleUser);
        }
    }
}

