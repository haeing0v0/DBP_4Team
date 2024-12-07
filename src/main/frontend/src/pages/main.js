import React, { useEffect, useState } from "react";
import "../styles/main.css";
import Sidebar from "../components/Sidebar";
import axios from "axios";
import { Line } from "react-chartjs-2";
import {
  Chart as ChartJS,
  CategoryScale,
  LinearScale,
  PointElement,
  LineElement,
  Title,
  Tooltip,
  Legend,
} from "chart.js";

ChartJS.register(CategoryScale, LinearScale, PointElement, LineElement, Title, Tooltip, Legend);

const Main = () => {
  const [totalEmployees, setTotalEmployees] = useState(0); // 전체 사원 수 상태
  const [topDepartments, setTopDepartments] = useState([]); // TOP 3 부서 데이터 상태
  const [departmentStats, setDepartmentStats] = useState([]); // 부서별 사원 수 데이터
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchStats = async () => {
      try {
        setLoading(true);

        // Fetch total employees and department stats
        const statsResponse = await axios.get("http://localhost:8080/api/employees/stats");
        setTotalEmployees(statsResponse.data.totalEmployees);
        setDepartmentStats(statsResponse.data.departmentStats);

        // Fetch TOP 3 departments
        const departmentsResponse = await axios.get("http://localhost:8080/api/departments");
        setTopDepartments(departmentsResponse.data);

        setError("");
      } catch (err) {
        console.error(err);
        setError("데이터를 불러오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchStats();
  }, []);

  const chartData = {
    labels: departmentStats.map((dept) => dept.departmentName),
    datasets: [
      {
        label: "부서별 사원 수",
        data: departmentStats.map((dept) => dept.employeeCount),
        borderColor: "blue",
        borderWidth: 2,
        tension: 0,
      },
    ],
  };

  const chartOptions = {
    responsive: true,
    maintainAspectRatio: false, // 그래프 비율 유지 비활성화
    plugins: {
      legend: {
        display: false,
      },
      title: {
        display: false,
      },
    },
    scales: {
      y: {
        min: 8, // 최소값
        max: 14, // 최대값
        ticks: {
          stepSize: 1, // 단위
          callback: (value) => (Number.isInteger(value) ? value : null), // 정수만 표시
        },
      },
    },
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <h1 className="dashboard-title">인사 관리 대시보드</h1>
        <div className="card-section">
          <div className="left-section">
            <div className="left-section-top">
              <div className="card">
                <h3>총 사원수</h3>
                {loading ? (
                  <p>로딩 중...</p>
                ) : error ? (
                  <p className="error-message">{error}</p>
                ) : (
                  <p>{totalEmployees}</p>
                )}
              </div>
              <div className="card">
                <h3>총 부서 수</h3>
                <p>6</p>
              </div>
              <h3 className="left-section-mid">성비 통계</h3>
            </div>
            <div className="left-section-bottom">
              <div className="card">
                <h3>남</h3>
                <p>9</p>
              </div>
              <div className="card">
                <h3>여</h3>
                <p>6</p>
              </div>
            </div>
          </div>
          <div className="right-section">
            <div className="card">
              <h3>TOP 3 부서</h3>
              {loading ? (
                <p>로딩 중...</p>
              ) : error ? (
                <p className="error-message">{error}</p>
              ) : (
                <table>
                  <thead>
                    <tr>
                      <th>부서</th>
                      <th>매출 총액</th>
                    </tr>
                  </thead>
                  <tbody>
                    {topDepartments.map((dept, index) => (
                      <tr key={index}>
                        <td>{dept.department_name}</td>
                        <td>{dept.department_totalsales.toLocaleString()}원</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </div>
          </div>
        </div>

        <div className="chart-container" style={{ height: "300px" }}>
          <h3>부서별 사원 수</h3>
          {loading ? (
            <p>로딩 중...</p>
          ) : error ? (
            <p className="error-message">{error}</p>
          ) : (
            <Line data={chartData} options={chartOptions} />
          )}
        </div>
      </div>
    </>
  );
};

export default Main;