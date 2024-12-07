import React, { useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

const Search = () => {
  const [employeeId, setEmployeeId] = useState("");
  const [employeeData, setEmployeeData] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSearch = async (event) => {
    event.preventDefault();

    if (!employeeId.trim()) {
      setError("Employee ID를 입력해주세요.");
      setEmployeeData(null);
      return;
    }

    setError("");
    setLoading(true);

    try {
      const response = await axios.get(`http://localhost:8080/api/employees/list`);
      const data = response.data;

      const employee = data.find((emp) => emp.employee_id.toString() === employeeId);

      if (!employee) {
        setError("해당 ID의 직원 정보가 없습니다.");
        setEmployeeData(null);
      } else {
        setEmployeeData(employee);
      }
    } catch (err) {
      console.error("API 호출 에러:", err);
      const errorMessage =
        err.response?.data?.message || "API 호출 중 오류가 발생했습니다.";
      setError(errorMessage);
      setEmployeeData(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직원 정보 조회</h1>
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

          {employeeData && !loading && (
            <div className="table-container">
              <h2>직원 정보</h2>
              <table className="department-table">
                <thead>
                  <tr>
                    <th>직원 ID</th>
                    <th>이름</th>
                    <th>이메일</th>
                    <th>연락처</th>
                    <th>나이</th>
                    <th>성별</th>
                    <th>입사일</th>
                    <th>월급</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>{employeeData.employee_id}</td>
                    <td>{employeeData.employee_name}</td>
                    <td>{employeeData.email}</td>
                    <td>{employeeData.phonenumber}</td>
                    <td>{employeeData.age}</td>
                    <td>{employeeData.gender}</td>
                    <td>{employeeData.date}</td>
                    <td>{employeeData.month_pay?.toLocaleString()}원</td>
                  </tr>
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default Search;
