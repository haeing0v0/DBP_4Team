package HRM.Manage.repository;

import HRM.Manage.domain.Department;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class jdbcDepartmentRepository implements DepartmentRepository{
    private final DataSource dataSource;
    public jdbcDepartmentRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Department> findAll() {
        String sql = "select * from Department";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Department> departments = new ArrayList<>();
            while(rs.next()) {
                Department department = new Department();
                department.setDepartment_id(rs.getInt(1));
                department.setDepartment_name(rs.getString(2));
                department.setDepartment_totalsales(rs.getInt(3));
                department.setEmployee_id(rs.getInt(4));
                departments.add(department);
            }
            return departments;
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
