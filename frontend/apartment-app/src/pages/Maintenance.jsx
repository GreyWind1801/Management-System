// src/pages/Maintenance.jsx
import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const Maintenance = () => {
  const [requests, setRequests] = useState([]);
  const [residentInfo, setResidentInfo] = useState(null);


  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current-user', config)
      .then(res => {
        setResidentInfo(res.data);
      })
      .catch(err => {
        console.error('Failed to fetch resident info:', err);
      });
  }, []);

  useEffect(() => {
    const fetchRequests = async () => {
      if (!residentInfo) return; // Wait until residentInfo is available

      try {
        const id = residentInfo.userId;
        const token = localStorage.getItem("token");
        const res = await axios.get(`http://localhost:9090/api/maintenance/my-request/${id}`, config);
        setRequests(res.data);
      } catch (err) {
        console.error("Error fetching maintenance requests", err);
      }
    };
    fetchRequests();
  }, [residentInfo]);

  return (
    <div className="p-4">
      <div className="flex justify-between items-center mb-4">
        <h1 className="text-2xl font-bold">My Maintenance Requests</h1>
        <Link to="/maintenance/create" className="bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
          Create New Request
        </Link>
      </div>
      <div className="space-y-4">
        {requests.map((req) => (
          <div key={req.requestId} className="p-4 border rounded shadow">
            <p><strong>Description:</strong> {req.description}</p>
            <p><strong>Status:</strong> {req.status}</p>
            <p><strong>Created At:</strong> {new Date(req.createdAt).toLocaleString()}</p>
            <Link to={`/maintenance/update/${req.requestId}`} className="text-blue-600 underline mt-2 inline-block">Update</Link>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Maintenance;
