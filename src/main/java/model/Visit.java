package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Visit.GET_ALL, query = "SELECT v FROM Visit v WHERE v.patient.id=:patient_id ORDER BY v.date DESC"),
        @NamedQuery(name = Visit.DELETE, query = "DELETE FROM Visit v WHERE v.id=:id AND v.patient.id=:patient_id")
})

@Entity
@Table(name = "visit", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Visit extends AbstractBaseEntity{

    public final static String GET_ALL = "Visit.getAll()";
    public final static String DELETE = "Visit.delete()";

    @Column(name = "date", nullable = false)
    private @NotNull LocalDate date;

    @Column(name = "patient_complaint", nullable = false)
    @NotBlank
    private String patientComplaint;

    @Column(name = "preliminary_diagnosis", nullable = true)
    private String preliminaryDiagnosis;

    @Column(name = "treatment", nullable = false)
    @NotBlank
    private String treatment;

    @Column(name = "diagnosis", nullable = false)
    @NotBlank
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    @NotNull
    private Patient patient;

    public Visit() {
    }

    public Visit(@NotNull LocalDate date, @NotBlank String patientComplaint, String preliminaryDiagnosis, @NotBlank String treatment, @NotBlank String diagnosis) {
        this.date = date;
        this.patientComplaint = patientComplaint;
        this.preliminaryDiagnosis = preliminaryDiagnosis;
        this.treatment = treatment;
        this.diagnosis = diagnosis;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public String getPatientComplaint() {
        return patientComplaint;
    }

    public void setPatientComplaint(String patientComplaint) {
        this.patientComplaint = patientComplaint;
    }

    public String getPreliminaryDiagnosis() {
        return preliminaryDiagnosis;
    }

    public void setPreliminaryDiagnosis(String preliminaryDiagnosis) {
        this.preliminaryDiagnosis = preliminaryDiagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
