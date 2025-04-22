import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LogVisitor = () => {
  const [visitorName, setVisitorName] = useState('');
  const [phone, setPhone] = useState('');
  const [purpose, setPurpose] = useState('');
  const [apartmentId, setApartmentId] = useState('');
  const [apartments, setApartments] = useState([]);

  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  // Fetch apartments for dropdown
  useEffect(() => {
    axios.get('http://localhost:9090/api/apartments', config)
      .then(res => setApartments(res.data))
      .catch(err => console.error('Failed to load apartments:', err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      visitorName,
      phone,
      purpose,
      visitApartment: {apartmentId: apartmentId},
    };

    try {
      await axios.post('http://localhost:9090/api/visitors', payload, config);
      alert('Visitor logged in successfully!');
      navigate('/visitors-log');
    } catch (error) {
      console.error('Error logging visitor:', error);
      alert('Failed to log visitor.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 bg-white p-6 rounded shadow">
      <h2 className="text-2xl font-semibold mb-4">Log In Visitor</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <input
          type="text"
          placeholder="Visitor Name"
          value={visitorName}
          onChange={(e) => setVisitorName(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        />
        <input
          type="text"
          placeholder="Phone Number"
          value={phone}
          onChange={(e) => setPhone(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        />
        <input
          type="text"
          placeholder="Purpose"
          value={purpose}
          onChange={(e) => setPurpose(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        />

        <select
          value={apartmentId}
          onChange={(e) => setApartmentId(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        >
          <option value="">Select Apartment</option>
          {apartments.map(apartment => (
            <option key={apartment.apartmentId} value={apartment.apartmentId}>
              {apartment.block} - {apartment.flatNumber}
            </option>
          ))}
        </select>

        <button type="submit" className="w-full bg-green-600 text-white py-2 rounded hover:bg-green-700">
          Submit
        </button>
      </form>
    </div>
  );
};

export default LogVisitor;
