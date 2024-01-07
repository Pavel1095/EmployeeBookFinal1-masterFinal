package SkyPro.Stud.EmployeeBookFinal.service;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalaryEmployee(int department);

    Employee minSalaryEmployee(int department);

    Collection<Employee> getEmployeesInDepartment(int department);

    Map<Integer, List<Employee>> getAll();


}
