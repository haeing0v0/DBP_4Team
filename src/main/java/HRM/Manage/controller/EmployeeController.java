package HRM.Manage.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import HRM.Manage.DTO.employeeStateDTO;
import HRM.Manage.domain.Department;
import HRM.Manage.domain.Employee;
import HRM.Manage.service.DepartmentService;
import HRM.Manage.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    // 메인 페이지 관련 직원 통계 정보
    @GetMapping("/stats")
    public ResponseEntity<employeeStateDTO> mainPageStats() {
        employeeStateDTO stats = employeeService.findEmployeeStats();
        return ResponseEntity.ok(stats);
    }

    // 직원 등록 API
    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(@RequestBody Employee form) {
        try {
            Employee employee = new Employee();
            employee.setEmployee_id(form.getEmployee_id());
            employee.setEmployee_name(form.getEmployee_name());
            employee.setPhonenumber(form.getPhonenumber());
            employee.setEmail(form.getEmail());
            employee.setAge(form.getAge());
            employee.setGender(form.getGender());
            employee.setDate(form.getDate());
            employee.setPosition_id_fk(form.getPosition_id_fk());
            employee.setDepartment_id_fk(form.getDepartment_id_fk());
            employeeService.join(employee);
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("직원 등록 중 오류가 발생했습니다.");
        }
    }

    // 모든 직원 리스트 조회
    @GetMapping("/list")
    public ResponseEntity<List<Employee>> employeeList() {
        List<Employee> employees = employeeService.findEmployee();
        if (employees.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(employees);
    }

    // 직원 ID로 직원 조회
    @GetMapping("/search")
    public ResponseEntity<?> searchEmployee(@RequestParam(value = "id", required = false) Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("직원 ID를 입력해주세요.");
        }

        Optional<Employee> employeeOne = employeeService.findOne(id);

        if (employeeOne.isEmpty()) {
            return ResponseEntity.status(404).body("해당 ID의 직원이 존재하지 않습니다.");
        }

        return ResponseEntity.ok(employeeOne);
    }

    // 특정 부서의 직원 목록 조회
    @GetMapping("/department")
    public ResponseEntity<?> departmentHandler(
            @RequestParam(value = "departmentName", required = false) String departmentName) {

        if (departmentName == null || departmentName.isEmpty()) {
            List<Department> departmentNames = departmentService.findDepartmentName();
            return ResponseEntity.ok(departmentNames);
        }

        // 특정 부서의 직원 조회
        List<Employee> depEmployee = employeeService.findEmployeesByDepartment(departmentName);

        if (depEmployee.isEmpty()) {
            return ResponseEntity.status(404).body("해당 부서의 직원이 없습니다.");
        }

        // 모든 직원의 필드가 제대로 반환되고 있는지 확인하는 로그 추가
        depEmployee.forEach(employee -> System.out.println(
                "직원 ID: " + employee.getEmployee_id() +
                ", 이름: " + employee.getEmployee_name() +
                ", 나이: " + employee.getAge() +
                ", 입사일: " + employee.getDate()
        ));

        return ResponseEntity.ok(depEmployee);
    }
}
