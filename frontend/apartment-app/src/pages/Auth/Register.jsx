import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import { useNavigate } from 'react-router-dom';
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import authApi from '../../api/authApi';

const schema = yup.object().shape({
  name: yup.string().required('Name is required'),
  phone: yup.string().required("Phone number is required").matches(/^[0-9]{10}$/, 'Phone number must be 10 digits'),
  email: yup.string().email('Invalid email').required('Email is required'),
  password: yup.string().min(6, 'Minimum 6 characters').required('Password is required'),
  confirmPassword: yup.string().required('Please confirm password').oneOf([yup.ref('password')], 'Passwords must match'),
});

const Register = () => {
  const navigate = useNavigate();
  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema)
  });
  const [serverError, setServerError] = useState('');
  const [loading, setLoading] = useState(false);

  const onSubmit = async (data) => {
    setLoading(true);
    setServerError('');

    const { name, phone, email, password } = data;
    const user = { name, phone, email, password };

    try {
      await authApi.register(user);
      navigate('/login'); // Redirect to login page
    } catch (err) {
      console.log("REGISTER ERROR:", err);
      setServerError(err?.response?.data?.message || 'Registration failed. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-md mx-auto bg-white p-6 rounded shadow-md mt-8">
      <h2 className="text-2xl font-semibold mb-4 text-center">Register</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">

        <input type="text" placeholder="Full Name" {...register('name')} className="input" />
        {errors.name && <p className="text-red-500">{errors.name.message}</p>}

        <input type="text" placeholder="Phone Number" {...register('phone')} className="input" />
        {errors.phone && <p className="text-red-500">{errors.phone.message}</p>}

        <input type="email" placeholder="Email" {...register('email')} className="input" />
        {errors.email && <p className="text-red-500">{errors.email.message}</p>}

        <input type="password" placeholder="Password" {...register('password')} className="input" />
        {errors.password && <p className="text-red-500">{errors.password.message}</p>}

        <input type="password" placeholder="Confirm Password" {...register('confirmPassword')} className="input" />
        {errors.confirmPassword && <p className="text-red-500">{errors.confirmPassword.message}</p>}

        <button type="submit" disabled={loading} className="w-full bg-blue-600 hover:bg-blue-700 text-white py-2 rounded">
          {loading ? 'Registering...' : 'Register'}
        </button>

        {serverError && <p className="text-red-600 mt-2 text-center">{serverError}</p>}
      </form>
    </div>
  );
};

export default Register;
