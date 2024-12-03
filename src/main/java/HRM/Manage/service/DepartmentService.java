package HRM.Manage.service;

import HRM.Manage.domain.Department;
import HRM.Manage.domain.Employee;
import HRM.Manage.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DepartmentService {
    private DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {this.departmentRepository = departmentRepository;}

    public List<Department> findDepartment() {
        return departmentRepository.findDepartment();
    }

}
