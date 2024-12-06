import React, { useState } from "react";
import axios from "axios";
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

  const [message, setMessage] = useState("");
  const [error, setError] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const validateForm = () => {
    const requiredFields = [
      "employeeId",
      "name",
      "email",
      "phone",
      "age",
      "gender",
      "joinDate",
      "positionId",
      "salaryId",
      "departmentCode",
    ];

    for (let field of requiredFields) {
      if (!formData[field]) {
        return `필수 입력 필드 (${field})가 비어 있습니다.`;
      }
    }
    return null;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const validationError = validateForm();
    if (validationError) {
      setError(validationError);
      alert(validationError);
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/employees/create", {
        employee_id: formData.employeeId,
        employee_name: formData.name,
        email: formData.email,
        phonenumber: formData.phone,
        age: formData.age,
        gender: formData.gender,
        date: formData.joinDate,
        position_id_fk: formData.positionId,
        pay_id_fk: formData.salaryId,
        department_id_fk: formData.departmentCode,
      });

      setMessage("직원 정보가 성공적으로 등록되었습니다.");
      alert("직원 정보가 성공적으로 등록되었습니다.");

      setFormData({
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
      setError("");
    } catch (err) {
      console.error(err);

      const errorMessage = err.response?.data || "직원 정보 등록 중 오류가 발생했습니다.";
      setError(errorMessage);
      alert(errorMessage);
    }
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
                        placeholder="성별(남성/여성)"
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
            {message && <p className="success-message">{message}</p>}
            {error && <p className="error-message">{error}</p>}
          </div>
        </div>
      </div>
    </>
  );
};

export default Register;
