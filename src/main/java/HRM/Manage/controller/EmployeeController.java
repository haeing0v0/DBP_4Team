package HRM.Manage.controller;

import HRM.Manage.domain.Department;
import HRM.Manage.domain.Employee;
import HRM.Manage.service.DepartmentService;
import HRM.Manage.service.EmployeeService;
import HRM.Manage.DTO.employeeStateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/employees/list")
    public String employeeList(Model model) {
        List<Employee> employees = employeeService.findEmployee();
        System.out.println("Employees: " + employees);
        model.addAttribute("employees", employees);

        return "employees/employeeList";
    }

    @GetMapping("/employees/search")
    public String searchEmployee(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id == null) {
            // ID가 없으면 검색 화면 반환
            model.addAttribute("message", "직원 ID를 입력해주세요.");
            return "employees/employeeSearch";
        }

        Optional<Employee> employeeOne = employeeService.findOne(id);

        if (employeeOne.isEmpty()) {
            // 검색 결과가 없는 경우
            model.addAttribute("message", "해당 ID의 직원이 존재하지 않습니다.");
            return "employees/employeeSearch";
        }

        // 검색 결과가 있는 경우
        model.addAttribute("employeeOne", employeeOne);
        return "employees/employeeSearchResult";
    }


    @GetMapping("/employees/department")
    public String departmentHandler(
            @RequestParam(value = "departmentName", required = false) String departmentName,
            Model model) {

        // 부서 이름 리스트 조회 (항상 필요)
        List<Department> departmentNames = departmentService.findDepartmentName();
        model.addAttribute("departmentNames", departmentNames);

        if (departmentName == null || departmentName.isEmpty()) {
            // departmentName이 없으면 부서 선택 화면 반환
            return "employees/departmentSelect";
        }

        // departmentName이 있으면 해당 부서의 직원 목록 조회
//        System.out.println("Selected Department: " + departmentName);

        List<Employee> depEmployee = employeeService.findEmployeesByDepartment(departmentName);
//        System.out.println("Employees: " + depEmployee);

        model.addAttribute("departmentName", departmentName); // 선택된 부서 이름 전달
        model.addAttribute("employees", depEmployee); // 직원 리스트 전달

        // 직원 목록 화면 반환
        return "employees/departmentEmployeeList";
    }









}
