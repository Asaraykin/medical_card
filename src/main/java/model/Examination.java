package model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@NamedQueries({
        @NamedQuery(name = Examination.DELETE, query = "DELETE FROM Examination x WHERE x.id=:id AND x.referral.id=:referral_id"),
        @NamedQuery(name = Examination.GET_ALL, query = "SELECT x FROM Examination x " +
                "WHERE x.referral.id=:referral_id ORDER BY x.date DESC")
})

@Entity
@Table(name = "examination", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Examination extends AbstractBaseEntity {

    public final static String GET_ALL = "Examination.getAll()";
    public final static String DELETE = "Examination.delete()";

    @Column(name = "date")
    @NotBlank
    private LocalDateTime date;

    @Column(name = "result")
    @NotBlank
    private String result;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "referral_id")
    private Referral referral;

    public Examination() {
    }

    public Examination(@NotBlank LocalDateTime date, @NotBlank String result) {
        this.date = date;
        this.result = result;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Referral getReferral() {
        return referral;
    }

    public void setReferral(Referral referral) {
        this.referral = referral;
    }
}
