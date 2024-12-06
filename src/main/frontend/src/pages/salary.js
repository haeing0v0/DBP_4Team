import React, { useState } from "react";
import axios from "axios";
import "../styles/salary.css";
import Sidebar from "../components/Sidebar";

const Salary = () => {
  const [employeeId, setEmployeeId] = useState("");
  const [salaryData, setSalaryData] = useState([]); 
  const [error, setError] = useState(""); 
  const [loading, setLoading] = useState(false); 

  const handleSearch = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError("");
    setSalaryData([]);

    try {
      const response = await axios.get(`http://localhost:8080/api/pay/${employeeId}`);
      if (response.data && Array.isArray(response.data)) {
        setSalaryData(response.data);
      } else {
        setError("잘못된 데이터 형식입니다.");
      }
    } catch (err) {
      console.error("Error fetching salary data:", err);
      setError("해당 직원 ID의 급여 정보를 찾을 수 없습니다.");
    } finally {
      setLoading(false); 
    }
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">급여 조회</h1>
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
              <button type="submit" className="submit-button">
                조회
              </button>
            </form>
          </div>
          {loading && <p>데이터를 불러오는 중입니다...</p>}
          {error && <p className="error-message">{error}</p>}
          {salaryData.length > 0 && (
            <div className="table-container">
              <h3 className="sub-title">직원 ID: {employeeId} 급여</h3>
              <table className="salary-table">
                <tbody>
                  {salaryData.map((pay, index) => (
                    <React.Fragment key={index}>
                      <tr>
                        <td className="label">기본급</td>
                        <td>{pay.default_pay ? pay.default_pay.toLocaleString() : "N/A"}</td>
                      </tr>
                      <tr>
                        <td className="label">연봉</td>
                        <td>{pay.year_pay ? pay.year_pay.toLocaleString() : "N/A"}</td>
                      </tr>
                      <tr>
                        <td className="label">인센티브</td>
                        <td>{pay.incentive ? pay.incentive.toLocaleString() : "N/A"}</td>
                      </tr>
                    </React.Fragment>
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

export default Salary;
