import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const BookingPage = () => {
  const [selectedFacility, setSelectedFacility] = useState('');
  const [bookingDate, setBookingDate] = useState('');
  const [residentInfo, setResidentInfo] = useState(null);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  // Static list of facilities
  const facilities = [
    { id: 1, name: 'Swimming Pool' },
    { id: 2, name: 'Clubhouse' },
    { id: 3, name: 'Tennis Court' },
    { id: 4, name: 'Gym' },
    { id: 5, name: 'Party Hall' },
  ];

  // Fetch logged-in resident info
  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current-user', config)
      .then(res => setResidentInfo(res.data))
      .catch(err => console.error('Failed to fetch resident info:', err));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!selectedFacility || !bookingDate || !residentInfo) {
      alert('Please select a facility and date.');
      return;
    }

    const payload = {
      facilityName: selectedFacility,
      user: {userId: residentInfo.userId},
      bookingDate: bookingDate,
      status: "Pending",
    };

    try {
      await axios.post('http://localhost:9090/api/resource-bookings', payload, config);
      alert('Facility booked successfully!');
      navigate('/bookings');
    } catch (err) {
      console.error('Booking failed:', err);
      alert('Failed to book facility. Try again later.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-6 bg-white rounded shadow">
      <h2 className="text-2xl font-bold mb-6">Book a Facility</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block mb-1 font-medium">Select Facility:</label>
          <select
            value={selectedFacility}
            onChange={(e) => setSelectedFacility(e.target.value)}
            className="w-full border p-2 rounded"
            required
          >
            <option value="">-- Choose Facility --</option>
            {facilities.map((facility) => (
              <option key={facility.id} value={facility.id}>
                {facility.name}
              </option>
            ))}
          </select>
        </div>

        <div>
          <label className="block mb-1 font-medium">Select Date:</label>
          <input
            type="date"
            value={bookingDate}
            onChange={(e) => setBookingDate(e.target.value)}
            className="w-full border p-2 rounded"
            required
            min={new Date().toISOString().split('T')[0]} // today's date onward
          />
        </div>

        <button
          type="submit"
          className="w-full bg-green-600 text-white py-2 px-4 rounded hover:bg-green-700"
        >
          Submit Booking
        </button>

        <button
          type="button"
          onClick={() => navigate('/bookings')}
          className="w-full mt-2 bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-600"
        >
          Back to Bookings
        </button>
      </form>
    </div>
  );
};

export default BookingPage;
