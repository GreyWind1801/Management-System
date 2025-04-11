import React from 'react';

const Navbar = () => {
  return (
    <nav className="bg-blue-600 text-white px-4 py-3 shadow-md flex justify-between items-center">
      <h1 className="text-xl font-semibold">Apartment Manager</h1>
      <div>
        <button className="bg-blue-800 px-3 py-1 rounded hover:bg-blue-700">Logout</button>
      </div>
    </nav>
  );
};

export default Navbar;
