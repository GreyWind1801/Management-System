import React, { useState } from 'react';
import axios from 'axios';

const VisitorsLog = () => {
  const [logs, setLogs] = useState([]);
  const [fromDate, setFromDate] = useState('');
  const [toDate, setToDate] = useState('');
  const [loading, setLoading] = useState(false);

  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  const fetchLogs = async () => {
    setLoading(true);
    try {
      const response = await axios.get(
        `http://localhost:9090/api/visitors`,
        config
      );
      setLogs(response.data);
    } catch (error) {
      console.error('Error fetching logs:', error);
      alert('Failed to fetch logs');
    }
    setLoading(false);
  };

  const handleLoginClick = () => {
    // Navigate to log in visitor page
    window.location.href = '/log-visitor';
  };

  const handleLogoutClick = () => {
    const visitorId = prompt("Enter Visitor ID to log out:");
    if (visitorId) {
      axios
        .put(`http://localhost:9090/api/visitors/logout/${visitorId}`, {}, config)
        .then(() => alert("Visitor logged out successfully"))
        .catch(() => alert("Failed to log out visitor"));
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Visitor Logs</h2>

      <div className="space-x-4 mb-6">
        <button onClick={handleLoginClick} className="bg-blue-600 text-white px-4 py-2 rounded">
          Log In Visitor
        </button>
        <button onClick={handleLogoutClick} className="bg-red-600 text-white px-4 py-2 rounded">
          Log Out Visitor
        </button>
      </div>

      <div className="mb-4 flex space-x-4">
        <input
          type="date"
          value={fromDate}
          onChange={(e) => setFromDate(e.target.value)}
          className="border px-3 py-2 rounded"
        />
        <input
          type="date"
          value={toDate}
          onChange={(e) => setToDate(e.target.value)}
          className="border px-3 py-2 rounded"
        />
        <button onClick={fetchLogs} className="bg-green-600 text-white px-4 py-2 rounded">
          Fetch Logs
        </button>
      </div>

      {loading ? (
        <p>Loading logs...</p>
      ) : (
        <table className="w-full border border-collapse">
          <thead>
            <tr className="bg-gray-200">
              <th className="border p-2">Visitor ID</th>
              <th className="border p-2">Visitor Name</th>
              <th className="border p-2">Phone</th>
              <th className="border p-2">Purpose</th>
              <th className="border p-2">Apartment</th>
              <th className="border p-2">Check In</th>
              <th className="border p-2">Check Out</th>
            </tr>
          </thead>
          <tbody>
            {logs.map((log) => (
              <tr key={log.id}>
                <td className="border p-2">{log.visitorId}</td>
                <td className="border p-2">{log.visitorName}</td>
                <td className="border p-2">{log.phone}</td>
                <td className="border p-2">{log.purpose}</td>
                <td className="border p-2">{log.visitApartment.flatNumber}</td>
                <td className="border p-2">{log.checkinTime}</td>
                <td className="border p-2">{log.checkoutTime || "Still Inside"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default VisitorsLog;
