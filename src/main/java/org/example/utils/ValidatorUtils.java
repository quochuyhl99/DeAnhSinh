package org.example.utils;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.apache.commons.validator.routines.DoubleValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.example.config.HibernateConfig;
import org.example.entity.Department;
import org.example.entity.Employee;


public class ValidatorUtils {
    private static final Validator ENTITY_VALIDATOR = HibernateConfig.getValidator();
    public static int validateInteger(String input) throws Exception {
        if (IntegerValidator.getInstance().validate(input) == null) {
            throw new Exception("invalid Integer number");
        }
        return IntegerValidator.getInstance().validate(input);
    }

    public static double validateDouble(String input) throws Exception {
        if (DoubleValidator.getInstance().validate(input) == null) {
            throw new Exception("invalid Double number");
        }
        return DoubleValidator.getInstance().validate(input);
    }

    public static boolean validateEntity(Object obj) {
        boolean check = true;
        if (obj instanceof Department) {
            Department department = (Department) obj;
            Set<ConstraintViolation<Department>> violations = ENTITY_VALIDATOR.validate(department);
            if (violations.size() > 0) {
                System.out.println("  - " + department.getClass().getSimpleName() + " invalid:");
                for (ConstraintViolation<Department> violation : violations) {
                    System.out.println("    + " + violation.getMessage());
                }
                check = false;
            }
        }

//        if (obj instanceof Salary) {
//            Salary salary = (Salary) obj;
//            Set<ConstraintViolation<Salary>> violations = ENTITY_VALIDATOR.validate(salary);
//            if (violations.size() > 0) {
//                System.out.println("  - " + salary.getClass().getSimpleName() + " invalid:");
//                for (ConstraintViolation<Salary> violation : violations) {
//                    System.out.println("    + " + violation.getMessage());
//                }
//                check = false;
//            }
//        }

        if (obj instanceof Employee) {
            Employee employee = (Employee) obj;
            Set<ConstraintViolation<Employee>> violations = ENTITY_VALIDATOR.validate(employee);
            if (violations.size() > 0) {
                System.out.println("  - " + employee.getClass().getSimpleName() + " invalid:");
                for (ConstraintViolation<Employee> violation : violations) {
                    System.out.println("    + " + violation.getMessage());
                }
                check = false;
            }
        }
        return check;
    }

}
