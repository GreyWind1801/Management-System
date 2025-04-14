import React from 'react';
import { Routes, Route } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';

import Dashboard from './pages/Dashboard';
import Apartments from './pages/Apartments';
import Residents from './pages/Residents';
import Payments from './pages/Payments';
import Maintenance from './pages/Maintenance';
import Visitors from './pages/Visitors';
import Bookings from './pages/Bookings';
import Documents from './pages/Documents';
import Announcements from './pages/Announcements';
import Login from './pages/Auth/Login';
import Register from './pages/Auth/Register';

import PrivateRoute from './routes/PrivateRoute';

function App() {
  return (
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="apartments" element={<PrivateRoute><Apartments /></PrivateRoute>} />
          <Route path="residents" element={<PrivateRoute><Residents /></PrivateRoute>} />
          <Route path="payments" element={<PrivateRoute><Payments /></PrivateRoute>} />
          <Route path="maintenance" element={<PrivateRoute><Maintenance /></PrivateRoute>} />
          <Route path="visitors" element={<PrivateRoute><Visitors /></PrivateRoute>} />
          <Route path="bookings" element={<PrivateRoute><Bookings /></PrivateRoute>} />
          <Route path="documents" element={<PrivateRoute><Documents /></PrivateRoute>} />
          <Route path="announcements" element={<PrivateRoute><Announcements /></PrivateRoute>} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Route>
      </Routes>
  );
}

export default App;
