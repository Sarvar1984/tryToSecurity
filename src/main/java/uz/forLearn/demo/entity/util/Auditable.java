package uz.forLearn.demo.entity.util;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.forLearn.demo.utils.LocalDateTimeConverter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Auditable implements BaseDomain, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @CreatedDate
    @Column(name = "created_at",columnDefinition = "TIMESTAMP default NOV()")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;


    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "updated_at",columnDefinition = "TIMESTAMP default NOV()")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updatedAt;

    @CreatedBy
    private Long createdBy;

    @LastModifiedBy
    private Long updatedBy;

}
