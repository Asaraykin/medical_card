package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Patient.GET_ALL, query = "SELECT p FROM Patient p"),
        @NamedQuery(name = Patient.GET, query = "SELECT p FROM Patient p WHERE p.id=:id"),
        @NamedQuery(name = Patient.DELETE, query = "DELETE FROM Patient p where p.id=:id")
})

@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"} )})
public class Patient extends AbstractBaseEntity {

    public static final String GET_ALL = "Patient.getAll";
    public static final String GET = "Patient.get";
    public static final String DELETE = "Patient.delete";

    @Column
    @NotBlank
    @Size(max = 150)
    private String name;

    @Column
    @NotBlank
    @Size(max = 300)
    private String address;

    @Column
    @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    private String telephone;

    @Column
    @NotBlank
    private String gender;

    @Column
    @Pattern(regexp = "[1-4]")
    private int blood_group;

    @Column
    @NotBlank
    private LocalDateTime date_of_birth;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "working",
            //foreign key for Patient in working table
            joinColumns = @JoinColumn(name = "patient_id"),
            //foreign key for other side - WorkPlace in working table
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    private Set<WorkPlace> workPlaces = new HashSet<>();
    public Set<WorkPlace> getWorkPlaces() {
        return workPlaces;
    }

    public void setWorkPlaces(Set<WorkPlace> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public Patient(Integer id, @NotBlank @Size(max = 150) String name, @NotBlank @Size(max = 300) String address, @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$") String telephone, @NotBlank String gender, @Pattern(regexp = "[1-4]") int blood_group, LocalDateTime date_of_birth) {
        super(id);
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.gender = gender;
        this.blood_group = blood_group;
        this.date_of_birth = date_of_birth;
    }

    public Patient() {
    }

    @Override
    public String toString() {
        return  "ФИО: " + name +
                ", адрес проживания: " + address +
                ", тел.: " + telephone +
                ", пол: " + gender +
                ", группа крови: " + blood_group;
    }

    public void addWorkPlace(WorkPlace workPlace){
        workPlaces.add(workPlace);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String sex) {
        this.gender = sex;
    }

    public int getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(int blood_group) {
        this.blood_group = blood_group;
    }

    public LocalDateTime getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDateTime dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

}
