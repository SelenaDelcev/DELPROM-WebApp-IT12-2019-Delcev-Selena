import React, { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import axios from 'axios';
import jwt_decode from 'jwt-decode';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

interface LoginProps {
  handleLogin: (accessToken: string, isAdmin: boolean) => void;
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

      if (response.status === 200) {
        const accessToken = response.data.accessToken;

        // Extract the 'uloga' value from the accessToken
        const decodedToken: any = jwt_decode(accessToken);
        const uloga = decodedToken.uloga;

        // Check if the user is an admin based on the 'uloga' value
        const isAdmin = uloga === 'ADMIN';

        handleLogin(accessToken, isAdmin);
        localStorage.setItem('accessToken', accessToken);
        history.push('/home');
        toast.success("Uspesno ste se prijavili");
      } else {
        toast.error("Korisnicko ime ili lozinka su neispravni");
      }
    } catch (error) {
      console.error(error);
      toast.error("Korisnicko ime ili lozinka su neispravni. Pokusajte ponovo");
    }
  };

  return (
    <div className="login-container">
      <div className="auth-form">
        <h2>Prijavi se</h2>
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label>Email:</label>
            <input
              type="text"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
            />
          </div>
          <div className="form-group">
            <label>Lozinka:</label>
            <input
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </div>
          <button type="submit">Potvrdi</button>
          <hr />
          <div className='signup'>Nemas nalog? Registruj se <Link to='/register'>ovde</Link></div>
        </form>
      </div>
    </div>
  );
};

export default Login;
