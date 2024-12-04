package HRM.Manage.DTO;

import javax.swing.text.Position;
import java.util.List;

public class employeeStateDTO {
    private int totalEmployees;
    private List<DepartmentStats> departmentStats;
    private List<PositionStats> positionStates;

    public int getTotalEmployees() {
        return totalEmployees;
    }
    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }

    public List<DepartmentStats> getDepartmentStats() {
        return departmentStats;
    }
    public void setDepartmentStats(List<DepartmentStats> departmentStats) {
        this.departmentStats = departmentStats;
    }

    public List<PositionStats> getPositionStates() {
        return positionStates;
    }
    public void setPositionStates(List<PositionStats> positionStates) {
        this.positionStates = positionStates;
    }



    public static class DepartmentStats {
        private String departmentName;
        private int employeeCount;

        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        public void setEmployeeCount(int employeeCount) {
            this.employeeCount = employeeCount;
        }
    }

    public static class PositionStats {
        private String positionName;
        private int employeeCount;

        public String getPositionName() {
            return positionName;
        }

        public void setPositionName(String positionName) {
            this.positionName = positionName;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }

        public void setEmployeeCount(int employeeCount) {
            this.employeeCount = employeeCount;
        }
    }

}
