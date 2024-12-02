package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class jdbcCustomerRepository implements EmployeeRepository{
    private final DataSource dataSource;

    public jdbcCustomerRepository(DataSource dataSource) {
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
            pstmt.setDate(7, employee.getDate());
            pstmt.setInt(8, employee.getPosition_id());
            pstmt.setInt(9, employee.getPay_id());
            pstmt.setInt(10, employee.getDepartment_id());
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
