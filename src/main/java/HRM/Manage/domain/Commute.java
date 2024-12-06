package HRM.Manage.domain;


import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalTime;

// java.sql.Date -> 날짜만 반환
// java.util.Date -> 날짜와 시간 모두 반환

public class Commute {
    Integer commute_id;
    String startWorkTime;
    String finishWorkTime;
    @DateTimeFormat(pattern = "yy-MM-dd")
    LocalDate workDay;
    Integer dayWorkTime;
    Integer monthWorkTime;
    Integer employee_id;

    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    Employee employee = new Employee();

    public Integer getCommute_id() {
        return commute_id;
    }

    public void setCommute_id(Integer commute_id) {
        this.commute_id = commute_id;
    }

    public String getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(String startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public String getFinishWorkTime() {
        return finishWorkTime;
    }

    public void setFinishWorkTime(String finishWorkTime) {
        this.finishWorkTime = finishWorkTime;
    }

    public LocalDate getWorkDay() {
        return workDay;
    }

    public void setWorkDay(LocalDate workDay) {
        this.workDay = workDay;
    }

    public Integer getDayWorkTime() {
        return dayWorkTime;
    }

    public void setDayWorkTime(Integer dayWorkTime) {
        this.dayWorkTime = dayWorkTime;
    }

    public Integer getMonthWorkTime() {
        return monthWorkTime;
    }

    public void setMonthWorkTime(Integer monthWorkTime) {
        this.monthWorkTime = monthWorkTime;
    }


}
