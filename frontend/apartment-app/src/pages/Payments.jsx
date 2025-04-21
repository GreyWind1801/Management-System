import React, { useEffect, useState } from 'react';
import axios from 'axios';

const Payments = () => {
  const [apartmentDetails, setApartmentDetails] = useState(null);
  const [amount, setAmount] = useState('');
  const [paymentType, setPaymentType] = useState('ONLINE');
  const [residentInfo, setResidentInfo] = useState(null);

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
        setApartmentDetails(res.data.apartment);
        setAmount(res.data.defaultRentAmount || ''); // Optional default amount
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
        amount,
        status: "completed",
        type: paymentType,
      };

      await axios.post('http://localhost:9090/api/payments', payload, config);
      alert('Payment successful!');
      setAmount('');
    } catch (error) {
      console.error('Payment failed:', error);
      alert('Payment failed. Try again.');
    }
  };

  return (
    <div className="p-6">
      <h2 className="text-2xl font-semibold mb-4">Pay Rent</h2>

      {apartmentDetails ? (
        <form onSubmit={handleSubmit} className="space-y-4 bg-white p-6 rounded shadow w-full max-w-md">
          <div>
            <p><strong>Apartment No:</strong> {apartmentDetails.flatNumber} </p>
            <p><strong>Block Name:</strong> {apartmentDetails.block}</p>
          </div>

          <input
            type="number"
            placeholder="Enter Amount"
            className="w-full border px-3 py-2 rounded"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            required
          />

          <select
            className="w-full border px-3 py-2 rounded"
            value={paymentType}
            onChange={(e) => setPaymentType(e.target.value)}
          >
            <option value="RENT">Rent</option>
            <option value="MAINTENANCE">Maintenance</option>
            <option value="BOOKING">Booking</option>
            <option value="MMC">MMC</option>
          </select>

          <button type="submit" className="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">
            Pay Now
          </button>
        </form>
      ) : (
        <p>Loading your apartment details...</p>
      )}
    </div>
  );
};

export default Payments;
