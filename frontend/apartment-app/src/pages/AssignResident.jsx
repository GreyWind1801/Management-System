import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

const AssignResident = () => {
  const [apartments, setApartments] = useState([]);
  const [formData, setFormData] = useState({
    user: '',
    residentType: '',
    apartmentId: '',
    startDate: '',
  });
  const token = localStorage.getItem('token');

  const API_BASE = 'http://localhost:9090/api';

  useEffect(() => {
    axios.get(`${API_BASE}/apartments`, config)
      .then(res => setApartments(res.data))
      .catch(err => console.error('Failed to fetch apartments:', err));
  }, []);

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {

      const userRes = await axios.get(`http://localhost:9090/api/users/by-email?email=${encodeURIComponent(formData.email)}`, config);

      const userId = userRes.data.userId;

      const assignPayload = {
        user: { userId: userId },
        apartment: { apartmentId: formData.apartmentId},
        residentType: formData.residentType,
        startDate: formData.startDate,
      };

      await axios.post(`${API_BASE}/apartment-residents`, assignPayload, config);
      alert('Resident assigned successfully');
      setFormData({ user: '', residentType: '', apartmentId: '', startDate: '' });
    } catch (err) {
      console.error('Assignment failed:', err);
      alert('Failed to assign resident');
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">Assign New Resident</h2>
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
        <input name="email" type="email" placeholder="Email" className="w-full border px-3 py-2 rounded" value={formData.email} onChange={handleChange} required />
        <select name="residentType" value={formData.residentType} onChange={handleChange} className="w-full border px-3 py-2 rounded" required>
          <option value="">Select Resident Type</option>
          <option value="OWNER">Owner</option>
          <option value="TENANT">Tenant</option>
          <option value="GUEST">Guest</option>
        </select>

        <select name="apartmentId" value={formData.apartmentId} onChange={handleChange} className="w-full border px-3 py-2 rounded" required>
          <option value="">Select Apartment</option>
          {apartments.map(apartment => (
            <option key={apartment.apartmentId} value={apartment.apartmentId}>
              {apartment.flatNumber} - {apartment.block}
            </option>
          ))}
        </select>

        <input type="date" name="startDate" className="w-full border px-3 py-2 rounded" value={formData.startDate} onChange={handleChange} required />

        <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">Assign</button>
      </form>
    </div>
  );
};

export default AssignResident;
