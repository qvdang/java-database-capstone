## MySQL Database Design

### Table admin:
- id: INT, Primary Key, Auto Increment
- email: VARCHAR(30), UNIQUE, NOT NULL
- username: VARCHAR(20), NOT NULL, UNIQUE
- password: VARCHAR(20), NOT NULL
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

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
- address: VARCHAR(255)
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP


### Table doctors:
- id: INT, Primary Key, Auto Increment
- email: VARCHAR(30), UNIQUE, NOT NULL
- password: VARCHAR(20), NOT NULL
- first_name: VARCHAR(20), NOT NULL
- middle_name: VARCHAR(10)
- last_name: VARCHAR(20), NOT NULL
- gender: VARCHAR(7) ('Male', 'Female', 'Unknown')
- specialty: VARCHAR(50), NOT NULL
<!-- Short description of doctor's specialty & background -->
- bio: VARCHAR(200)
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP


### Table doctor_availabilities:
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key -> doctors(id)
- day_of_week: TINYINT 0: Sunday -> 6: Saturday
- start_time: TIME NOT NULL
- end_time: TIME NOT NULL
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP


### Table appointments:
- id: INT, Primary Key, Auto Increment
- doctor_id: INT, Foreign Key -> doctors(id)
- patient_id: INT, Foreign Key -> patients(id)
- appointment_time: DATETIME, NOT NULL
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP


### Table clinics:
- id : INT, Primary Key, Auto Increment
- name: VARCHAR(30)
- address: VARCHAR(255)
- phone: VARCHAR(30)
- created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP

### Scenarios
- If a patient is deleted, all the associated appointments must be removed as well
- A patient's appointment history should be retained for 5 years
- A prescription should be associated with an appointment


## MongoDB Collection Design

### Collection: prescriptions
```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
    "phone": "(123) 456-7890"
  }
}

db.createCollection("prescriptions", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["patientName", "appointmentId", "medication", "dosage", "doctorNotes", "refillCount"],
      properties: {
        refillCount: {
          bsonType: "int",
          minimum: 0,
          maximum: 5
        },
        pharmacy: {
          bsonType: "object",
          properties: {
            name: { bsonType: "string" },
            location: { bsonType: "string" },
            phone: { bsonType: "string" }
          }
        }
      }
    }
  },
  validationAction: "error"  // reject invalid documents entirely
})


### Collection: feedbacks
Optional feedback from a patient after their appointment

```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "doctorName": "Vincent Rentz",
  "feedbackText": "Dr. Rentz was attentive and fully answered each of my questions...",
  "rating": 5,
  "recommended": "yes"
}

db.createCollection("feedbacks", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["patientName", "appointmentId", "doctorName", "title", "feedbackText", "rating", "recommended"],
      properties: {
        rating: {
          bsonType: "int",
          minimum: 1,
          maximum: 5
        },
        recommended: {
          bsonType: "string",
          enum: ["yes", "no"],
          description: "Only allowed values are yes, no"
        }
      }
    }
  },
  validationAction: "error"  // reject invalid documents entirely
})


### Collection: logs
```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "doctorName": "Vincent Rentz",
  "checkinTime": Timestamp()
}

db.createCollection("logs", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["patientName", "appointmentId", "doctorName", "checkinTime"]
    }
  },
  validationAction: "error"  // reject invalid documents entirely
})


### Collection: messages
```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "doctorName": "Vincent Rentz",
  [
    {"fromPatient": "text msg to the doctor", "timestamp": Timestamp()},
    {"fromDoctor" : "text msg to the patient", "timestamp": Timestamp()},
    ......
  ]
}

db.createCollection("messages", {
  validator: {
    $jsonSchema: {
      bsonType: "object",
      required: ["patientName", "appointmentId", "doctorName"],
    }
  },
  validationAction: "error"  // reject invalid documents entirely
})

