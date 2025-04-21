import React, { useEffect, useState } from 'react';
import axios from 'axios';

const UpdateRole = () => {
  const [residents, setResidents] = useState([]);
  const [selectedResidentId, setSelectedResidentId] = useState('');
  const [newRole, setNewRole] = useState('');

  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current')
      .then(res => setResidents(res.data))
      .catch(err => console.error('Failed to fetch residents:', err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedResidentId || !newRole) {
      alert("Please select a resident and a new role.");
      return;
    }

    try {
      await axios.put(`http://localhost:9090/api/apartment-residents/${selectedResidentId}/update-role`, {
        residentType: newRole,
      });
      alert('Resident role updated successfully');
      setSelectedResidentId('');
      setNewRole('');
    } catch (err) {
      console.error('Failed to update role:', err);
      alert('Role update failed');
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">Update Resident Role</h2>
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
        <select
          value={selectedResidentId}
          onChange={(e) => setSelectedResidentId(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        >
          <option value="">Select Resident</option>
          {residents.map((resident) => (
            <option key={resident.id} value={resident.id}>
              {resident.name} - {resident.email}
            </option>
          ))}
        </select>

        <select
          value={newRole}
          onChange={(e) => setNewRole(e.target.value)}
          className="w-full border px-3 py-2 rounded"
          required
        >
          <option value="">Select New Role</option>
          <option value="OWNER">Owner</option>
          <option value="TENANT">Tenant</option>
          <option value="GUEST">Guest</option>
        </select>

        <button type="submit" className="bg-yellow-600 text-white px-4 py-2 rounded hover:bg-yellow-700">
          Update Role
        </button>
      </form>
    </div>
  );
};

export default UpdateRole;
