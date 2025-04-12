import React from 'react';
import { Routes, Route } from 'react-router-dom';

import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';

import Dashboard from './pages/Dashboard';
import Residents from './pages/Residents';
import Payments from './pages/Payments';

function App() {
  return (
    <div className="flex flex-col h-screen">
      <Navbar />
      <div className="flex flex-1">
        <Sidebar />
        <main className="flex-1 p-4 overflow-auto">
        <Routes>
            <Route path="/" element={<Dashboard />} />
            <Route path="/residents" element={<Residents />} />
            <Route path="/payments" element={<Payments />} />
        </Routes>
        </main>
      </div>
    </div>
  );
}

export default App;
