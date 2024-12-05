import './App.css';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from './pages/main.js';
import CheckIn from './pages/check_in.js';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/check-in" element={<CheckIn />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
