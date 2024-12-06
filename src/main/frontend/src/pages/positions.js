import React, { useState, useEffect } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";

const Positions = () => {
  const [positions, setPositions] = useState([]); 
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const fetchPositions = async () => {
      try {
        setLoading(true);
        const response = await axios.get("http://localhost:8080/api/companypositions");
        setPositions(response.data);
        setError("");
      } catch (err) {
        console.error("API 호출 에러:", err);
        setError("데이터를 가져오는 중 오류가 발생했습니다.");
      } finally {
        setLoading(false);
      }
    };

    fetchPositions();
  }, []);

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직급 목록</h1>
          {loading && <p>데이터를 불러오는 중입니다...</p>}
          {error && <p className="error-message">{error}</p>}
          {!loading && !error && positions.length > 0 ? (
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
                  {positions.map((position) => (
                    <tr key={position.position_id}>
                      <td>{position.position_id}</td>
                      <td>{position.position_name}</td>
                      <td>{position.explanation}</td>
                      <td>{position.position_pay.toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          ) : (
            !loading && <p>직급 정보가 없습니다.</p>
          )}
        </div>
      </div>
    </>
  );
};

export default Positions;
