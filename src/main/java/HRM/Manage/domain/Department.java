package HRM.Manage.domain;

public class Department {
    Integer department_id;
    String department_name;
    Integer department_totalsales;
    Integer employee_id;

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

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }
}
