package uz.forLearn.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.forLearn.demo.entity.enums.PermissionName;
import uz.forLearn.demo.entity.util.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission extends Auditable implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private PermissionName permissionName;

    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return permissionName.name();
    }
}
