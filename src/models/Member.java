package models;

import java.time.LocalDate;

/**
 * Member class extending User
 * Represents a fitness member in the system
 */
public class Member extends User {
    private int memberId;
    private String membershipType; // BASIC, PREMIUM, VIP
    private LocalDate membershipStartDate;
    private LocalDate membershipExpiryDate;
    private double weight;
    private double height;
    private int age;
    private String fitnessGoal;
    private double monthlyFee;
    private boolean membershipActive;

    public Member(String email, String passwordHash, String firstName,
                  String lastName, String phoneNumber, String membershipType) {
        super(email, passwordHash, firstName, lastName, phoneNumber, "MEMBER");
        this.membershipType = membershipType;
        this.membershipStartDate = LocalDate.now();
        this.membershipExpiryDate = LocalDate.now().plusMonths(1);
        this.membershipActive = true;
        setMonthlyFeeByType(membershipType);
    }

    private void setMonthlyFeeByType(String type) {
        switch(type.toUpperCase()) {
            case "BASIC":
                this.monthlyFee = 29.99;
                break;
            case "PREMIUM":
                this.monthlyFee = 59.99;
                break;
            case "VIP":
                this.monthlyFee = 99.99;
                break;
            default:
                this.monthlyFee = 29.99;
        }
    }

    // Getters and Setters
    public int getMemberId() { return memberId; }
    public void setMemberId(int memberId) { this.memberId = memberId; }

    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { 
        this.membershipType = membershipType;
        setMonthlyFeeByType(membershipType);
    }

    public LocalDate getMembershipStartDate() { return membershipStartDate; }
    public void setMembershipStartDate(LocalDate membershipStartDate) { this.membershipStartDate = membershipStartDate; }

    public LocalDate getMembershipExpiryDate() { return membershipExpiryDate; }
    public void setMembershipExpiryDate(LocalDate membershipExpiryDate) { this.membershipExpiryDate = membershipExpiryDate; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getFitnessGoal() { return fitnessGoal; }
    public void setFitnessGoal(String fitnessGoal) { this.fitnessGoal = fitnessGoal; }

    public double getMonthlyFee() { return monthlyFee; }
    public void setMonthlyFee(double monthlyFee) { this.monthlyFee = monthlyFee; }

    public boolean isMembershipActive() { return membershipActive; }
    public void setMembershipActive(boolean membershipActive) { this.membershipActive = membershipActive; }

    public double calculateBMI() {
        if (height > 0) {
            return weight / (height * height);
        }
        return 0;
    }

    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) return "Underweight";
        if (bmi < 25) return "Normal weight";
        if (bmi < 30) return "Overweight";
        return "Obese";
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", membershipType='" + membershipType + '\'' +
                ", name='" + getFullName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", membershipActive=" + membershipActive +
                '}';
    }
}
