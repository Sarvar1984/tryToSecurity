package uz.forLearn.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.forLearn.demo.entity.util.Auditable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employer extends Auditable {

    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private LegalPerson legalPerson;
    @OneToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private PhysicalPerson physicalPerson;

}
