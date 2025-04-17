import React, { useState, useContext } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import authApi from '../../api/authApi';
import { AuthContext } from '../../context/AuthContext';
import { Link } from 'react-router-dom';

// ✅ Validation schema
const schema = yup.object().shape({
  email: yup.string().email('Invalid email').required('Email is required'),
  password: yup.string().required('Password is required'),
});

const Login = () => {
  const navigate = useNavigate();
  const {register,handleSubmit,formState: { errors },} = useForm({
    resolver: yupResolver(schema),
  });

  const [serverError, setServerError] = useState('');
  const [loading, setLoading] = useState(false);

  const { login } = useContext(AuthContext); // ✅ Access auth context
  

  const onSubmit = async (data) => {
    setLoading(true);
    setServerError('');

    try {
      const response = await authApi.login(data);// ✅ API call
      console.log("Login response:", response.data); 

      const { token, email, role } = response.data; // expected: { token, email, role }

      const user = { email, role };

      localStorage.setItem('token', token); // store token

      login( token, user ); // set context

      navigate('/'); // ✅ or your home route

    } catch (err) {
      console.error("Login error:", err);
      setServerError(
        err?.response?.data?.message || 'Login failed. Please try again.'
      );
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex flex-col items-center justify-center min-h-screen bg-gray-50 p-4">
      <div className="w-full max-w-md bg-white p-6 rounded-lg shadow-md">
        <h2 className="text-2xl font-semibold mb-6 text-center">Login</h2>

        <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block mb-1 font-medium">Email</label>
            <input
              type="email"
              {...register('email')}
              className="w-full border border-gray-300 px-3 py-2 rounded-md"
            />
            {errors.email && <p className="text-red-500 text-sm">{errors.email.message}</p>}
          </div>

          <div>
            <label className="block mb-1 font-medium">Password</label>
            <input
              type="password"
              {...register('password')}
              className="w-full border border-gray-300 px-3 py-2 rounded-md"
            />
            {errors.password && <p className="text-red-500 text-sm">{errors.password.message}</p>}
          </div>

          {serverError && <p className="text-red-600 text-sm text-center">{serverError}</p>}

          <button
            type="submit"
            className="w-full bg-blue-600 text-white py-2 rounded-md hover:bg-blue-700 transition"
            disabled={loading}
          >
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </form>

        <p className="mt-4 text-center text-sm text-gray-600">
          Don’t have an account?{' '}
          <Link to="/register" className="text-blue-600 hover:underline">Register here</Link>
        </p>
      </div>
    </div>
  );
};

export default Login;
