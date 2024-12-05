import React, { useState } from "react";
import "../styles/incentives.css";
import Sidebar from "../components/Sidebar";

const Incentives = () => {
  const [formData, setFormData] = useState({
    employeeId: "",
    incentive: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Incentive Submitted:", formData);
    alert("인센티브가 등록되었습니다!");
    setFormData({ employeeId: "", incentive: "" }); // 입력값 초기화
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">인센티브 등록</h1>
          <form onSubmit={handleSubmit} className="incentive-form">
            <div className="input-row">
              <div className="input-group">
                <label htmlFor="employeeId">직원 ID</label>
                <input
                  type="text"
                  id="employeeId"
                  name="employeeId"
                  value={formData.employeeId}
                  onChange={handleInputChange}
                  placeholder="직원 ID를 입력하세요"
                />
              </div>
              <div className="input-group">
                <label htmlFor="incentive">인센티브</label>
                <input
                  type="text"
                  id="incentive"
                  name="incentive"
                  value={formData.incentive}
                  onChange={handleInputChange}
                  placeholder="인센티브 금액을 입력하세요"
                />
              </div>
            </div>
            <div className="button-div">
                <button type="submit" className="submit-button">
                등록
                </button>
            </div>
          </form>
        </div>
      </div>
    </>
  );
};

export default Incentives;
