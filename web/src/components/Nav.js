import { NavLink } from "react-router-dom";
import logo from '../images/cohort_connect_logo.png';

function Nav({ isAuthed, onLogout }) {
  return (
    <nav>
       <ul>
        <li>
           <NavLink to='/'>
            <img src={logo} className="logo"></img>
           </NavLink>
        </li>
        <li>
          <NavLink to='student-directory'>Student Directory</NavLink>
        </li>
        <li>
          <NavLink to='profile'>Profile</NavLink>
        </li>
        <li>
          {/* if logged in logout button, if logged out login link */}
          { isAuthed ? (
            <button onClick={onLogout}>Logout</button>
          ) : (
            <NavLink to='login'>Login</NavLink>
          )}

          {!isAuthed && <NavLink to="/signup">Sign Up</NavLink>}
          
        </li>
     </ul>

    </nav>
  )
}

export default Nav;