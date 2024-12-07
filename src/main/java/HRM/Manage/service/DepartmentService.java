package HRM.Manage.service;

import java.util.List;

import org.springframework.stereotype.Service;

import HRM.Manage.domain.Department;
import HRM.Manage.repository.DepartmentRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    public DepartmentService(DepartmentRepository departmentRepository) {this.departmentRepository = departmentRepository;}

    public List<Department> findDepartment() {
        return departmentRepository.findDepartment();
    }
    public List<Department> findTop3Department() {return departmentRepository.findTop3Department();}
    public List<Department> findDepartmentName() {return departmentRepository.findDepartmentName();}
}
