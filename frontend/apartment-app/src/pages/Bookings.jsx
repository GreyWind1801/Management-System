import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const Bookings = () => {
  const [bookings, setBookings] = useState([]);
  const [loading, setLoading] = useState(true);
  const [residentInfo, setResidentInfo] = useState(null);
  const navigate = useNavigate();
  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

// Fetch current apartment details of logged-in user
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
    if (!residentInfo) return;
    // Fetch the user's bookings
    const id = residentInfo.userId;
    axios.get(`http://localhost:9090/api/resource-bookings/user/${id}`, config)
    .then(res => {
      setBookings(Array.isArray(res.data) ? res.data : []);
      setLoading(false);
    })
    .catch(err => {
      console.error('Failed to fetch bookings:', err);
      setLoading(false);
    });
  }, [residentInfo]);

  const handleCancelBooking = (bookingId) => {
    if (window.confirm('Are you sure you want to cancel this booking?')) {
      axios.delete(`http://localhost:9090/api/resource-bookings/${bookingId}`, config)
      .then(() => {
        setBookings(bookings.filter(booking => booking.bookingId !== bookingId));
        alert('Booking canceled successfully!');
      })
      .catch(err => {
        console.error('Failed to cancel booking:', err);
        alert('Failed to cancel booking');
      });
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-8 p-6 border rounded shadow bg-white">
      <h2 className="text-2xl font-bold mb-6">Your Facility Bookings</h2>
      {loading ? (
        <p>Loading...</p>
      ) : (
        <div>
          {bookings.length > 0 ? (
            <ul>
              {bookings.map((booking) => (
                <li key={booking.id} className="mb-4">
                  <p><strong>Facility:</strong> {booking.facilityName}</p>
                  <p><strong>Date:</strong> {booking.bookingDate}</p>
                  <p><strong>Status:</strong> {booking.status}</p>
                  <button 
                    onClick={() => handleCancelBooking(booking.bookingId)} 
                    className="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600"
                  >
                    Cancel Booking
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No bookings found</p>
          )}
          <button 
            onClick={() => navigate('/book-facility')} 
            className="mt-6 bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
          >
            Book a Facility
          </button>
        </div>
      )}
    </div>
  );
};

export default Bookings;
