// src/pages/UpdateRequest.jsx
import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";

const UpdateRequest = () => {
  const { requestId } = useParams();
  const [description, setDescription] = useState("");
  const [status, setStatus] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchRequest = async () => {
      try {
        const token = localStorage.getItem("token");
        const res = await axios.get(`/api/maintenance/${requestId}`, {
          headers: { Authorization: `Bearer ${token}` }
        });
        setStatus(res.data.status);
      } catch (err) {
        console.error("Error fetching request", err);
      }
    };
    fetchRequest();
  }, [requestId]);

  const handleUpdate = async (e) => {
    e.preventDefault();
    try {
      const token = localStorage.getItem("token");
      await axios.put(
        `http://localhost:9090/api/maintenance/${requestId}`,
        { status, description },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      navigate("/maintenance");
    } catch (err) {
      console.error("Error updating request", err);
    }
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-semibold mb-4">Update Maintenance Request</h2>
      <form onSubmit={handleUpdate} className="space-y-4">
        <select
          className="w-full p-2 border rounded"
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          required
        >
          <option value="">Select Status</option>
          <option value="Open">Open</option>
          <option value="In Progress">In Progress</option>
          <option value="Resolved">Resolved</option>
        </select>
        <button type="submit" className="bg-yellow-500 text-white px-4 py-2 rounded hover:bg-yellow-600">
          Update Status
        </button>
      </form>
    </div>
  );
};

export default UpdateRequest;
