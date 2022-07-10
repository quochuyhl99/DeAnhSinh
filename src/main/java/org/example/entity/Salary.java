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
import javax.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.bytebuddy.asm.Advice.Local;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "SALARIES")
public class Salary {
    @EmbeddedId
    private SalaryPk salaryPk;

    @ToString.Exclude
    @MapsId(value = "empId")
    @ManyToOne
    @JoinColumn(name = "EmpId")
    private Employee employee;

    @Column(name = "Salary")
    private int salary;

    @Transient
    private LocalDate fromDate;
    @Column(name = "ToDate")
    private LocalDate toDate;

    public Salary(Employee employee, int salary, LocalDate fromDate, LocalDate toDate) {
        this.employee = employee;
        this.salary = salary;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.salaryPk = new SalaryPk(employee, fromDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Salary)) {
            return false;
        }
        Salary salary = (Salary) o;
        return Objects.equals(salaryPk, salary.salaryPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryPk);
    }
}
