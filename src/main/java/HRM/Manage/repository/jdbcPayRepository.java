package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import HRM.Manage.domain.Pay;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
public class jdbcPayRepository implements PayRepository{
    private final DataSource dataSource;

    public jdbcPayRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public Optional<Pay> findPayById(Integer id) {
        if (id == null) {
            return Optional.empty();  // id가 null이면 빈 Optional을 반환
        }

        String sql = "select DEFAULT_PAY, YEAR_PAY, INCENTIVE from pay where id = ?";
        Connection conn = null;     PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Pay pay = new Pay();
                pay.setDefault_pay(rs.getInt("DEFAULT_PAY"));
                pay.setYear_pay(rs.getInt("YEAR_PAY"));
                pay.setIncentive(rs.getInt("INCENTIVE"));
                return Optional.of(pay);
            } else { return Optional.empty(); }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {close(conn, pstmt, rs); }
    }

    @Override
    public boolean saveIncentive(Integer id, int incentive) {
        String sql = "UPDATE PAY SET INCENTIVE = INCENTIVE + ? WHERE EMPLOYEE_ID = ?";
        Connection conn = null;     PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, incentive);
            pstmt.setInt(2, id);

            int rows = pstmt.executeUpdate();
            return rows>0;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
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
