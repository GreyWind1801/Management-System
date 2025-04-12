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
          <NavLink to="/residents" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Residents
          </NavLink>
        </li>
        <li>
          <NavLink to="/payments" className={({ isActive }) => `${linkClasses} ${isActive ? 'bg-blue-200 font-bold' : ''}`}>
            Payments
          </NavLink>
        </li>
      </ul>
    </aside>
  );
};

export default Sidebar;
