package SkyPro.Stud.EmployeeBookFinal.controller;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;
import SkyPro.Stud.EmployeeBookFinal.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "min-salary", params = "department")
    public Employee minSalaryEmployee(@RequestParam int department){
        return departmentService.minSalaryEmployee(department);
    }

    @GetMapping(value = "max-salary", params = "department")
    public Employee maxSalaryEmployee(@RequestParam int department){
        return departmentService.maxSalaryEmployee(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll(){
        return departmentService.getAll();
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> getAllByDepartment(@RequestParam int department) {
        return departmentService.getEmployeesInDepartment(department);
    }
}
