// src/api/authApi.js
import axios from './axios';

const authApi = {
  register: (data) => axios.post('/api/auth/register', data),
  login: (data) => axios.post('/api/auth/login', data),
};

export default authApi;
