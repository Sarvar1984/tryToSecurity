package uz.forLearn.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.forLearn.demo.entity.Role;
import uz.forLearn.demo.entity.enums.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByRoleName(RoleName roleName);
}
