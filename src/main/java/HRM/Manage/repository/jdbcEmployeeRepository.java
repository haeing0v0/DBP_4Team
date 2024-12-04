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
        String sql = "insert into EMPLOYEE values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setDate(7, new java.sql.Date(employee.getDate().getTime())); // Date 변환
            pstmt.setInt(8, employee.getPosition_id_fk());
            pstmt.setInt(9, employee.getPay_id_fk());
            pstmt.setInt(10, employee.getDepartment_id_fk());

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
        String sql = "select * from customer where id = ?";
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
                employee.setDate(rs.getDate(7));
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
                employee.setDate(rs.getDate(7));
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
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        //rs, stmt, conn 순서대로 처리
        try {
            if (rs != null) {                rs.close();
            }
        } catch (SQLException e) {            e.printStackTrace();
        }
        try {
            if (pstmt != null) {                pstmt.close();
            }
        } catch (SQLException e) {            e.printStackTrace();
        }
        try {
            if (conn != null) {                close(conn);  // private method
            }
        } catch (SQLException e) {            e.printStackTrace();
        }
    }
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
