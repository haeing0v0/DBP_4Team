package HRM.Manage.controller;

import HRM.Manage.domain.Employee;
import HRM.Manage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class
EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String mainpage() {return "mainpage";}

    @GetMapping("/employees/enter")
    public String enterEmployee() {return "employees/enterEmployee";}

    @PostMapping(value = "/employees/enter")
    public String create(Employee e){
        Employee employee = new Employee();
        employee.setEmployee_id(e.getEmployee_id());
        employee.setEmployee_name(e.getEmployee_name());
        employee.setPhonenumber(e.getPhonenumber());
        employee.setEmail(e.getEmail());
        employee.setAge(e.getAge());
        employee.setGender(e.getGender());
        employee.setDate(e.getDate());
        employee.setPosition_id(e.getPosition_id());
        employee.setPay_id(e.getPay_id());
        employee.setDepartment_id(e.getDepartment_id());
        employeeService.join(employee);
        return "redirect:/";
    }

//    @GetMapping(value = "/employees/department")
//    public String list(Model model) {
//        List<Employee> employees = employeeService.findDepartmentEmployee();
//
//    }





}
