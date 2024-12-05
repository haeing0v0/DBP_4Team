import React from "react";
import "../styles/check.css";
import Sidebar from '../components/Sidebar';

const CheckIn = () => {
  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">출퇴근 등록</h1>
          <div className="checkin-wrap">
            <h3 className="sub-title">출퇴근 정보 입력</h3>
            <form className="attendance-form">
              <div className="form-group">
                <label htmlFor="employeeId">직원 ID</label>
                <input
                  type="text"
                  id="employeeId"
                  placeholder="Search employee ID"
                />
              </div>
              <div className="time-group">
                <div className="time-div">
                  <label htmlFor="checkInTime">출근 시간</label>
                  <input
                    type="time"
                    id="checkInTime"
                    placeholder="00:00"
                  />
                </div>
                <div className="time-div">
                  <label htmlFor="checkOutTime">퇴근 시간</label>
                  <input
                    type="time"
                    id="checkOutTime"
                    placeholder="00:00"
                  />
                </div>
              </div>
              <div className="form-group">
                <label htmlFor="workDate">근무 일자</label>
                <input
                  type="date"
                  id="workDate"
                  placeholder="MM/DD/YYYY"
                />
              </div>
              <div className="button-div">
                <button type="submit" className="submit-button">등록</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default CheckIn;
