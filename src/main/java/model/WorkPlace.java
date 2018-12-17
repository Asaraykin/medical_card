package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = WorkPlace.DELETE, query = "DELETE FROM WorkPlace x WHERE x.id=:id"),
        @NamedQuery(name = WorkPlace.GET_ALL, query = "SELECT u FROM WorkPlace u ORDER BY u.name"),
})

@Entity
@Table(name = "work_place", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class WorkPlace extends AbstractBaseEntity{

    public final static String DELETE = "WorkPlace.delete()";
    public static final String GET_ALL = "WorkPlace.getAll()";

    @Column(name = "name")
    @NotBlank
    private String name;



    @ManyToMany(mappedBy = "workPlaces", fetch = FetchType.EAGER)
    private Set<Patient> patientSet = new HashSet<>();
    public Set<Patient> getPatientSet() {
        return patientSet;
    }

    public void setPatientSet(Set<Patient> patientSet) {
        this.patientSet = patientSet;
    }

    public WorkPlace() {
    }

    public WorkPlace(@NotBlank String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
