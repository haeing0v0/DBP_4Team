import React, { useState, useEffect } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import "../styles/departments.css";

const Departments = () => {
  const [departments, setDepartments] = useState([]);
  const [selectedDepartment, setSelectedDepartment] = useState(null);
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchDepartments = async () => {
      try {
        setLoading(true);
        const response = await axios.get("http://localhost:8080/api/departments");
        setDepartments(response.data);
        setError("");
      } catch (err) {
        console.error(err);
        setError("부서 데이터를 불러오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchDepartments();
  }, []);

  // 부서를 클릭했을 때 해당 부서의 직원 목록 가져오기
  const handleDepartmentClick = async (departmentName) => {
    try {
      setLoading(true);
      setSelectedDepartment(departmentName);
      const response = await axios.get("http://localhost:8080/api/employees/department", {
        params: { departmentName },
      });
      setEmployees(response.data);
      setError("");
    } catch (err) {
      console.error(err);
      setEmployees([]);
      setError("직원 데이터를 불러오는 중 오류가 발생했습니다.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">부서 목록</h1>
          {loading && <p>데이터를 불러오는 중입니다...</p>}
          {error && <p className="error-message">{error}</p>}
          {!loading && !error && departments.length > 0 ? (
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
                  {departments.map((department) => (
                    <tr
                      key={department.department_id}
                      onClick={() => handleDepartmentClick(department.department_name)}
                      style={{ cursor: "pointer" }}
                    >
                      <td>{department.department_id}</td>
                      <td>{department.department_name}</td>
                      <td>{department.department_totalsales.toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            !loading && <p>부서 정보가 없습니다.</p>
          )}

          {selectedDepartment && (
            <div className="employee-list">
              <h2>{selectedDepartment} 부서의 직원 목록</h2>
              {employees.length > 0 ? (
                <table className="employee-table">
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
                        <td>{employee.gender === "M" ? "남성" : "여성"}</td>
                        <td>{employee.date}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              ) : (
                <p>직원 정보가 없습니다.</p>
              )}
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default Departments;
