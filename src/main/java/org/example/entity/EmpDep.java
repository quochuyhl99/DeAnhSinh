package org.example.entity;

import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "EMP_DEP")
public class EmpDep {
    @EmbeddedId
    private EmpDepPk empDepPk;

    @ToString.Exclude
    @MapsId(value = "empId")
    @ManyToOne
    @JoinColumn(name = "EmpId")
    private Employee employee;

    @ToString.Exclude
    @MapsId(value = "depId")
    @ManyToOne
    @JoinColumn(name = "DepId")
    private Department department;

    @Column(name = "FromDate")
    private LocalDate fromDate;

    @Column(name = "ToDate")
    private LocalDate toDate;

    public EmpDep(LocalDate fromDate, LocalDate toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmpDep)) {
            return false;
        }
        EmpDep empDep = (EmpDep) o;
        return Objects.equals(empDepPk, empDep.empDepPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empDepPk);
    }
}
