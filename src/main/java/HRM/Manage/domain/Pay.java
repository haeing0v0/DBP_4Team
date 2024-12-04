package HRM.Manage.domain;

public class Pay {
    Integer pay_id;
    Integer default_pay;
    Integer year_pay;
    Integer incentive;
    Integer employee_id_fk;

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

    public Integer getEmployee_id_fk() {
        return employee_id_fk;
    }

    public void setEmployee_id_fk(Integer employee_id_fk) {
        this.employee_id_fk = employee_id_fk;
    }
}
