import React, { useState } from "react";
import "../styles/register.css";
import Sidebar from "../components/Sidebar";

const Register = () => {
  const [formData, setFormData] = useState({
    employeeId: "",
    name: "",
    email: "",
    phone: "",
    age: "",
    gender: "",
    joinDate: "",
    positionId: "",
    salaryId: "",
    departmentCode: "",
  });

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("Submitted Data:", formData);
    alert("직원 정보가 등록되었습니다!");
  };

  return (
    <>
      <Sidebar />
      <div className="main-container">
        <div className="wrap">
          <h1 className="dashboard-title">직원 등록</h1>
          <div className="register-wrap">
            <h3 className="sub-title">직원 정보 입력</h3>
            <form onSubmit={handleSubmit}>
              <table className="register-table">
                <tbody>
                  <tr>
                    <td>직원 ID</td>
                    <td>
                      <input
                        type="text"
                        name="employeeId"
                        value={formData.employeeId}
                        onChange={handleInputChange}
                        placeholder="직원 ID"
                      />
                    </td>
                    <td>이름</td>
                    <td>
                      <input
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleInputChange}
                        placeholder="이름"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>이메일</td>
                    <td>
                      <input
                        type="email"
                        name="email"
                        value={formData.email}
                        onChange={handleInputChange}
                        placeholder="이메일"
                      />
                    </td>
                    <td>연락처</td>
                    <td>
                      <input
                        type="text"
                        name="phone"
                        value={formData.phone}
                        onChange={handleInputChange}
                        placeholder="연락처"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>나이</td>
                    <td>
                      <input
                        type="number"
                        name="age"
                        value={formData.age}
                        onChange={handleInputChange}
                        placeholder="나이"
                      />
                    </td>
                    <td>성별</td>
                    <td>
                      <input
                        type="text"
                        name="gender"
                        value={formData.gender}
                        onChange={handleInputChange}
                        placeholder="성별(남/여)"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>입사일</td>
                    <td>
                      <input
                        type="date"
                        name="joinDate"
                        value={formData.joinDate}
                        onChange={handleInputChange}
                      />
                    </td>
                    <td>직급 ID</td>
                    <td>
                      <input
                        type="text"
                        name="positionId"
                        value={formData.positionId}
                        onChange={handleInputChange}
                        placeholder="직급 ID"
                      />
                    </td>
                  </tr>
                  <tr>
                    <td>급여 ID</td>
                    <td>
                      <input
                        type="text"
                        name="salaryId"
                        value={formData.salaryId}
                        onChange={handleInputChange}
                        placeholder="급여 ID"
                      />
                    </td>
                    <td>부서 코드</td>
                    <td>
                      <input
                        type="text"
                        name="departmentCode"
                        value={formData.departmentCode}
                        onChange={handleInputChange}
                        placeholder="부서 코드"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
              <div className="button-div">
                <button type="submit" className="submit-button">
                    등록
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </>
  );
};

export default Register;
