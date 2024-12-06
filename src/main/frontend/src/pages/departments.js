import React from "react";
import "../styles/departments.css";
import Sidebar from "../components/Sidebar";

const Departments = () => {
  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">부서/부서별 직원 조회</h1>
          <div className="table-container">
            <table className="department-table">
              <thead>
                <tr>
                  <th>부서 ID</th>
                  <th>부서명</th>
                  <th>매출 총액</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>301</td>
                  <td>인사팀</td>
                  <td>3,200,000</td>
                </tr>
                <tr>
                  <td>302</td>
                  <td>홍보팀</td>
                  <td>2,940,000</td>
                </tr>
                <tr>
                  <td>303</td>
                  <td>마케팅팀</td>
                  <td>2,564,300</td>
                </tr>
                <tr>
                  <td>304</td>
                  <td>개발팀</td>
                  <td>2,350,000</td>
                </tr>
                <tr>
                  <td>305</td>
                  <td>생산팀</td>
                  <td>2,240,600</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>
  );
};

export default Departments;
