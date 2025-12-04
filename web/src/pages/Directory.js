import { useEffect, useState } from "react";
import { apiRequest } from "../api";

function Directory() {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState(null);
  const [email, setEmail] = useState("");
  const [type, setType] = useState("");
  const [displayName, setDisplayName] = useState("");
  const [lookingForMentee, setLookingForMentee] = useState([]);
  const [lookingForMentor, setLookingForMentor] = useState([]);
  const [mentorTo, setMentoTo] = useState(null);


  // load existing users 
  useEffect(() => {
    async function getUsers() {
      const data = await apiRequest('/api/v1/users');
      setUsers(data);
    }
    getUsers().catch(err => {
      console.log("Error fetching users: ", err);
      setError("failed to load users");
    });
  }, []); 

  return (
    <>
      <h1>Student Directory</h1>

      <div className="users">
      {users.map(user => (
        <div className="user" key={user.id}>
          <h2>{user.displayName}</h2>
          <h3>{user.email}</h3>
          <h3>{user.type}</h3>
          {user.lookingForMentee ? 
          (<h3>Looking for mentee</h3>) :
          ('')}
          {user.lookingForMentee ? 
          (<h3>Looking for mentor</h3>) :
          ('')}
          <h3>{user.lookingForMentee}</h3>
          {mentorTo != null ? (<h3>Mentor to {mentorTo.email}</h3>) : ''}
        </div>
      ))}
      </div>
\    </>
  );
}

export default Directory;