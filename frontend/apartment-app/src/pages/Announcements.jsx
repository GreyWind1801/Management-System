import React, { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Announcements = () => {
  const [announcements, setAnnouncements] = useState([]);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  useEffect(() => {
    axios.get('http://localhost:9090/api/announcements', {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => setAnnouncements(res.data))
      .catch(err => console.error('Error fetching announcements:', err));
  }, []);

  return (
    <div className="p-6">
      <div className="flex justify-between items-center mb-4">
        <h2 className="text-2xl font-bold">Community Announcements</h2>
        <button 
          onClick={() => navigate('/add-announcement')}
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Add Announcement
        </button>
      </div>

      <div className="space-y-4">
        {announcements.map((announcement) => (
          <div key={announcement.id} className="bg-white p-4 rounded shadow">
            <h3 className="text-lg font-semibold">{announcement.title}</h3>
            <p className="text-gray-600 text-sm mb-2">{new Date(announcement.createdAt).toLocaleString()}</p>
            <p>{announcement.content}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Announcements;
