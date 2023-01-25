package uz.forLearn.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.forLearn.demo.entity.enums.RoleName;
import uz.forLearn.demo.entity.util.Auditable;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role extends Auditable implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Column(name = "description")
    private String description;


    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "role_permissiond",
            joinColumns ={@JoinColumn(name = "role_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "id")}

    )
    private Set<Permission>permissions=new HashSet<>();

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
