import React, { useState } from "react";
import "../styles/check.css";
import Sidebar from "../components/Sidebar";

const Check = () => {
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
          <h1 className="dashboard-title">출퇴근 조회</h1>
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
                  <tr>
                    <td>12345</td>
                    <td>Aug 1</td>
                    <td>9:00 AM</td>
                    <td>5:00 PM</td>
                    <td>8 hours</td>
                    <td>160 hours</td>
                  </tr>
                  <tr>
                    <td>12345</td>
                    <td>Aug 2</td>
                    <td>9:00 AM</td>
                    <td>5:00 PM</td>
                    <td>8 hours</td>
                    <td>160 hours</td>
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

export default Check;
