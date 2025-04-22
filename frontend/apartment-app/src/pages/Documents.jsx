import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import axios from 'axios';

const Documents = () => {
  const [documents, setDocuments] = useState([]);
  const token = localStorage.getItem('token');
  const [residentInfo, setResidentInfo] = useState(null);
  const config = {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };

  useEffect(() => {
    axios.get('http://localhost:9090/api/apartment-residents/current-user', config)
      .then(res => {
        setResidentInfo(res.data);
      })
      .catch(err => {
        console.error('Failed to fetch resident info:', err);
      });
  }, []);

  const fetchDocuments = async () => {
    try {
      const id = residentInfo.userId;
      const res = await axios.get(`http://localhost:9090/api/documents/user/${id}`, config);
      setDocuments(res.data);
    } catch (err) {
      console.error(err);
      alert('Failed to load documents');
    }
  };

  const handleDelete = async (id) => {
    const confirmDelete = window.confirm("Are you sure you want to delete this document?");
    if (!confirmDelete) return;

    try {
      await axios.delete(`http://localhost:9090/api/documents/${id}`, config);
      alert('Document deleted successfully');
      fetchDocuments(); // refresh list
    } catch (err) {
      console.error(err);
      alert('Failed to delete document');
    }
  };

  useEffect(() => {
    if (residentInfo) {
      fetchDocuments();
    }
  }, [residentInfo]);

  return (
    <div className="p-6">
      <h2 className="text-2xl font-bold mb-4">My Documents</h2>

      <div className="mb-6">
        <Link to="/documents/upload" className="bg-blue-600 text-white px-4 py-2 rounded">
          Upload New Document
        </Link>
      </div>

      <table className="w-full border border-collapse">
        <thead className="bg-gray-200">
          <tr>
            <th className="border p-2">Name</th>
            <th className="border p-2">Type</th>
            <th className="border p-2">Description</th>
            <th className="border p-2">Size (KB)</th>
            <th className="border p-2">Public</th>
            <th className="border p-2">Uploaded</th>
            <th className="border p-2">Actions</th>
          </tr>
        </thead>
        <tbody>
          {documents.map((doc) => (
            <tr key={doc.documentId}>
              <td className="border p-2">{doc.documentName}</td>
              <td className="border p-2">{doc.documentType}</td>
              <td className="border p-2">{doc.description}</td>
              <td className="border p-2">{(doc.fileSize / 1024).toFixed(2)}</td>
              <td className="border p-2">{doc.public ? 'Yes' : 'No'}</td>
              <td className="border p-2">{new Date(doc.uploadDate).toLocaleString()}</td>
              <td className="border p-2 space-x-2">
                <a href={doc.fileUrl} target="_blank" rel="noreferrer" className="text-blue-600 underline">View</a>
                <button onClick={() => handleDelete(doc.documentId)} className="text-red-600 underline">
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Documents;
