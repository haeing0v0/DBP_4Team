package HRM.Manage;

import HRM.Manage.repository.EmployeeRepository;
import HRM.Manage.repository.jdbcCustomerRepository;
import HRM.Manage.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService(employeeRepository());
    }
    @Bean
    public EmployeeRepository employeeRepository() {
        return new jdbcCustomerRepository(dataSource);
    }

}
