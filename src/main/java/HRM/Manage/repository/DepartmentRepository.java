package HRM.Manage.repository;

import HRM.Manage.domain.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();
}
