package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "visit", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Visit extends AbstractBaseEntity{

    @Column(name = "date", nullable = false)
    @NotBlank
    private LocalDateTime date;

    @Column(name = "patient_complaint", nullable = false)
    private String patientComplaint;

}
