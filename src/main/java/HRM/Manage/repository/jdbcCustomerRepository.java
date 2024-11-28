package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
            pstmt.setString(3,employee.getAddress());
            pstmt.setString(4, employee.getPhonenumber());
            pstmt.setString(5,employee.getEmail());
            pstmt.setInt(6, employee.getAge());
            pstmt.setString(7, employee.getGender());
            pstmt.setDate(8, java.sql.Date.valueOf(java.time.LocalDate.now()));
            pstmt.setInt(9, employee.getPosition_id());
            pstmt.setInt(10, employee.getPay_id());
            pstmt.setInt(11, employee.getDepartment_id());
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
                employee.setAddress(rs.getString(3));
                employee.setPhonenumber(rs.getString(4));
                employee.setEmail(rs.getString(5));
                employee.setAge(rs.getInt(6));
                employee.setGender(rs.getString(7));
                employee.setDate(rs.getDate(8));
                employee.setPosition_id(rs.getInt(9));
                employee.setPay_id(rs.getInt(10));
                employee.setDepartment_id(rs.getInt(11));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        return null;
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
