import React from 'react';
import { useNavigate } from 'react-router-dom';

const Residents = () => {
  const navigate = useNavigate();

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-6">Resident Management</h2>

      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        <button
          className="bg-blue-600 text-white py-2 px-4 rounded hover:bg-blue-700"
          onClick={() => navigate('/residents/view')}
        >
          View Residents
        </button>

        <button
          className="bg-green-600 text-white py-2 px-4 rounded hover:bg-green-700"
          onClick={() => navigate('/residents/assign')}
        >
          Assign Resident
        </button>

        <button
          className="bg-yellow-500 text-white py-2 px-4 rounded hover:bg-yellow-600"
          onClick={() => navigate('/residents/update-role')}
        >
          Update Role
        </button>

        <button
          className="bg-red-500 text-white py-2 px-4 rounded hover:bg-red-600"
          onClick={() => navigate('/residents/history')}
        >
          View History
        </button>
      </div>
    </div>
  );
};

export default Residents;
