import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

interface LoginProps {
  handleLogin: () => void;
}

const Login: React.FC<LoginProps> = ({ handleLogin }) => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const history = useHistory();

  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    try {
      const payload = {
        usernameOrEmail: email,
        password: password,
      };

      const response = await axios.post('http://localhost:8080/api/auth/login', payload, {
        headers: {
          'Content-Type': 'application/json',
          'accept': 'application/hal+json'
        },
      });

      console.log("Response:", response);

      console.log(response.data);

      if (response.data.message === "Email not exists") {
        alert("Email does not exist");
      } else if (response.status === 200) {
        handleLogin(); // Call the handleLogin function passed as prop
        history.push('/home');
      } else {
        alert("Incorrect Email and Password do not match");
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <label>Email:</label>
        <input
          type="text"
          value={email}
          onChange={(event) => setEmail(event.target.value)}
        />
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />
        <button type="submit">Potvrdi</button>
      </form>
    </div>
  );
};

export default Login;


