import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import "../styles/departments.css";

const Departments = () => {
  const [departments, setDepartments] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const navigate = useNavigate();

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

  // 부서를 클릭했을 때 해당 부서의 직원 목록 페이지로 이동하기
  const handleDepartmentClick = (departmentName) => {
    navigate(`/departments/${departmentName}/employees`);
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
        </div>
      </div>
    </>
  );
};

export default Departments;
