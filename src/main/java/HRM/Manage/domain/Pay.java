package HRM.Manage.domain;

public class Pay {
    Integer pay_id;
    Integer default_pay;
    Integer year_pay;
    Integer incentive;
    private Employee employee_id;

    public Integer getPay_id() {
        return pay_id;
    }

    public void setPay_id(Integer pay_id) {
        this.pay_id = pay_id;
    }

    public Integer getDefault_pay() {
        return default_pay;
    }

    public void setDefault_pay(Integer default_pay) {
        this.default_pay = default_pay;
    }

    public Integer getIncentive() {
        return incentive;
    }

    public void setIncentive(Integer incentive) {
        this.incentive = incentive;
    }

    public Integer getYear_pay() {
        return year_pay;
    }

    public void setYear_pay(Integer year_pay) {
        this.year_pay = year_pay;
    }

    public Employee getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Employee employee_id) {
        this.employee_id = employee_id;
    }
}
