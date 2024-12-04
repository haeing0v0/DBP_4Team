package HRM.Manage.repository;

import HRM.Manage.domain.Commute;
import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Optional;

@Repository
public class jdbcCommuteRepository implements CommuteRepository{
    private final DataSource dataSource;

    public jdbcCommuteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Commute> findById(Integer id) {
        String sql = "select c.* from COMMUTE c JOIN EMPLOYEE e ON c.COMMUTE_ID = e.COMMUTE_ID WHERE e.EMPLOYEE_ID = ?";
        Connection conn = null;     PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Commute commute = new Commute();
                commute.setCommute_id(rs.getInt(1));
                commute.setStartWorkTime(rs.getDate(2));
                commute.setFinishWorkTime(rs.getDate(3));
                commute.setWorkDay(rs.getDate(4));
                commute.setDayWorkTime(rs.getInt(5));
                commute.setMonthWorkTime(rs.getInt(6));
                return Optional.of(commute);
            } else { return Optional.empty(); }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {close(conn, pstmt, rs); }
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
