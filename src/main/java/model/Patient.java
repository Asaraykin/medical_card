package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    private String sex;

    @Column
    @Pattern(regexp = "[1-4]")
    private int blood_group;

    public Patient(Integer id, @NotBlank @Size(max = 150) String name, @NotBlank @Size(max = 300) String address, @Pattern(regexp = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$") String telephone, @NotBlank String sex, @Pattern(regexp = "[1-4]") int blood_group) {
        super(id);
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.sex = sex;
        this.blood_group = blood_group;
    }

    @Override
    public String toString() {
        return  "ФИО: " + name +
                ", адрес проживания: " + address +
                ", тел.: " + telephone +
                ", пол: " + sex +
                ", группа крови: " + blood_group;
    }
}
