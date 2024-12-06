import React from "react";
//import "../styles/positions.css";
import Sidebar from "../components/Sidebar";

const Positions = () => {
  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직급 목록</h1>
          <div className="table-container">
            <table className="department-table">
              <thead>
                <tr>
                  <th>직급 ID</th>
                  <th>직급명</th>
                  <th>직급 설명</th>
                  <th>직급별 급여</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>12345</td>
                  <td>부장</td>
                  <td>부장 설명</td>
                  <td>3,200,000</td>
                </tr>
                <tr>
                  <td>12346</td>
                  <td>차장</td>
                  <td>차장 설명</td>
                  <td>2,940,000</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>
  );
};

export default Positions;
