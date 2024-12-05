import React from "react";
import "../styles/employees.css";
import Sidebar from "../components/Sidebar";

const Employees = () => {
  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직원 목록</h1>
          <div className="table-container">
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
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>111</td>
                  <td>정해인</td>
                  <td>deu@deu.ac.kr</td>
                  <td>010-1111-1111</td>
                  <td>29</td>
                  <td>남</td>
                  <td>2024.11.30</td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </>
  );
};

export default Employees;
