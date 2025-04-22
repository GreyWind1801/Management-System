import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const UploadDocument = () => {
  const navigate = useNavigate();
  const [apartmentDetails, setApartmentDetails] = useState(null);
  const [residentInfo, setResidentInfo] = useState(null);

  const [formData, setFormData] = useState({
    user: {userId: ''},
    apartment: {apartmentId: ''},
    documentName: '',
    description: '',
    documentType: 'LEASE_AGREEMENT',
    isPublic: false,
    file: null,
  });

  const token = localStorage.getItem('token');

  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current-user', config)
      .then(res => {
        setResidentInfo(res.data);
        setApartmentDetails(res.data.apartment);
      })
      .catch(err => {
        console.error('Failed to fetch resident info:', err);
      });
  }, []);

  const handleChange = (e) => {
    const { name, value, type, checked, files } = e.target;
    if (type === 'file') {
      setFormData({ ...formData, file: files[0] });
    } else if (type === 'checkbox') {
      setFormData({ ...formData, [name]: checked });
    } else {
      setFormData({ ...formData, [name]: value });
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!formData.file) {
      alert('Please select a file to upload.');
      return;
    }

    const payload = new FormData();
    payload.append('user', JSON.stringify(residentInfo));
    payload.append('apartment', JSON.stringify(apartmentDetails));
    payload.append('documentName', formData.documentName);
    payload.append('description', formData.description);
    payload.append('documentType', formData.documentType);
    payload.append('isPublic', formData.isPublic);
    payload.append('file', formData.file);

    try {
      const token = localStorage.getItem('token');
      const res = await axios.post(
        'http://localhost:9090/api/documents', payload, config );
      alert('Document uploaded successfully!');
      navigate('/documents');
    } catch (err) {
      console.error(err);
      alert('Failed to upload document');
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-8 p-6 border rounded shadow bg-white">
      <h2 className="text-2xl font-bold mb-6">Upload Document</h2>
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-semibold">Document Name</label>
          <input
            type="text"
            name="documentName"
            value={formData.documentName}
            onChange={handleChange}
            required
            className="w-full border p-2 rounded"
          />
        </div>

        <div>
          <label className="block font-semibold">Description</label>
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            className="w-full border p-2 rounded"
          />
        </div>

        <div>
          <label className="block font-semibold">Document Type</label>
          <select
            name="documentType"
            value={formData.documentType}
            onChange={handleChange}
            className="w-full border p-2 rounded"
          >
            <option value="LEASE_AGREEMENT">Lease Agreement</option>
            <option value="ID_PROOF">ID Proof</option>
            <option value="UTILITY_BILL">Utility Bill</option>
            <option value="MAINTENANCE_RECEIPT">Maintenance Receipt</option>
            <option value="OTHER">Other</option>
          </select>
        </div>

        <div className="flex items-center gap-2">
          <input
            type="checkbox"
            name="isPublic"
            checked={formData.isPublic}
            onChange={handleChange}
          />
          <label>Make Document Public</label>
        </div>

        <div>
          <label className="block font-semibold">Upload File</label>
          <input
            type="file"
            name="file"
            accept=".pdf,.jpg,.jpeg,.png"
            onChange={handleChange}
            required
            className="w-full"
          />
        </div>

        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Upload
        </button>
      </form>
    </div>
  );
};

export default UploadDocument;
