// src/pages/Apartments.jsx
import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom'; // ✅ Step 2.4: Import Link

const Apartments = () => {
  const [apartments, setApartments] = useState([]);
  const [formData, setFormData] = useState({ block: '', flatNumber: '', rentAmount: '', billingCycle: '' });
  const [editMode, setEditMode] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const API_URL = 'http://localhost:9090/api/apartments';
  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
  
  const fetchApartments = async () => {
    try {
      const res = await axios.get(API_URL, config);
      setApartments(res.data);
    } catch (err) {
      console.error('Failed to load apartments:', err);
    }
  };

  useEffect(() => {
    fetchApartments();
  }, []);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editMode) {
        await axios.put(`${API_URL}/${formData.apartmentId}`, formData, config);
      } else {
        await axios.post(API_URL, formData, config);
      }
      setFormData({ block: '', flatNumber: '', rentAmount: '', billingCycle: '' });
      setEditMode(false);
      fetchApartments();
    } catch (err) {
      console.error('Error saving apartment:', err);
    }
  };

  const handleEdit = (apartment) => {
    setFormData(apartment);
    setEditMode(true);
    setEditingId(apartment.id);
  };

  const handleDelete = async (apartmentId) => {
    try {
      await axios.delete(`${API_URL}/${apartmentId}`, config);
      fetchApartments();
    } catch (err) {
      console.error('Delete failed:', err);
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Apartment Management</h2>

      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-4 rounded-md shadow-md">
        <input type="text" name="block" placeholder="Block" value={formData.block} onChange={handleChange} className="w-full border px-3 py-2 rounded" required />
        <input type="text" name="flatNumber" placeholder="Flat Number" value={formData.floor} onChange={handleChange} className="w-full border px-3 py-2 rounded" required />
        <input type="number" name="rentAmount" placeholder="Rent Amount" value={formData.rentAmount} onChange={handleChange} className="w-full border px-3 py-2 rounded" />
        <input type="text" name="billingCycle" placeholder="Billing Cycle (e.g., Monthly)" value={formData.billingCycle} onChange={handleChange} className="w-full border px-3 py-2 rounded" />
        <button type="submit" className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
          {editMode ? 'Update Apartment' : 'Add Apartment'}
        </button>
      </form>

      <div className="mt-6">
        <h3 className="text-xl font-semibold mb-2">Apartments List</h3>
        <div className="space-y-3">
          {apartments.map((apt) => (
            <div key={apt.id} className="p-4 bg-gray-100 rounded-md shadow-sm flex justify-between items-center">
              <div>
                <p className="font-medium">Block - {apt.block}, Floor {apt.flatNumber}</p>
                <p className="text-sm text-gray-600">Rent: ₹{apt.rentAmount || 'N/A'}, Billing: {apt.billingCycle || 'N/A'}</p>
                
                {/* ✅ Step 2.4: Add Link to Residents Page */}
                <Link
                  to={`/apartments/${apt.apartmentId}/residents`}
                  className="text-blue-600 hover:underline text-sm"
                >
                  View Residents
                </Link>
              </div>
              <div className="space-x-2">
                <button onClick={() => handleEdit(apt)} className="bg-yellow-400 text-white px-3 py-1 rounded">Edit</button>
                <button onClick={() => handleDelete(apt.apartmentId)} className="bg-red-500 text-white px-3 py-1 rounded">Delete</button>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Apartments;
