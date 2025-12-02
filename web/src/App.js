import { useState } from "react";
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { getToken } from "./api";
import './App.css';
import Directory from './pages/Directory';
import Home from './pages/Home';
import Login from './pages/Login';





function App() {
  const [isAuthed, setIsAuthed] = useState(!!getToken());

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login onLogin={() => setIsAuthed(true)} />}
        />
        <Route path="/" element={<Home/>} />
        <Route path="/student-directory" element={<Directory/>} />
        {/* <Route path="/profile" element={<Profile/>} /> */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
