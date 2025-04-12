import React from 'react';
import { useNavigate } from 'react-router-dom';

const Navbar = () => {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  const handleLogout = () => {
    localStorage.removeItem("token"); // Clear the login token
    navigate("/login"); // Redirect to login page
  };

  const handleLogin = () => {
    navigate("/login"); // Navigate to login page
  };

  return (
    <nav className="bg-blue-600 text-white px-4 py-3 shadow-md flex justify-between items-center">
      <h1 className="text-xl font-semibold">Apartment Manager</h1>
      <div>
        {token ? (
          <button
            onClick={handleLogout}
            className="bg-blue-800 px-3 py-1 rounded hover:bg-blue-700"
          >
            Logout
          </button>
        ) : (
          <button
            onClick={handleLogin}
            className="bg-blue-800 px-3 py-1 rounded hover:bg-blue-700"
          >
            Login
          </button>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
