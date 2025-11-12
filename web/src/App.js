import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Directory from './pages/Directory';
import Home from './pages/Home';


function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/student-directory" element={<Directory/>} />
        {/* <Route path="/profile" element={<Profile/>} /> */}
      </Routes>
    </BrowserRouter>
  );
}

export default App;
