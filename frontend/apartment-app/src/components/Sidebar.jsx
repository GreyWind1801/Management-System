import React from 'react';
import { NavLink } from 'react-router-dom';

const Sidebar = () => {
  const linkClasses = 'block py-2 px-3 rounded hover:bg-blue-100';

  return (
    <aside className="bg-gray-100 w-60 h-screen shadow-md p-4">
      <ul className="space-y-4">
        <li>
          <NavLink to="/" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Dashboard
          </NavLink>
        </li>
        <li>
          <NavLink to="/apartments" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Apartments
          </NavLink>
        </li>
        <li>
          <NavLink to="/residents" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Residents
          </NavLink>
        </li>
        <li>
          <NavLink to="/payments" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Payments
          </NavLink>
        </li>
        <li>
          <NavLink to="/maintenance" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Maintenance
          </NavLink>
        </li>
        <li>
          <NavLink to="/visitors" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Visitors
          </NavLink>
        </li>
        <li>
          <NavLink to="/bookings" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Bookings
          </NavLink>
        </li>
        <li>
          <NavLink to="/documents" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Documents
          </NavLink>
        </li>
        <li>
          <NavLink to="/announcements" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Announcements
          </NavLink>
        </li>
      </ul>
    </aside>
  );
};

export default Sidebar;
