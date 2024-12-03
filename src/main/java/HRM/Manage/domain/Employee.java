package HRM.Manage.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

// java.sql.Date -> 날짜만 반환
// java.util.Date -> 날짜와 시간 모두 반환
public class Employee {
    Integer employee_id;
    String employee_name;
    String phonenumber;
    String email;
    Integer age;
    String gender;
    java.util.Date date;

    //company_position과 1:N관계
    private Set<Company_position> position_id_fk = new HashSet<>();
    //pay와 1대 1 관계
    private Pay pay_id_fk;
    //Department와 1:N관계
    private Set<Department> department_id_fk = new HashSet<>();

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Company_position> getPosition_id_fk() { //position_id 전체 반환
        return position_id_fk;
    }

    public void setPosition_id(Set<Company_position> position_id_fk) {
        this.position_id_fk = position_id_fk;
    }

    public Pay getPay_id() {
        return pay_id_fk;
    }

    public void setPay_id(Pay pay_id){
        this.pay_id_fk = pay_id;
    }

    public Set<Department> getDepartment_id() {
        return department_id_fk;
    }

    public void setDepartment_id(Set<Department> department_id) {
        this.department_id_fk = department_id;
    }



}
