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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
        return "redirect:/employees/enter";
    }

    @GetMapping("/employees/department")    //부서 선택 시 직원 조회. -> 수정 필요
    public String departmentEmployeeList(@RequestParam(value = "departmentName", required = false) String departmentName, Model model) {

        List<Department> departmentNames = departmentService.findDepartmentName();
        model.addAttribute("departmentNames", departmentNames); //부서 이름 리스트

        // 직원 조회는 departmentName이 유효할 때만 수행
        if (departmentName != null && !departmentName.isEmpty()) {
            List<Employee> depEmployee = employeeService.findEmployeesByDepartment(departmentName);
            model.addAttribute("departmentName", departmentName); // 선택된 부서 이름
            model.addAttribute("employees", depEmployee);         // 선택된 부서의 직원 리스트
        } else {
            model.addAttribute("employees", null); // 직원 리스트가 없을 경우 null로 처리
        }

        return "employees/departmentEmployeeList";
    }


//    @GetMapping(value = "/employees/department")
//    public String list(Model model) {
//        List<Employee> employees = employeeService.findDepartmentEmployee();
//
//    }





}
