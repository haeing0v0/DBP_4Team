package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            // position_id_fk 처리: 첫 번째 ID 가져오기
            if (!employee.getPosition_id_fk().isEmpty()) {
                Company_position firstPosition = employee.getPosition_id_fk().iterator().next();
                pstmt.setInt(8, firstPosition.getPosition_id()); // 첫 번째 position_id 사용
            } else {
                pstmt.setNull(8, java.sql.Types.INTEGER); // position_id 값이 없으면 NULL 설정
            }

            // pay_id_fk 처리
            if (employee.getPay_id() != null) {
                pstmt.setInt(9, employee.getPay_id().getPay_id()); // pay_id 사용
            } else {
                pstmt.setNull(9, java.sql.Types.INTEGER); // pay_id 값이 없으면 NULL 설정
            }

            // department_id_fk 처리: 첫 번째 ID 가져오기
            if (!employee.getDepartment_id().isEmpty()) {
                Department firstDepartment = employee.getDepartment_id().iterator().next();
                pstmt.setInt(10, firstDepartment.getDepartment_id()); // 첫 번째 department_id 사용
            } else {
                pstmt.setNull(10, java.sql.Types.INTEGER); // department_id 값이 없으면 NULL 설정
            }

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
                employee.setPosition_id(rs.getInt(8));
                employee.setPay_id(rs.getInt(9));
                employee.setDepartment_id(rs.getInt(10));
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
                employee.setPosition_id(rs.getInt(8));
                employee.setPay_id(rs.getInt(9));
                employee.setDepartment_id(rs.getInt(10));
                employees.add(employee);
            }
            return employees;
        } catch (Exception e) {   throw new IllegalStateException(e);
        } finally { close(conn, pstmt, rs);
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
