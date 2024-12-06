import React, { useState, useEffect } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

const Employees = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchEmployees = async () => {
      try {
        setLoading(true);
        const response = await axios.get("http://localhost:8080/api/employees/list");
        setEmployees(response.data);
        setError("");
      } catch (err) {
        console.error(err);
        setError("데이터를 불러오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchEmployees();
  }, []);

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직원 목록</h1>
          {loading && <p>데이터를 불러오는 중입니다...</p>}
          {error && <p className="error-message">{error}</p>}
          {!loading && !error && employees.length > 0 ? (
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
                  {employees.map((employee) => (
                    <tr key={employee.employee_id}>
                      <td>{employee.employee_id}</td>
                      <td>{employee.employee_name}</td>
                      <td>{employee.email}</td>
                      <td>{employee.phonenumber}</td>
                      <td>{employee.age}</td>
                      <td>{employee.gender}</td>
                      <td>{employee.date}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            !loading && <p>직원 정보가 없습니다.</p>
          )}
        </div>
      </div>
    </>
  );
};

export default Employees;
