package SkyPro.Stud.EmployeeBookFinal.service;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;
import SkyPro.Stud.EmployeeBookFinal.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private List<Employee> employees = List.of(
            new Employee("Ivan", "Baranov", 4, 25_000),
            new Employee("Alex", "Ivanov", 4, 50_000),
            new Employee("Max", "Petrov", 2, 150_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = departmentService.maxSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryEmployee_shouldThrowExceptionWhenEmployeeNotInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.maxSalaryEmployee(1));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMaxSalaryIfEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = departmentService.minSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(0), result);

    }

    @Test
    void minSalaryEmployee_shouldThrowExceptionIfNotEmployeesInDepartment() {

        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalaryEmployee(3));
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmployeesCollectionWhenEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Collection<Employee> result = departmentService.getEmployeesInDepartment(
                employees.get(0).getDepartment());

        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }

    @Test
    void getAllByDepartment_shouldReturnEmptyListIfNotEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Collection<Employee> result = departmentService.getEmployeesInDepartment(3);

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void getAll_shouldReturnMapWithEmployeeWhenEmployeeInDepartments() {
        when(employeeService.findAll()).thenReturn(employees);

        Map<Integer, List<Employee>> expectedMap = Map.of(
                2,List.of(employees.get(2)),
                4, List.of(employees.get(0), employees.get(1)));

        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_should() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        Map<Integer, List<Employee>> expectedMap = Map.of();

        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }
}