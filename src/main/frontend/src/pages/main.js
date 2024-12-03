import React from "react";
import "../styles/main.css";
import Sidebar from '../components/Sidebar';

const Main = () => {
  return (
    <>
        <Sidebar />
        <div className="main-container">
            <h1 className="dashboard-title">인사 관리 대시보드</h1>
            <div className="statistics-cards">
                <div className="card">
                <h3>총 사원수</h3>
                <p>9</p>
                </div>
                <div className="card">
                <h3>총 부서 수</h3>
                <p>6</p>
                </div>
                <div className="card">
                <h3>TOP 3 부서</h3>
                <table>
                    <thead>
                    <tr>
                        <th>부서</th>
                        <th>매출 총액</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>인사팀</td>
                        <td>4,500,000</td>
                    </tr>
                    <tr>
                        <td>영업팀</td>
                        <td>3,200,000</td>
                    </tr>
                    <tr>
                        <td>홍보팀</td>
                        <td>3,015,000</td>
                    </tr>
                    </tbody>
                </table>
                </div>
                <div className="card">
                <h3>성비 통계</h3>
                <p>
                    남: <span style={{ color: "blue" }}>5</span> 여:{" "}
                    <span style={{ color: "red" }}>4</span>
                </p>
                </div>
            </div>

            {/* 하단 그래프 */}
            <div className="chart-container">
                <h3>부서별 사원 수</h3>
                <canvas id="departmentChart"></canvas>
            </div>
            </div>
    </>
  );
};

export default Main;
