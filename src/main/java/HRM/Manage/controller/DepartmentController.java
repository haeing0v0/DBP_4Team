package HRM.Manage.controller;

import HRM.Manage.service.DepartmentService;
import HRM.Manage.domain.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/department/list") //부서 목록 조회
    public String departmentList(Model model) {
        List<Department> departments = departmentService.findDepartment();
        model.addAttribute("departments", departments);
        return "department/departmentList";
    }


}
