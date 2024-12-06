package HRM.Manage.repository;

import HRM.Manage.DTO.employeeStateDTO;
import HRM.Manage.domain.Employee;
import oracle.jdbc.OracleType;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class jdbcEmployeeRepository implements EmployeeRepository{
    private final DataSource dataSource;

    public jdbcEmployeeRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO EMPLOYEE (EMPLOYEE_ID, EMPLOYEE_NAME, PHONENUMBER, EMAIL, AGE, GENDER, ENTERCOMPANYDATE, POSITION_ID, PAY_ID, DEPARTMENT_ID, MONTHPAY) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, EMPLOYEE_SEQ.NEXTVAL, ?, "
                + "(SELECT POSITION_PAY FROM COMPANY_POSITION WHERE POSITION_ID = ?))";

        Connection conn = null;     PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employee.getEmployee_id());
            pstmt.setString(2, employee.getEmployee_name());
            pstmt.setString(3, employee.getPhonenumber());
            pstmt.setString(4,employee.getEmail());
            pstmt.setInt(5, employee.getAge());
            pstmt.setString(6, employee.getGender());
            pstmt.setDate(7,java.sql.Date.valueOf(employee.getDate())); // Date 변환
            pstmt.setInt(8, employee.getPosition_id_fk());
            pstmt.setInt(9, employee.getDepartment_id_fk());
            pstmt.setInt(10, employee.getPosition_id_fk());

            int num = pstmt.executeUpdate();
            if(num == 1){ return employee;}
            else { throw new SQLException("id 조회 실패"); }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public Optional<Employee> findById(Integer id) {
        String sql = "select * from EMPLOYEE where EMPLOYEE_ID = ?";
        Connection conn = null;     PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getInt(1));
                employee.setEmployee_name(rs.getString(2));
                employee.setPhonenumber(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setAge(rs.getInt(5));
                employee.setGender(rs.getString(6));
                employee.setDate(rs.getDate(7).toLocalDate());
                employee.setPosition_id_fk(rs.getInt(8));
                employee.setPay_id_fk(rs.getInt(9));
                employee.setDepartment_id_fk(rs.getInt(10));
                return Optional.of(employee);
            } else { return Optional.empty(); }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {close(conn, pstmt, rs); }
    }

    @Override
    public List<Employee> findAll() {
        String sql = "select * from EMPLOYEE";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Employee> employees = new ArrayList<>();
            while(rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getInt(1));
                employee.setEmployee_name(rs.getString(2));
                employee.setPhonenumber(rs.getString(3));
                employee.setEmail(rs.getString(4));
                employee.setAge(rs.getInt(5));
                employee.setGender(rs.getString(6));
                employee.setDate(rs.getDate(7).toLocalDate());
                employee.setPosition_id_fk(rs.getInt(8));
                employee.setPay_id_fk(rs.getInt(9));
                employee.setDepartment_id_fk(rs.getInt(10));
                employees.add(employee);
            }
            return employees;
        } catch (Exception e) {   throw new IllegalStateException(e);
        } finally { close(conn, pstmt, rs);
        }
    }

    @Override
    public employeeStateDTO calculateEmployeeStats() {
        Connection conn = null;     CallableStatement cstmt = null;
        String procedureCall = "{CALL CALCULATE_EMPLOYEE_STATS(?, ?, ?)}";

        try {
            conn = getConnection();
            cstmt = conn.prepareCall(procedureCall);

            cstmt.registerOutParameter(1, Types.INTEGER);
            cstmt.registerOutParameter(2, OracleTypes.CURSOR);
            cstmt.registerOutParameter(3, OracleTypes.CURSOR);

            cstmt.execute();

            int totalEmployees = cstmt.getInt(1);

            List<employeeStateDTO.DepartmentStats> departmentStats = new ArrayList<>();
            try (ResultSet rs = (ResultSet)cstmt.getObject(2)) {
                while(rs.next()) {
                    employeeStateDTO.DepartmentStats deptStat = new employeeStateDTO.DepartmentStats();
                    deptStat.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
                    deptStat.setEmployeeCount(rs.getInt("EMPLOYEE_COUNT"));
                    departmentStats.add(deptStat);
                }
            }

            List<employeeStateDTO.PositionStats> positionStats = new ArrayList<>();
            try ( ResultSet rs = (ResultSet)cstmt.getObject(3)) {
                while(rs.next()) {
                    employeeStateDTO.PositionStats posStat = new employeeStateDTO.PositionStats();
                    posStat.setPositionName(rs.getString("POSITION_NAME"));
                    posStat.setEmployeeCount(rs.getInt("EMPLOYEE_COUNT"));
                    positionStats.add(posStat);
                }
            }

            employeeStateDTO stats = new employeeStateDTO();
            stats.setTotalEmployees(totalEmployees);
            stats.setDepartmentStats(departmentStats);
            stats.setPositionStates(positionStats);
            return stats;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { close(conn, cstmt, null);
        }
    }

    @Override
    public List<Employee> findEmployeesByDepartment(String name) {
        String sql = "SELECT e.EMPLOYEE_ID, e.EMPLOYEE_NAME, e.PHONENUMBER, e.EMAIL, e.GENDER " +
                "FROM Employee e " +
                "JOIN Department d ON e.department_id = d.department_id " +
                "WHERE d.department_name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            List<Employee> employees = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
                employee.setEmployee_name(rs.getString("EMPLOYEE_NAME"));
                employee.setPhonenumber(rs.getString("PHONENUMBER"));
                employee.setEmail(rs.getString("EMAIL"));
                employee.setGender(rs.getString("GENDER"));

                // 저장 프로시저 호출로 근무일수 계산
                int workDays = calculateWorkDays(employee.getEmployee_id());
                employee.setWorkDays(workDays); // Employee 객체에 근무일수 추가 (Employee에 필드 필요)

                employees.add(employee);
            }


            return employees;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { close(conn, pstmt, null);
        }
    }

    private int calculateWorkDays(int employeeId) {
        Connection conn = null;     CallableStatement cstmt = null;
        String procedureCall = "{CALL CALCULATE_WORK_DAYS(?, ?)}";
        try {
            conn = getConnection();
            cstmt = conn.prepareCall(procedureCall);

            cstmt.setInt(1, employeeId);
            cstmt.registerOutParameter(2, Types.INTEGER);

            cstmt.execute();

            return cstmt.getInt(2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally { close(conn, cstmt, null);
        }
    }



    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (stmt != null) { // stmt는 PreparedStatement 또는 CallableStatement 모두 가능
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn); // Connection 닫기
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
