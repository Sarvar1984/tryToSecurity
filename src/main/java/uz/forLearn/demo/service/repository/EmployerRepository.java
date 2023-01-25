package uz.forLearn.demo.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.forLearn.demo.entity.Employer;

@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
}
