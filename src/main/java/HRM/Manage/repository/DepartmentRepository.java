package HRM.Manage.repository;

import HRM.Manage.domain.Department;

import java.util.List;

public interface DepartmentRepository {
    // 부서 조회 -> 부서ID, 부서명, 매출총액
    List<Department> findDepartment();
}
