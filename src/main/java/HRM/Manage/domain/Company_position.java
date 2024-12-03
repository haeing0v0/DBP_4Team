package HRM.Manage.domain;

public class Company_position {
    Integer position_id;
    String position_name;
    String explanation;
    Integer position_pay;

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public Integer getPosition_pay() {
        return position_pay;
    }

    public void setPosition_pay(Integer position_pay) {
        this.position_pay = position_pay;
    }
}
