// src/pages/CreateRequest.jsx
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const CreateRequest = () => {
  const [description, setDescription] = useState("");
  const navigate = useNavigate();
  const [apartmentDetails, setApartmentDetails] = useState(null);
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
          setApartmentDetails(res.data.apartment);
        })
        .catch(err => {
          console.error('Failed to fetch resident info:', err);
        });
    }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        user: { userId: residentInfo.userId},
        apartment: { apartmentId: apartmentDetails.apartmentId},
        description,
        status: "Open",
        assignedStaff: { userId: "16"},
      };

      await axios.post("http://localhost:9090/api/maintenance",payload, config);
      navigate("/maintenance");
    } catch (err) {
      console.error("Error creating request", err);
    }
  };

  return (
    <div className="p-4 max-w-lg mx-auto">
      <h2 className="text-xl font-semibold mb-4">Create Maintenance Request</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <textarea
          required
          placeholder="Describe your issue"
          className="w-full p-2 border rounded"
          rows="4"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <button type="submit" className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
          Submit Request
        </button>
      </form>
    </div>
  );
};

export default CreateRequest;
