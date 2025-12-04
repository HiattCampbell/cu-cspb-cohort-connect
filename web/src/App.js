import { useState } from "react";
import { BrowserRouter, Route, Routes, useLocation } from 'react-router-dom';
import { getToken, setToken } from "./api";
import Nav from './components/Nav';
import Directory from './pages/Directory';
import Home from './pages/Home';
import Login from './pages/Login';
import Profile from './pages/Profile';
import Signup from './pages/Signup';
import './styles/App.css';


function App() {
  const [isAuthed, setIsAuthed] = useState(!!getToken());

  function handleLogout() {
    setToken(null);
    setIsAuthed(false);
  }

  return (
    <BrowserRouter>
      <AppLayout isAuthed={isAuthed} setIsAuthed={setIsAuthed} handleLogout={handleLogout}/>
    </BrowserRouter>
  );
}

function AppLayout({ isAuthed, setIsAuthed, handleLogout }) {
  const location = useLocation();
  const hideNav = location.pathname === "/login" || location.pathname === "/signup" ;
  return (
    <>
    { !hideNav && (<Nav isAuthed={isAuthed} onLogout={handleLogout}></Nav>) }
    <Routes>
      
      <Route path="/login" element={<Login onLogin={() => setIsAuthed(true)} />}
      />
       <Route path="/signup" element={<Signup onSignup={() => setIsAuthed(true)} />}
      />
      <Route path="/" element={<Home/>} />
      <Route path="/student-directory" element={<Directory/>} />
      <Route path="/profile" element={<Profile/>} />
    </Routes>
  </>
  )
 
}

export default App;
