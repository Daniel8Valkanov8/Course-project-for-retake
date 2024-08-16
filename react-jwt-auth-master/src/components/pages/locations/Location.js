import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Locations.css';
import LocationForm from './LocationForm';

axios.defaults.baseURL = 'http://localhost:8080';

const Locations = () => {
  const [formData, setFormData] = useState({
    street: '',
    number: '',
    city: '',
    country: ''
  });
  const [locations, setLocations] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const fetchLocations = async () => {
      try {
        const user = JSON.parse(localStorage.getItem('user'));
        const token = user?.accessToken;

        const config = {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        };

        const response = await axios.get('/locations', config);

        setLocations(response.data);
      } catch (error) {
        console.error('Error fetching locations:', error);
      }
    };

    fetchLocations();
  }, []);

  const validateForm = () => {
    const { street, number, city, country } = formData;
    const newErrors = {};

    if (!street || street.length < 3) {
      newErrors.street = 'Street must contain at least 3 characters.';
    }

    if (!number || isNaN(number)) {
      newErrors.number = 'Number must be a valid number.';
    }

    if (!city || city.length < 3) {
      newErrors.city = 'City must contain at least 3 characters.';
    }

    if (!country || country.length < 3) {
      newErrors.country = 'Country must contain at least 3 characters.';
    }

    setErrors(newErrors);

    return Object.keys(newErrors).length === 0;
  };

  const createLocation = async (event) => {
    event.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const token = user?.accessToken;

      const config = {
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      };

      const response = await axios.post('/locations', formData, config);

      if (response.status === 201) {
        console.log('Location created successfully:', response.data);
        setLocations([response.data, ...locations]);
        setFormData({
          street: '',
          number: '',
          city: '',
          country: ''
        });
        setErrors({});
      }
    } catch (error) {
      console.error('Error creating location:', error);
    }
  };

  const deleteLocation = async (locationId) => {
    try {
      const user = JSON.parse(localStorage.getItem('user'));
      const token = user?.accessToken;

      const config = {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };

      const response = await axios.delete(`/locations/${locationId}`, config);

      if (response.status === 200) {
        console.log('Location deleted successfully');
        setLocations(locations.filter(location => location.id !== locationId));
      }
    } catch (error) {
      console.error('Error deleting location:', error);
    }
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  return (
    <div className="container">
      <div className="content mt-4">
        <div className="row">
          <div className="col-md-8 mb-4">
            <div className="card">
              <div className="card-body">
                <h1>Locations</h1>
                <p>All available locations for holidays.</p>
              </div>
            </div>
            {locations.map((location) => (
              <LocationForm 
                key={location.id} 
                location={location} 
                onDelete={deleteLocation}  // Pass deleteLocation as a prop
              />
            ))}
          </div>
          <div className="col-md-4 mb-4">
            <div className="card">
              <div className="card-body">
                <h1>Create A New Location</h1>
                <form onSubmit={createLocation} className="mt-4">
                  <div className="form-group">
                    <label htmlFor="street" className="form-label">Street</label>
                    <input
                      type="text"
                      className={`form-control ${errors.street ? 'is-invalid' : ''}`}
                      id="street"
                      placeholder="Street"
                      value={formData.street}
                      onChange={handleInputChange}
                      required
                    />
                    {errors.street && <div className="invalid-feedback">{errors.street}</div>}
                  </div>
                  <div className="form-group">
                    <label htmlFor="number" className="form-label">Number</label>
                    <input
                      type="text"
                      className={`form-control ${errors.number ? 'is-invalid' : ''}`}
                      id="number"
                      placeholder="Number"
                      value={formData.number}
                      onChange={handleInputChange}
                      required
                    />
                    {errors.number && <div className="invalid-feedback">{errors.number}</div>}
                  </div>
                  <div className="form-group">
                    <label htmlFor="city" className="form-label">City</label>
                    <input
                      type="text"
                      className={`form-control ${errors.city ? 'is-invalid' : ''}`}
                      id="city"
                      placeholder="City"
                      value={formData.city}
                      onChange={handleInputChange}
                      required
                    />
                    {errors.city && <div className="invalid-feedback">{errors.city}</div>}
                  </div>
                  <div className="form-group">
                    <label htmlFor="country" className="form-label">Country</label>
                    <input
                      type="text"
                      className={`form-control ${errors.country ? 'is-invalid' : ''}`}
                      id="country"
                      placeholder="Country"
                      value={formData.country}
                      onChange={handleInputChange}
                      required
                    />
                    {errors.country && <div className="invalid-feedback">{errors.country}</div>}
                  </div>
                  <button type="submit" className="btn btn-outline-primary mt-3">
                    Create Location
                  </button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Locations;
