import React from 'react';
import { Routes, Route } from 'react-router-dom';
import MainLayout from './layouts/MainLayout';

import Dashboard from './pages/Dashboard';
import Apartments from './pages/Apartments';
import Residents from './pages/Residents';
import Payments from './pages/Payments';
import Maintenance from './pages/Maintenance';
import VisitorsLog from './pages/VisitorsLog';
import Bookings from './pages/Bookings';
import Documents from './pages/Documents';
import Announcements from './pages/Announcements';
import Login from './pages/Auth/Login';
import Register from './pages/Auth/Register';
import AssignResident from './pages/AssignResident';
import ViewResidents from './pages/ViewResidents';
import UpdateRole from './pages/UpdateRole';
import ViewResidentHistory from './pages/ViewResidentHistory';
import AddAnnouncement from './pages/AddAnnouncement';
import LogVisitor from './pages/LogVisitor';
import CreateRequest from './pages/CreateRequest';
import UpdateRequest from './pages/UpdateRequest';
import UploadDocument from './pages/UploadDocument';
import BookingPage from './pages/BookingPage';

import PrivateRoute from './routes/PrivateRoute';



function App() {
  return (
      <Routes>
        <Route path="/" element={<MainLayout />}>
          <Route index element={<Dashboard />} />
          <Route path="apartments" element={<PrivateRoute><Apartments /></PrivateRoute>} />
          <Route path="residents" element={<PrivateRoute><Residents /></PrivateRoute>} />
          <Route path="/residents/assign" element={<PrivateRoute><AssignResident /></PrivateRoute>} />
          <Route path="/residents/view" element={<PrivateRoute><ViewResidents /></PrivateRoute>} />
          <Route path="/residents/update-role" element={<PrivateRoute><UpdateRole /></PrivateRoute>} />
          <Route path="/residents/history" element={<PrivateRoute><ViewResidentHistory /></PrivateRoute>} />
          <Route path="payments" element={<PrivateRoute><Payments /></PrivateRoute>} />
          <Route path="maintenance" element={<PrivateRoute><Maintenance /></PrivateRoute>} />
          <Route path="/maintenance/create" element={<PrivateRoute><CreateRequest /></PrivateRoute>} />
          <Route path="/maintenance/update/:requestId" element={<PrivateRoute><UpdateRequest /></PrivateRoute>} />
          <Route path="/visitors-log" element={<PrivateRoute><VisitorsLog /></PrivateRoute>} />
          <Route path="/log-visitor" element={<PrivateRoute><LogVisitor /></PrivateRoute>} />
          <Route path="bookings" element={<PrivateRoute><Bookings /></PrivateRoute>} />
          <Route path="book-facility" element={<PrivateRoute><BookingPage /></PrivateRoute>} />
          <Route path="documents" element={<PrivateRoute><Documents /></PrivateRoute>} />
          <Route path="/documents/upload" element={<PrivateRoute><UploadDocument /></PrivateRoute>} />
          <Route path="announcements" element={<PrivateRoute><Announcements /></PrivateRoute>} />
          <Route path="/add-announcement" element={<PrivateRoute><AddAnnouncement /></PrivateRoute>} />
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
        </Route>
      </Routes>
  );
}

export default App;
