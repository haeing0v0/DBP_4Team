import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from './pages/main.js';
import CheckIn from './pages/check_in.js';
import Check from './pages/check.js'; 
import Departments from './pages/departments.js'; 
import Positions from './pages/positions.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/check-in" element={<CheckIn />} />
        <Route path="/check" element={<Check />} />
        <Route path="/departments" element={<Departments />} />
        <Route path="/positions" element={<Positions />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
