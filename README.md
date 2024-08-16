Travel agency rest api with authorization and authentication. Here are 2wo florers with reactjs project - my rest client and backend - java spring boot project. 

Project start instructions:
1. First start backend in IntelliJ IDEA


2. Make sure the roles'ROLE_USER' and 'ROLE_ADMIN' are initialize in SQL springReactAwtSchema.
   if there is empty, use this SQL script for initialize roles: {
   
    USE springreactawtschema;
    INSERT INTO roles (name) VALUES ('ROLE_USER');
     INSERT INTO roles (name) VALUES ('ROLE_MODERATOR');
      INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
   
}

4. Frontend start:
   3.1 Go into react-jwt-auth-master and use npm install and npm start for install packages and start the Rest Client application
   3.2 Register your first user(for example admin)
   3.3 Login and make sure the login is completely.
   3.4 Go in sql and use this script for Authorize your youser with admin access:
   
   {
   
   INSERT INTO springreactawtschema.user_roles (user_id, role_id) VALUES (1, 2);
   
   }
   (Check user_id and role_id are correctly).
   
   3.5 Logout from your account and login again and you will get an admin navigation bar.
   
6. Test Software with Create location,ather that holliday and reservation.
