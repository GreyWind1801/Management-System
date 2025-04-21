// src/api/apartmentResidentsApi.js
import axios from './axios'; // Ensure axiosConfig is set with baseURL + token header

const BASE_URL = '/api/apartment-residents';

const apartmentResidentsApi = {
  getAllByApartmentId: (apartmentId) => axios.get(`${BASE_URL}/apartment/${apartmentId}`),
  getByUserId: (userId) => axios.get(`${BASE_URL}/user/${userId}`),
  getResidentById: (id) => axios.get(`${BASE_URL}/${id}`),
  assignResident: (data) => axios.post(`${BASE_URL}/assign`, data),
  removeResident: (residentId) => axios.put(`${BASE_URL}/remove/${residentId}`),
  updateRole: (id, role) => axios.put(`${BASE_URL}/${id}/update-role`, null, {
    params: { role }
  }),
  getCurrentResidents: (apartmentId) => axios.get(`${BASE_URL}/current/apartment/${apartmentId}`),
  getHistoryByResidentId: (residentId) => axios.get(`${BASE_URL}/history/resident/${residentId}`),
  getHistoryByApartmentId: (apartmentId) => axios.get(`${BASE_URL}/history/apartment/${apartmentId}`),
};

export default apartmentResidentsApi;
