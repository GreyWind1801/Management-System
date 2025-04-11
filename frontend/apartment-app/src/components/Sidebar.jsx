import React from 'react';

const Sidebar = () => {
  return (
    <aside className="bg-gray-100 w-60 h-screen shadow-md p-4">
      <ul className="space-y-4">
        <li className="hover:text-blue-600 cursor-pointer">Dashboard</li>
        <li className="hover:text-blue-600 cursor-pointer">Residents</li>
        <li className="hover:text-blue-600 cursor-pointer">Payments</li>
        <li className="hover:text-blue-600 cursor-pointer">Documents</li>
        <li className="hover:text-blue-600 cursor-pointer">Announcements</li>
        <li className="hover:text-blue-600 cursor-pointer">Reports</li>
      </ul>
    </aside>
  );
};

export default Sidebar;
