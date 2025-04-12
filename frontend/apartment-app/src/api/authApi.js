// src/api/authApi.js
import axios from './axios';

const authApi = {
  register: (data) => axios.post('/auth/register', data),
  login: (data) => axios.post('/auth/login', data),
};

export default authApi;
