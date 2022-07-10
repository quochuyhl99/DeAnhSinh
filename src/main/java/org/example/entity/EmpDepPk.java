package org.example.entity;

import java.io.Serializable;
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
public class EmpDepPk implements Serializable {

    private static final long serialVersionUID = -8853593144241074869L;

    private int empId;

    private String depId;

    public EmpDepPk(Employee employee, Department department) {
        this(employee.getEmpId(), department.getDepId());
    }
}
