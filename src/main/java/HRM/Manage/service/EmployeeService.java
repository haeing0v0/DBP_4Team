package HRM.Manage.service;

import HRM.Manage.DTO.employeeStateDTO;
import HRM.Manage.domain.Employee;
import HRM.Manage.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {this.employeeRepository = employeeRepository;}
    public Integer join(Employee employee) {
        validateDuplicateMember(employee);
        employeeRepository.save(employee);
        return employee.getEmployee_id();
    }
    private void validateDuplicateMember(Employee employee) {
        employeeRepository.findById(employee.getEmployee_id())
                .ifPresent(m -> {throw new IllegalStateException("이미 존재하는 ID 회원입니다.");});

    }
    public List<Employee> findEmployee() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> findOne(Integer employee_id) {
        return employeeRepository.findById(employee_id);
    }
    public employeeStateDTO findEmployeeStats() {return employeeRepository.calculateEmployeeStats();}

//    public List<Employee> findDepartmentEmployee() {
//
//    }


}
