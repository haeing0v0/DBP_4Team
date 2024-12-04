package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import HRM.Manage.DTO.employeeStateDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(Integer id);
    List<Employee> findAll();
    employeeStateDTO calculateEmployeeStats();  //저장프로시저(1) 호출 메서드

}
