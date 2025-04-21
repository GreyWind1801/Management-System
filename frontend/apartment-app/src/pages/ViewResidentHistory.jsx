import React, { useState } from 'react';
import axios from 'axios';

const ViewResidentHistory = () => {
  const [residentId, setResidentId] = useState('');
  const [history, setHistory] = useState([]);
  const [error, setError] = useState('');

  const handleSearch = async () => {
    if (!residentId) {
      setError('Please enter a Resident ID.');
      return;
    }

    try {
      const res = await axios.get(`http://localhost:9090/api/apartment-residents/history/resident/${residentId}`);
      setHistory(res.data);
      setError('');
    } catch (err) {
      console.error(err);
      setError('Resident history not found or error occurred.');
      setHistory([]);
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-xl font-semibold mb-4">View Resident History</h2>

      <div className="mb-4 flex gap-2">
        <input
          type="text"
          placeholder="Enter Resident ID"
          value={residentId}
          onChange={(e) => setResidentId(e.target.value)}
          className="border px-3 py-2 rounded w-1/2"
        />
        <button
          onClick={handleSearch}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Search
        </button>
      </div>

      {error && <p className="text-red-500">{error}</p>}

      {history.length > 0 && (
        <div className="space-y-4">
          {history.map((entry) => (
            <div key={entry.id} className="bg-white border border-gray-200 p-4 rounded shadow">
              <p><strong>Apartment:</strong> {entry.apartment.name} (Block {entry.apartment.block}, Floor {entry.apartment.floor})</p>
              <p><strong>Role:</strong> {entry.residentType}</p>
              <p><strong>Start Date:</strong> {entry.startDate}</p>
              <p><strong>End Date:</strong> {entry.endDate || 'Present'}</p>
              <p><strong>Currently Assigned:</strong> {entry.isCurrent ? 'Yes' : 'No'}</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default ViewResidentHistory;
