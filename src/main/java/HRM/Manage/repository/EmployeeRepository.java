package HRM.Manage.repository;

import HRM.Manage.domain.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    Employee save(Employee employee);
    Optional<Employee> findById(Integer id);
    List<Employee> findAll();

}
