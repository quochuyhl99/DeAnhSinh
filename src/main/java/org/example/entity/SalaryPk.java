package org.example.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
@Embeddable
public class SalaryPk implements Serializable {

    private static final long serialVersionUID = -6614109471981288678L;

    private Integer empId;

    @Column(name = "FromDate")
    private LocalDate fromDate;

    public SalaryPk(Employee employee, LocalDate fromDate) {
        this(employee.getEmpId(), fromDate);
    }
}
