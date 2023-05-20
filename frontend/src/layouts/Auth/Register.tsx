import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import axios from 'axios';

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
                alert("Korisnik je uspesno registrovan");
                history.push('/login');
            } else {
                alert("Registracija nije uspela");
            }
        } catch (error) {
            console.error(error);
        }
    };

    return (
        <div>
            <h2>Registracija</h2>
            <form onSubmit={handleSubmit}>
                <label>Ime:</label>
                <input
                    type="text"
                    value={ime}
                    onChange={(event) => setIme(event.target.value)}
                />
                <label>Prezime:</label>
                <input
                    type="text"
                    value={prezime}
                    onChange={(event) => setPrezime(event.target.value)}
                />
                <label>Datum rodjenja:</label>
                <input
                    type="date"
                    value={datumRodjenja}
                    onChange={(event) => setDatumRodjenja(event.target.value)}
                />
                <label>Email:</label>
                <input
                    type="text"
                    value={email}
                    onChange={(event) => setEmail(event.target.value)}
                />
                <label>Telefon:</label>
                <input
                    type="number"
                    value={telefon}
                    onChange={(event) => setTelefon(event.target.value)}
                />
                <label>Adresa:</label>
                <input
                    type="text"
                    value={adresa}
                    onChange={(event) => setAdresa(event.target.value)}
                />
                <label>Username:</label>
                <input
                    type="text"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
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