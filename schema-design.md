## MySQL Database Design

### Table admin:
- id: INT, Primary Key, Auto Increment
- email: VARCHAR(30), UNIQUE, NOT NULL
- password: VARCHAR(20), NOT NULL

### Table patients:
- id: INT, Primary Key, Auto Increment
- email: VARCHAR(30), UNIQUE, NOT NULL
- password: VARCHAR(20), NOT NULL
- home_phone: VARCHAR(30)
- cell_phone: VARCHAR(30)
- first_name: VARCHAR(20), NOT NULL
- middle_name: VARCHAR(10)
- last_name: VARCHAR(20), NOT NULL
- gender: VARCHAR(6) ('Male', 'Female', 'Unknown')
- address: VARCHAR(50)


### Table doctors:
- id: INT, Primary Key, Auto Increment
- email: VARCHAR(30), UNIQUE, NOT NULL
- password: VARCHAR(20), NOT NULL
- first_name: VARCHAR(20), NOT NULL
- middle_name: VARCHAR(10)
- last_name: VARCHAR(20), NOT NULL
- gender: VARCHAR(6) ('Male', 'Female', 'Unknown')
- specialty: VARCHAR(20), NOT NULL
<!-- Short description of doctor's specialty & background -->
- bio: VARCHAR(200)


### Table appointments:
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key -> doctors(id)
- patient_id: INT, Foreign Key -> patients(id)
- appointment_time: DATETIME, NOT NULL
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)


