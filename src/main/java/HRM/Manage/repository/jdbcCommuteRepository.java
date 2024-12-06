package HRM.Manage.repository;

import HRM.Manage.domain.Commute;
import HRM.Manage.domain.Employee;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;

@Repository
public class jdbcCommuteRepository implements CommuteRepository{
    private final DataSource dataSource;

    public jdbcCommuteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Commute> findById(Integer id) {
        String sql = "SELECT * FROM COMMUTE WHERE EMPLOYEE_ID = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        List<Commute> commuteList = new ArrayList<>(); // 결과를 담을 리스트 생성

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            while (rs.next()) { // ResultSet에서 여러 행을 반복 처리
                Commute commute = new Commute();
                commute.setCommute_id(rs.getInt("COMMUTE_ID")); // 명시적 컬럼 이름으로 매핑
                commute.setEmployee_id(rs.getInt("EMPLOYEE_ID"));
                commute.setStartWorkTime(rs.getString("STARTWORKTIME"));
                commute.setFinishWorkTime(rs.getString("FINISHWORKTIME"));
                if (rs.getDate("WORKDAY") != null) {
                    commute.setWorkDay(rs.getDate("WORKDAY").toLocalDate());
                }
                commute.setDayWorkTime(rs.getInt("DAYWORKTIME"));
                commute.setMonthWorkTime(rs.getInt("MONTHWORKTIME"));

                commuteList.add(commute); // 리스트에 추가
            }

        } catch (Exception e) {
            throw new IllegalStateException("Error while fetching commute records: " + e.getMessage(), e);
        } finally {
            close(conn, pstmt, rs);
        }

        return commuteList; // 리스트 반환
    }


    @Override
    public Commute save(Commute commute) {
        String sql = "INSERT INTO COMMUTE (commute_id, employee_id, startWorkTime, finishWorkTime, workDay) "
                + "VALUES (COMMUTE_ID_SEQ.NEXTVAL, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        // 입력값 검증
        if (commute.getEmployee_id() == null) {
            throw new IllegalArgumentException("Employee ID cannot be null.");
        }

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql, new String[] {"commute_id"}); // 자동 생성된 ID 반환

            pstmt.setInt(1, commute.getEmployee_id());
            pstmt.setString(2, commute.getStartWorkTime());
            pstmt.setString(3, commute.getFinishWorkTime());
            pstmt.setDate(4, java.sql.Date.valueOf(commute.getWorkDay()));

            int num = pstmt.executeUpdate();
            if (num == 1) {
                rs = pstmt.getGeneratedKeys(); // 생성된 ID 값 가져오기
                if (rs.next()) {
                    commute.setCommute_id(rs.getInt(1)); // 생성된 ID를 설정
                }
                return commute;
            } else {
                throw new SQLException("Failed to insert commute record.");
            }
        } catch (Exception e) {
            throw new IllegalStateException("Error while saving commute record: " + e.getMessage(), e);
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
