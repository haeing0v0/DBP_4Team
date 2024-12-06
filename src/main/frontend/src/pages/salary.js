import React, { useState } from "react";
import "../styles/salary.css";
import Sidebar from "../components/Sidebar";

const Salary = () => {
  const [showTable, setShowTable] = useState(false);

  const handleSearch = (event) => {
    event.preventDefault();
    setShowTable(true); // 버튼 클릭 시 표를 표시
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
                />
              </div>
              <button type="submit" className="submit-button">
                조회
              </button>
            </form>
          </div>
          {showTable && (
            <div className="table-container">
              <h3 className="sub-title">정해인님의 급여</h3>
              <table className="salary-table">
                <tbody>
                  <tr>
                    <td className="label">기본급</td>
                    <td>3,200,000</td>
                  </tr>
                  <tr>
                    <td className="label">연봉</td>
                    <td>38,000,000</td>
                  </tr>
                  <tr>
                    <td className="label">인센티브</td>
                    <td>420,000</td>
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

export default Salary;
