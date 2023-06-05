import React, { useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import axios from 'axios';
import { toast } from 'react-toastify';

export const Register = () => {
    const [ime, setIme] = useState('');
    const [prezime, setPrezime] = useState('');
    const [datumRodjenja, setDatumRodjenja] = useState('');
    const [telefon, setTelefon] = useState('');
    const [adresa, setAdresa] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const handleSubmit = async (event: React.FormEvent) => {
        event.preventDefault();

        try {
            const payload = {
                ime: ime,
                prezime: prezime,
                datumRodjenja: datumRodjenja,
                email: email,
                telefon: telefon,
                adresa: adresa,
                username: username,
                password: password,
            };

            const response = await axios.post('http://localhost:8080/api/auth/register', payload, {
                headers: {
                    'Content-Type': 'application/json',
                    'accept': 'application/hal+json'
                },
            });

            console.log("Response:", response);

            console.log(response.data);

            if (response.status === 201) {
                toast.success("Korisnik je uspesno registrovan");
                history.push('/login');
            } else {
                toast.error("Registracija nije uspela");
            }
        } catch (error) {
            toast.error("Neispravni podaci za registraciju. Proverite unos podataka");
            console.error(error);
        }
    };

    return (
        <div className="register-container">
            <div className="auth-form">
                <h2>Registracija</h2>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label>Ime:</label>
                        <input
                            type="text"
                            value={ime}
                            onChange={(event) => setIme(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Prezime:</label>
                        <input
                            type="text"
                            value={prezime}
                            onChange={(event) => setPrezime(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Datum rodjenja:</label>
                        <input
                            type="date"
                            value={datumRodjenja}
                            onChange={(event) => setDatumRodjenja(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Email:</label>
                        <input
                            type="text"
                            value={email}
                            onChange={(event) => setEmail(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Telefon:</label>
                        <input
                            type="number"
                            value={telefon}
                            onChange={(event) => setTelefon(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label>Adresa:</label>
                        <input
                            type="text"
                            value={adresa}
                            onChange={(event) => setAdresa(event.target.value)}
                        />
                    </div>
                    <div className="form-group">
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(event) => setUsername(event.target.value)}
                    />
                    </div>
                    <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(event) => setPassword(event.target.value)}
                    />
                    </div>
                    <button type="submit">Potvrdi</button>
                    <a className='signup'>Imas nalog? Prijavi se <Link to='/login'>ovde</Link></a>
                </form>
            </div>
        </div>
    );
};