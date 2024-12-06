import React, { useState } from "react";
import axios from "axios";
import "../styles/incentives.css";
import Sidebar from "../components/Sidebar";

const Incentives = () => {
  const [formData, setFormData] = useState({
    employeeId: "",
    incentive: "",
  });
  const [message, setMessage] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/pay/save", null, {
        params: {
          id: formData.employeeId,
          incentive: formData.incentive,
        },
      });

      setMessage(response.data); 
      alert(response.data);
    } catch (error) {
      console.error(error);
      setMessage(error.response?.data || "인센티브 등록 중 오류가 발생했습니다.");
      alert(error.response?.data || "인센티브 등록 중 오류가 발생했습니다.");
    }

    setFormData({ employeeId: "", incentive: "" });
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
          {message && <p className="message">{message}</p>}
        </div>
      </div>
    </>
  );
};

export default Incentives;
