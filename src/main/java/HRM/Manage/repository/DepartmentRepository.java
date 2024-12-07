package HRM.Manage.repository;

import java.util.List;

import HRM.Manage.domain.Department;

public interface DepartmentRepository {
    // 부서 조회 -> 부서ID, 부서명, 매출총액
    List<Department> findDepartment();
    List<Department> findTop3Department();
    List<Department> findDepartmentName();
}
