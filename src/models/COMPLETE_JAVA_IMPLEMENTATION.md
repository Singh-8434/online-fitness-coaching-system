# Complete Java Implementation - Online Fitness System

## Project Structure

```
src/
├── models/
│   ├── User.java (✓ Created)
│   ├── Member.java (✓ Created)
│   ├── Trainer.java
│   ├── FitnessClass.java
│   ├── Booking.java
│   └── Payment.java
├── dao/
│   ├── UserDAO.java (Interface)
│   ├── UserDAOImpl.java (Implementation)
│   ├── MemberDAO.java
│   ├── MemberDAOImpl.java
│   ├── TrainerDAO.java
│   └── TrainerDAOImpl.java
├── services/
│   ├── UserService.java
│   ├── MemberService.java
│   ├── TrainerService.java
│   ├── ClassService.java
│   ├── BookingService.java
│   └── AuthenticationService.java
├── controllers/
│   ├── UserController.java
│   ├── MemberController.java
│   ├── TrainerController.java
│   ├── ClassController.java
│   └── PaymentController.java
├── utilities/
│   ├── DatabaseConnection.java
│   ├── PasswordEncryption.java
│   ├── EmailValidator.java
│   └── Constants.java
└── main/
    └── FitnessSystemMain.java
```

## Trainer Model Class

```java
package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Trainer class extending User
 * Represents a fitness trainer in the system
 */
public class Trainer extends User {
    private int trainerId;
    private String specialization; // Cardio, Strength, Yoga, etc.
    private int yearsOfExperience;
    private String certification;
    private List<String> certifications;
    private double hourlyRate;
    private int maxClientsPerDay;
    private boolean isAvailable;
    private double rating;

    public Trainer(String email, String passwordHash, String firstName,
                   String lastName, String phoneNumber, String specialization) {
        super(email, passwordHash, firstName, lastName, phoneNumber, "TRAINER");
        this.specialization = specialization;
        this.certifications = new ArrayList<>();
        this.hourlyRate = 50.0;
        this.maxClientsPerDay = 10;
        this.isAvailable = true;
        this.rating = 0.0;
    }

    // Getters and Setters
    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public String getCertification() { return certification; }
    public void setCertification(String certification) { this.certification = certification; }

    public List<String> getCertifications() { return certifications; }
    public void addCertification(String cert) { this.certifications.add(cert); }

    public double getHourlyRate() { return hourlyRate; }
    public void setHourlyRate(double hourlyRate) { this.hourlyRate = hourlyRate; }

    public int getMaxClientsPerDay() { return maxClientsPerDay; }
    public void setMaxClientsPerDay(int maxClientsPerDay) { this.maxClientsPerDay = maxClientsPerDay; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = Math.min(5.0, Math.max(0.0, rating)); }

    public void updateRating(double newRating) {
        this.rating = (this.rating + newRating) / 2;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "trainerId=" + trainerId +
                ", name='" + getFullName() + '\'' +
                ", specialization='" + specialization + '\'' +
                ", rating=" + rating +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
```

## FitnessClass Model

```java
package models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * FitnessClass represents a group fitness class
 */
public class FitnessClass {
    private int classId;
    private String className;
    private int trainerId;
    private String trainerName;
    private LocalDate classDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String classType; // Yoga, Cardio, Strength, Pilates
    private int capacity;
    private int enrolledMembers;
    private double classPrice;
    private String location;
    private String level; // BEGINNER, INTERMEDIATE, ADVANCED
    private boolean isActive;

    public FitnessClass(String className, int trainerId, LocalDate classDate,
                       LocalTime startTime, LocalTime endTime, String classType) {
        this.className = className;
        this.trainerId = trainerId;
        this.classDate = classDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.classType = classType;
        this.capacity = 20;
        this.enrolledMembers = 0;
        this.classPrice = 15.0;
        this.isActive = true;
    }

    // Getters and Setters
    public int getClassId() { return classId; }
    public void setClassId(int classId) { this.classId = classId; }

    public String getClassName() { return className; }
    public void setClassName(String className) { this.className = className; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }

    public String getTrainerName() { return trainerName; }
    public void setTrainerName(String trainerName) { this.trainerName = trainerName; }

    public LocalDate getClassDate() { return classDate; }
    public void setClassDate(LocalDate classDate) { this.classDate = classDate; }

    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }

    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public int getEnrolledMembers() { return enrolledMembers; }
    public void setEnrolledMembers(int enrolledMembers) { this.enrolledMembers = enrolledMembers; }

    public int getAvailableSpots() {
        return capacity - enrolledMembers;
    }

    public boolean enrollMember() {
        if (getAvailableSpots() > 0 && isActive) {
            this.enrolledMembers++;
            return true;
        }
        return false;
    }

    public void removeMember() {
        if (enrolledMembers > 0) {
            this.enrolledMembers--;
        }
    }

    public boolean isFull() {
        return enrolledMembers >= capacity;
    }

    public double getClassPrice() { return classPrice; }
    public void setClassPrice(double classPrice) { this.classPrice = classPrice; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    @Override
    public String toString() {
        return "FitnessClass{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", classType='" + classType + '\'' +
                ", trainerName='" + trainerName + '\'' +
                ", enrolledMembers=" + enrolledMembers + '/' + capacity +
                '}';
    }
}
```

## Note: Additional Classes

This file contains model and core classes. Additional classes needed:

- **Booking.java** - For class bookings
- **Payment.java** - For payment transactions
- **UserDAO & Implementation** - Database operations
- **Service Classes** - Business logic
- **Controller Classes** - Request handling
- **Utility Classes** - Helper functions

See separate commits for each category.

Presented by: Ujjwal Raj
