import React, { useState } from "react";
import axios from "axios";
import "../styles/check_in.css";
import Sidebar from "../components/Sidebar";

const CheckIn = () => {
  const [formData, setFormData] = useState({
    employeeId: "",
    checkInTime: "",
    checkOutTime: "",
    workDate: "",
  });
  const [message, setMessage] = useState(""); 

  const handleInputChange = (e) => {
    const { id, value } = e.target;
    setFormData({ ...formData, [id]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/api/commute/enter", {
        employee_id: formData.employeeId,
        startWorkTime: formData.checkInTime,
        finishWorkTime: formData.checkOutTime,
        workDay: formData.workDate,
      });

      setMessage(response.data);
      alert(response.data); 

      setFormData({ employeeId: "", checkInTime: "", checkOutTime: "", workDate: "" });
    } catch (err) {
      console.error(err);
      setMessage(err.response?.data || "출퇴근 기록 저장 중 오류가 발생했습니다.");
      alert(err.response?.data || "출퇴근 기록 저장 중 오류가 발생했습니다."); 
    }
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">출퇴근 관리</h1>

          <div className="checkin-wrap">
            <h3 className="sub-title">출퇴근 정보 입력</h3>
            <form className="attendance-form" onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="employeeId">직원 ID</label>
                <input
                  type="text"
                  id="employeeId"
                  value={formData.employeeId}
                  onChange={handleInputChange}
                  placeholder="직원 ID 입력"
                />
              </div>
              <div className="time-group">
                <div className="time-div">
                  <label htmlFor="checkInTime">출근 시간</label>
                  <input
                    type="time"
                    id="checkInTime"
                    value={formData.checkInTime}
                    onChange={handleInputChange}
                    placeholder="00:00"
                  />
                </div>
                <div className="time-div">
                  <label htmlFor="checkOutTime">퇴근 시간</label>
                  <input
                    type="time"
                    id="checkOutTime"
                    value={formData.checkOutTime}
                    onChange={handleInputChange}
                    placeholder="00:00"
                  />
                </div>
              </div>
              <div className="form-group">
                <label htmlFor="workDate">근무 일자</label>
                <input
                  type="date"
                  id="workDate"
                  value={formData.workDate}
                  onChange={handleInputChange}
                  placeholder="MM/DD/YYYY"
                />
              </div>
              <div className="button-div">
                <button type="submit" className="submit-button">등록</button>
              </div>
            </form>
          </div>
          {message && <p className="message">{message}</p>}
        </div>
      </div>
    </>
  );
};

export default CheckIn;
