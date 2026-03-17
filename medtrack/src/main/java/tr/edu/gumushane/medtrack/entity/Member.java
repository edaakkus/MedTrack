package tr.edu.gumushane.medtrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    // OneToMany: Member -> HealthRecord (iki yönlü, owning side: HealthRecord)
    @OneToMany(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<HealthRecord> healthRecords;

    // OneToOne: Member -> Prescription (iki yönlü, inverse side: Member)
    // Prescription tarafında owning side olduğu için mappedBy kullanıyoruz
    @OneToOne(mappedBy = "member", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JsonIgnore
    private Prescription prescription;

    // ManyToMany: Member <-> Role (iki yönlü, owning side: Member)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(
        name = "member_roles",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Member() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }

    public List<HealthRecord> getHealthRecords() { return healthRecords; }
    public void setHealthRecords(List<HealthRecord> healthRecords) { this.healthRecords = healthRecords; }

    public Prescription getPrescription() { return prescription; }
    public void setPrescription(Prescription prescription) { this.prescription = prescription; }

    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }
}

