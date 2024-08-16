import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './AllReservations.css';


import 'react-datepicker/dist/react-datepicker.css';
import ReservationForm from './AllReservations.form';

axios.defaults.baseURL = 'http://localhost:8080';

const AllReservations = () => {
  const setReservations = useState([]);
  
  const [createdReservations, setCreatedReservations] = useState([]);

  useEffect(() => {
    const fetchReservations = async () => {
      try {
        const user = JSON.parse(localStorage.getItem('user'));
        const token = user?.accessToken;

        const config = {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        };

        const response = await axios.get('/locations', config);
        setReservations(response.data);
      } catch (error) {
        console.error('Error fetching locations:', error);
      }
    };

    fetchReservations();
  }); 

  useEffect(() => {
    const fetchHolidays = async () => {
      try {
        const user = JSON.parse(localStorage.getItem('user'));
        const token = user?.accessToken;

        const config = {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        };

        const response = await axios.get('/user-reservations', config);
        setCreatedReservations(response.data);
      } catch (error) {
        console.error('Error fetching reservations:', error);
      }
    };

    fetchHolidays();
  }, []); 


  return (
    <div className="container">
      <div className="content mt-4">
        <div className="row">
          <div className="col-md-8 mb-45">
            <div className="card">
              <div className="card-body">
                <h1>All Created Reservations</h1>
                <p>All created reservations details</p>
              </div>
            </div>
            {createdReservations.map(reservation => (
              <ReservationForm key={reservation.id} reservation={reservation} />
            ))}
          
          </div>
        </div>
      </div>
    </div>
  );
};

export default AllReservations;
