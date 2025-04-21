import React, { useEffect, useState } from 'react';
import axios from 'axios';

const ViewResidents = () => {
  const [residents, setResidents] = useState([]);

  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current', config)
      .then(res => setResidents(res.data))
      .catch(err => console.error('Failed to fetch current residents:', err));
  }, []);

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">Current Residents</h2>
      {residents.length === 0 ? (
        <p>No residents assigned currently.</p>
      ) : (
        <div className="grid gap-4">
          {residents.map((res) => (
            <div key={res.id} className="bg-white rounded shadow p-4 border border-gray-200">
              <h3 className="text-lg font-semibold">{res.name}</h3>
              <p><strong>Email:</strong> {res.email}</p>
              <p><strong>Phone:</strong> {res.phone || 'N/A'}</p>
              <p><strong>Role:</strong> {res.residentType}</p>
              <p><strong>Apartment:</strong> {res.apartment?.block}, Floor {res.apartment?.flatNumber}</p>
              <p><strong>Start Date:</strong> {res.startDate}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ViewResidents;
