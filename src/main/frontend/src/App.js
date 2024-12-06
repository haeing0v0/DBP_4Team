import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from './pages/main.js';
import CheckIn from './pages/check_in.js';
import Check from './pages/check.js'; 
import Departments from './pages/departments.js'; 
import Positions from './pages/positions.js'; 
import Register from './pages/register.js'; 
import Salary from './pages/salary.js'; 
import Incentives from './pages/incentives.js'; 
import Employees from './pages/employees.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/check-in" element={<CheckIn />} />
        <Route path="/check" element={<Check />} />
        <Route path="/departments" element={<Departments />} />
        <Route path="/positions" element={<Positions />} />
        <Route path="/register" element={<Register />} />
        <Route path="/salary" element={<Salary />} />
        <Route path="/incentives" element={<Incentives />} />
        <Route path="/employees" element={<Employees />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
