package org.example;

import javax.persistence.Query;
import org.example.config.HibernateConfig;
import org.example.entity.Department;
import org.example.entity.Employee;
import org.example.entity.Salary;
import org.example.entity.SalaryPk;
import org.hibernate.Session;
import static org.example.utils.LocalDateUtil.stringToLocalDate;

public class App 
{
    public static void main( String[] args )
    {
        initData();

    }

    public static void initData() {
        Session session = HibernateConfig.getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "SELECT TOP(1) NULL FROM DEPARTMENTS";
        Query query = session.createNativeQuery(sql);
        if (query.getResultList().isEmpty()) {
            /**
             * Thêm Departments vào DB
             */
            Department department1 = new Department("Finance");
            Department department2 = new Department("Accounting");

            session.save(department1);
            session.save(department2);

            /**
             * Thêm Employee vào DB
             */
            Employee employee1 = new Employee("Employee 1",
                                              stringToLocalDate("21/01/2011"),
                                              stringToLocalDate("21/01/2021"));
            Employee employee2 = new Employee("Employee 2",
                                              stringToLocalDate("22/02/2012"),
                                              stringToLocalDate("22/02/2022"));

            session.save(employee1);
            session.save(employee2);

            /**
             * Thêm data vào bảng Salary
             */
            Salary salary1 = new Salary(employee1,
                                        10000000,
                                        stringToLocalDate("21/01/2011"),
                                        stringToLocalDate("21/01/2012"));
            Salary salary2 = new Salary(employee1,
                                        10000000,
                                        stringToLocalDate("22/02/2012"),
                                        stringToLocalDate("22/02/2013"));
            session.save(salary1);
            session.save(salary2);
        }
        session.getTransaction().commit();
    }
}
