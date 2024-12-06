package HRM.Manage.repository;

import HRM.Manage.domain.Employee;
import HRM.Manage.domain.Pay;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class jdbcPayRepository implements PayRepository{
    private final DataSource dataSource;

    public jdbcPayRepository(DataSource dataSource) {this.dataSource = dataSource;}

    @Override
    public List<Pay> findPayById(Integer id) {
        String sql = "SELECT DEFAULT_PAY, YEAR_PAY, INCENTIVE FROM pay WHERE EMPLOYEE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Pay> payList = new ArrayList<>(); // 결과를 담을 리스트 생성

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Pay pay = new Pay();
                pay.setDefault_pay(rs.getInt("DEFAULT_PAY")); // 기본 급여
                pay.setYear_pay(rs.getInt("YEAR_PAY"));      // 연간 급여
                // INCENTIVE 처리: NULL 확인
                int incentive = rs.getInt("INCENTIVE");
                if (rs.wasNull()) {
                    pay.setIncentive(null); // NULL로 설정
                } else {
                    pay.setIncentive(incentive); // 값 설정
                }

                payList.add(pay); // 리스트에 추가
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error fetching pay records", e);
        } finally {
            close(conn, pstmt, rs);
        }

        return payList; // 결과 리스트 반환
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
