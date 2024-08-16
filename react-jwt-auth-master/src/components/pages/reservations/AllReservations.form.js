const ReservationForm = ({ reservation }) => {
    return (
      <div className="card mt-4" style={{ borderRadius: '15px' }}>
        <div className="card-body">
          <h3>Reservation created ID: {reservation.id}</h3>
          <p><strong>Contact Name: </strong> {reservation.phoneNumber}</p>
          <p><strong>Phone Number: </strong> {reservation.phoneNumber}</p>
          <p><strong>Start Date:</strong> 
          {new Date(reservation.holiday.startDate).toLocaleDateString()}</p>
        </div>
      </div>
    );
  };
  
  export default ReservationForm;
  