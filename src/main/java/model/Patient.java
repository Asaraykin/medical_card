package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = Patient.GET_ALL, query = "SELECT p FROM Patient p"),
        @NamedQuery(name = Patient.GET, query = "SELECT p FROM Patient p WHERE p.id=:id"),
        @NamedQuery(name = Patient.DELETE, query = "DELETE FROM Patient p where p.id=:id")
})

@Entity
@Table(name = "patient", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "oms"} )})
public class Patient  {

    public static final String GET_ALL = "Patient.getAll";
    public static final String GET = "Patient.get";
    public static final String DELETE = "Patient.delete";


    @GenericGenerator(name = "generator", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;


    @Column
    @NotBlank
    @Size(max = 150)
    private String name;

    @Column
    @NotBlank
    @Size(max = 300)
    private String address;

    @Column
    @NotBlank
    private String oms;

    @Column
   @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$")
    private String telephone;

    @Column
    @NotBlank
    private String gender;

    @Column
   // @Range(min = 1, max = 4)
    private int blood_group;

    @Column
    @NotNull
    private LocalDate date_of_birth;

    //TODO eager or lazy which is better
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "working",
            //foreign key for Patient in working table
            joinColumns = @JoinColumn(name = "patient_id"),
            //foreign key for other side - WorkPlace in working table
            inverseJoinColumns = @JoinColumn(name = "work_id"))
    //https://stackoverflow.com/questions/41407921/eliminate-circular-json-in-java-spring-many-to-many-relationship
    @JsonManagedReference
    private Set<WorkPlace> workPlaces = new HashSet<>();


    public void setId(Integer id) {
this.id = id;    }


    public Integer getId() {
        return this.id;
    }


    public boolean isNew() {
        return this.id == null;
    }

    public Set<WorkPlace> getWorkPlaces() {
        return workPlaces;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setWorkPlaces(Set<WorkPlace> workPlaces) {
        this.workPlaces = workPlaces;
    }

    public String getOms() {
        return oms;
    }

    public void setOms(String oms) {
        this.oms = oms;
    }

    public Patient(User user, @NotBlank @Size(max = 150) String name, @NotBlank @Size(max = 300) String address, @NotBlank String oms, String telephone, @NotBlank String gender, int blood_group, @NotNull LocalDate date_of_birth) {
        this.user = user;
        this.name = name;
        this.address = address;
        this.oms = oms;
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
    public void removeWorkPlace(WorkPlace workPlace){
        workPlaces.remove(workPlace);
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

    public LocalDate getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(LocalDate dateOfBirth) {
        this.date_of_birth = dateOfBirth;
    }

}
