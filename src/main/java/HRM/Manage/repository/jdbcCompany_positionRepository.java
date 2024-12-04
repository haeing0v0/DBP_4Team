package HRM.Manage.repository;

import HRM.Manage.domain.CompanyPosition;
import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class jdbcCompany_positionRepository implements CompanyPositionRepository{
    private final DataSource dataSource;

    public jdbcCompany_positionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<CompanyPosition> findAllCompany_position() {
        String sql = "select * from COMPANY_POSITION";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<CompanyPosition> company_position = new ArrayList<>();
            while(rs.next()) {
                CompanyPosition cp = new CompanyPosition();
                cp.setPosition_id(rs.getInt(1));
                cp.setPosition_name(rs.getString(2));
                cp.setExplanation(rs.getString(3));
                cp.setPosition_pay(rs.getInt(4));
                company_position.add(cp);
            }
            return company_position;
        } catch (Exception e) {   throw new IllegalStateException(e);
        } finally { close(conn, pstmt, rs);
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
