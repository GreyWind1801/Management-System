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

function App() {
  return (
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="apartments" element={<Apartments />} />
          <Route path="residents" element={<Residents />} />
          <Route path="payments" element={<Payments />} />
          <Route path="maintenance" element={<Maintenance />} />
          <Route path="visitors" element={<Visitors />} />
          <Route path="bookings" element={<Bookings />} />
          <Route path="documents" element={<Documents />} />
          <Route path="announcements" element={<Announcements />} />
        </Route>
      </Routes>
  );
}

export default App;
