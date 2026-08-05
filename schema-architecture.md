## Architecture Summary
This project, a Smart Clinic Management System, builds a Spring Boot application that uses both MVC and REST controllers to support different types of clients.
The browser based Admin and Doctor dashboards are rendered with the Thymeleaf templates, while all the other modules consume the REST APIs.  The application persists data
in two different databases: JPA entities such as admin, patient, doctor, appointment in MySQL, and document objects such as prescriptions in MongoDB.
The controllers receive the requests and pass the data to the common service layer, which then propagate to the corresponding JPA repository.

## Step-by-step Flow of Data and Control
1. A user/client makes a request to one of the dashboards(AdminDashboard, DoctorDashboard) or a REST modules(Appointments, PatientDashboard, PatientRecord)
2. The request is appropriately handled by one of the Thymeleaf or REST controllers
3. The controller calls the service layer 
4. The service layer passes the input data to the appropriate repository(MySQL or MongoDB)
5. The repository interacts directly with the underlying database to carryout the data operation 
6. Data retrieved from MySQL or MongoDB are mapped into Java classes(aka model binding)
7. Bound models are returned to be used in the HTTP response:
   - In the MVC flow, the models are passed from the controller to the Thymeleaf templates for dynamic HTML generation
   - In the REST flow, the models(or their transformed DTOs) are serialized into JSONs and included in the HTTP response

