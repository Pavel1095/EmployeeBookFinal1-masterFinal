package SkyPro.Stud.EmployeeBookFinal.controller;

import SkyPro.Stud.EmployeeBookFinal.dto.Employee;
import SkyPro.Stud.EmployeeBookFinal.service.EmployeeService;
import SkyPro.Stud.EmployeeBookFinal.util.EmployeeNameValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee (@RequestParam String firstName, @RequestParam String lastName,
                                 @RequestParam(required = false, defaultValue = "0") Integer department,
                                 @RequestParam(required = false, defaultValue = "0") Double salary){
        EmployeeNameValidator.validaIsAlpha(firstName, lastName);
        return employeeService.addEmployeeFull(firstName, lastName, department, salary);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName, @RequestParam String lastName){
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee getEmployee (@RequestParam String firstName, @RequestParam String lastName){
        EmployeeNameValidator.validaIsAlpha(firstName, lastName);
        return employeeService.getEmployee(firstName, lastName);
    }

    @GetMapping
    public Collection<Employee> findAll(){
        return employeeService.findAll();
    }
}
