package HRM.Manage.domain;


import java.util.Date;

// java.sql.Date -> 날짜만 반환
// java.util.Date -> 날짜와 시간 모두 반환

public class Commute {
    Integer commute_id;
    java.util.Date startWorkTime;
    java.util.Date finishWorkTime;
    java.util.Date workDay;
    Integer dayWorkTime;
    Integer monthWorkTime;

    Employee employee = new Employee();

    public Integer getCommute_id() {
        return commute_id;
    }

    public void setCommute_id(Integer commute_id) {
        this.commute_id = commute_id;
    }

    public Date getStartWorkTime() {
        return startWorkTime;
    }

    public void setStartWorkTime(Date startWorkTime) {
        this.startWorkTime = startWorkTime;
    }

    public Date getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Date workDay) {
        this.workDay = workDay;
    }

    public Date getFinishWorkTime() {
        return finishWorkTime;
    }

    public void setFinishWorkTime(Date finishWorkTime) {
        this.finishWorkTime = finishWorkTime;
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
