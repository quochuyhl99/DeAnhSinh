package org.example.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "DEPARTMENTS")
public class Department {
    @Id
    @GenericGenerator(name = "depIdGenerator", strategy = "org.example.utils.DepIdGenerator")
    @GeneratedValue(generator = "depIdGenerator")
    @Column(name = "Id")
    private String depId;

    @Column(name = "Name")
    private String departmentName;

    @ToString.Exclude
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmpDep> employees = new ArrayList<>();

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        Department that = (Department) o;
        return Objects.equals(depId, that.depId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(depId);
    }
}
