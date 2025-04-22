import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const AddAnnouncement = () => {
  const [title, setTitle] = useState('');
  const [message, setMessage] = useState('');
  const [residentInfo, setResidentInfo] = useState(null);
  const navigate = useNavigate();
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

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const payload = {
        title,
        message,
        author: {userId: residentInfo.userId}
      };

      await axios.post('http://localhost:9090/api/announcements', payload, config);

      alert('Announcement added!');
      navigate('/announcements');
    } catch (error) {
      console.error('Error creating announcement:', error);
      alert('Failed to create announcement.');
    }
  };

  return (
    <div className="p-6 max-w-lg mx-auto">
      <h2 className="text-2xl font-bold mb-4">Create New Announcement</h2>
      <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow">
        <input 
          type="text"
          placeholder="Title"
          className="w-full border px-3 py-2 rounded"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />
        <textarea 
          placeholder="Content"
          className="w-full border px-3 py-2 rounded"
          rows={6}
          value={message}
          onChange={(e) => setMessage(e.target.value)}
          required
        />
        <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">
          Submit
        </button>
      </form>
    </div>
  );
};

export default AddAnnouncement;
