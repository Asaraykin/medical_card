package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedQueries({
        @NamedQuery(name = Referral.GET_ALL, query = "SELECT r FROM Referral r WHERE r.visit.id=:visit_id order by r.date DESC"),
        @NamedQuery(name = Referral.DELETE, query = "DELETE FROM Referral r WHERE r.id=:id AND r.visit.id=:visit_id")
})

@Entity
@Table(name = "referral", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Referral extends AbstractBaseEntity {

    public final static String GET_ALL = "Referral.getAll()";
    public final static String DELETE = "Referral.delete()";

    @Column(name = "date")
    private @NotNull LocalDate date;

    @Column(name = "type")
    @NotBlank
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "visit_id")
    @NotNull
    private Visit visit;

  /*  @OneToOne(fetch = FetchType.LAZY)
    private Examination examination;
*/
    public Referral() {
    }

    public Referral(@NotNull LocalDate date, @NotBlank String type) {
        this.date = date;
        this.type = type;
    }

    public @NotNull LocalDate getDate() {
        return date;
    }

    public void setDate(@NotNull LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Visit getVisit() {
        return visit;
    }

    public void setVisit(Visit visit) {
        this.visit = visit;
    }
}
