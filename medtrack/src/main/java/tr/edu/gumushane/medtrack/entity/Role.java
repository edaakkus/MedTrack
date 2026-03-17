package tr.edu.gumushane.medtrack.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // ManyToMany: Role <-> Member (iki yönlü, inverse side: Role)
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Member> members;

    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Set<Member> getMembers() { return members; }
    public void setMembers(Set<Member> members) { this.members = members; }
}

