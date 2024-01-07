package SkyPro.Stud.EmployeeBookFinal.service;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeAlreadyAddedException;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeNotFoundException;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeStorageIsFullException;
import SkyPro.Stud.EmployeeBookFinal.util.EmployeeNameValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.cache.interceptor.SimpleKeyGenerator.generateKey;


@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeMap;

    private static final int EMPLOYEES_SIZE = 3;


    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployeeFull(String firstName, String lastName, int department, double salary) {
        if (employeeMap.size() == EMPLOYEES_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        String capitalizedFirstName = StringUtils.capitalize(firstName);
        String capitalizedLastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee(
                capitalizedFirstName,
                capitalizedLastName,
                department,
                salary);

        String key = getKey(capitalizedFirstName, capitalizedLastName);

        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        employeeMap.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        String key = getKey(firstName, lastName);

        Employee employee = employeeMap.remove(key);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, 2, 15_000);
        String fullNameKey = getKey(firstName, lastName);
        if (!employeeMap.containsKey(fullNameKey)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    private static String getKey(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    public Collection<Employee> findAll() {
        return employeeMap.values();
    }
}
