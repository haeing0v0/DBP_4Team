package HRM.Manage.controller;

import HRM.Manage.domain.Department;
import HRM.Manage.domain.Employee;
import HRM.Manage.service.DepartmentService;
import HRM.Manage.service.EmployeeService;
import HRM.Manage.DTO.employeeStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

@Controller
public class
EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping("/")
    public String mainpage(Model model) {
        employeeStateDTO stats = employeeService.findEmployeeStats();
        model.addAttribute("totalEmployees", stats.getTotalEmployees());
        model.addAttribute("departmentStats", stats.getDepartmentStats());
        model.addAttribute("positionStats", stats.getPositionStates());
        model.addAttribute("Top3Department", departmentService.findTop3Department());
        return "mainpage";
    }

    @GetMapping("/employees/enter")
    public String enterEmployee() {return "employees/enterEmployee";}

    @PostMapping(value = "/employees/create")
    public String create(Employee form){
        Employee employee = new Employee();
        employee.setEmployee_id(form.getEmployee_id());
        employee.setEmployee_name(form.getEmployee_name());
        employee.setPhonenumber(form.getPhonenumber());
        employee.setEmail(form.getEmail());
        employee.setAge(form.getAge());
        employee.setGender(form.getGender());
        employee.setDate(form.getDate());
        employee.setPosition_id_fk(form.getPosition_id_fk());
        employee.setPay_id_fk(form.getPay_id_fk());
        employee.setDepartment_id_fk(form.getDepartment_id_fk());
        employeeService.join(employee);
        return "employees/employeeList";
    }


//    @GetMapping(value = "/employees/department")
//    public String list(Model model) {
//        List<Employee> employees = employeeService.findDepartmentEmployee();
//
//    }





}
