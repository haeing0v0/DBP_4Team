package HRM.Manage.domain;
import java.util.HashSet;
import java.util.Set;
// 부서, 직원 => 1 : N

public class Department {
    Integer department_id;
    String department_name;
    Integer department_totalsales;

    //일대다 직원 목록
//    private Set<Employee> employees = new HashSet<>();

    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public Integer getDepartment_totalsales() {
        return department_totalsales;
    }

    public void setDepartment_totalsales(Integer department_totalsales) {
        this.department_totalsales = department_totalsales;
    }

}
