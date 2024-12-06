import React, { useState } from "react";
import axios from "axios";
import "../styles/check.css";
import Sidebar from "../components/Sidebar";

const Check = () => {
  const [employeeId, setEmployeeId] = useState("");
  const [attendanceData, setAttendanceData] = useState([]); 
  const [showTable, setShowTable] = useState(false);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false); 

  const handleSearch = async (event) => {
    event.preventDefault();

    if (!employeeId.trim()) {
      setError("Employee ID를 입력해주세요.");
      setShowTable(false);
      return;
    }

    setError("");
    setLoading(true);

    try {
      // API 호출
      const response = await axios.get(`http://localhost:8080/api/commute/find`, {
        params: { id: employeeId },
      });

      const data = response.data;

      if (!Array.isArray(data) || data.length === 0) {
        setError("해당 ID의 직원 출퇴근 기록이 없습니다.");
        setShowTable(false);
      } else {
        setAttendanceData(data);
        setShowTable(true);
      }
    } catch (err) {
      console.error("API 호출 에러:", err);
      const errorMessage =
        err.response?.data?.message || "API 호출 중 오류가 발생했습니다.";
      setError(errorMessage);
      setAttendanceData([]);
      setShowTable(false);
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">출퇴근 조회</h1>
          <div className="check-form">
            <form onSubmit={handleSearch} className="search-form">
              <div className="input-container">
                <input
                  type="text"
                  placeholder="Employee ID"
                  className="check-search-input"
                  value={employeeId}
                  onChange={(e) => setEmployeeId(e.target.value)}
                />
              </div>
              <button type="submit" className="submit-button" disabled={loading}>
                {loading ? "조회 중..." : "조회"}
              </button>
            </form>
          </div>

          {error && <p className="error-message">{error}</p>}

          {loading && <p className="loading-message">데이터를 가져오는 중...</p>}

          {showTable && !loading && (
            <div className="table-container">
              <table className="attendance-table">
                <thead>
                  <tr>
                    <th>Employee ID</th>
                    <th>Work Date</th>
                    <th>Check-in Time</th>
                    <th>Check-out Time</th>
                    <th>Total Hours</th>
                    <th>Monthly Hours</th>
                  </tr>
                </thead>
                <tbody>
                  {attendanceData.map((item, index) => (
                    <tr key={index}>
                      <td>{item.employee_id}</td>
                      <td>{item.workDay}</td>
                      <td>{item.startWorkTime}</td>
                      <td>{item.finishWorkTime}</td>
                      <td>{item.dayWorkTime}</td>
                      <td>{item.monthWorkTime}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default Check;
