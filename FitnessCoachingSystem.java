import java.util.*;
import java.time.LocalDate;

// Client class to represent a fitness client
class Client {
    private String clientId;
    private String name;
    private int age;
    private double weight;
    private double height;
    private String fitnessGoal;
    private LocalDate joinDate;
    
    public Client(String clientId, String name, int age, double weight, double height, String fitnessGoal) {
        this.clientId = clientId;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.fitnessGoal = fitnessGoal;
        this.joinDate = LocalDate.now();
    }
    
    public String getClientId() { return clientId; }
    public String getName() { return name; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    
    @Override
    public String toString() {
        return String.format("Client[ID: %s, Name: %s, Age: %d, Weight: %.1fkg, Height: %.1fcm, Goal: %s]",
                clientId, name, age, weight, height, fitnessGoal);
    }
}

// WorkoutPlan class
class WorkoutPlan {
    private String planId;
    private String clientId;
    private String planName;
    private List<String> exercises;
    private int durationWeeks;
    
    public WorkoutPlan(String planId, String clientId, String planName, int durationWeeks) {
        this.planId = planId;
        this.clientId = clientId;
        this.planName = planName;
        this.durationWeeks = durationWeeks;
        this.exercises = new ArrayList<>();
    }
    
    public void addExercise(String exercise) {
        exercises.add(exercise);
    }
    
    public String getClientId() { return clientId; }
    
    @Override
    public String toString() {
        return String.format("WorkoutPlan[ID: %s, Client: %s, Plan: %s, Duration: %d weeks, Exercises: %d]",
                planId, clientId, planName, durationWeeks, exercises.size());
    }
}

// DietPlan class
class DietPlan {
    private String planId;
    private String clientId;
    private int dailyCalories;
    private Map<String, String> mealPlan;
    
    public DietPlan(String planId, String clientId, int dailyCalories) {
        this.planId = planId;
        this.clientId = clientId;
        this.dailyCalories = dailyCalories;
        this.mealPlan = new HashMap<>();
    }
    
    public void addMeal(String mealTime, String mealDescription) {
        mealPlan.put(mealTime, mealDescription);
    }
    
    public String getClientId() { return clientId; }
    
    @Override
    public String toString() {
        return String.format("DietPlan[ID: %s, Client: %s, Calories: %d, Meals: %d]",
                planId, clientId, dailyCalories, mealPlan.size());
    }
}

// Main System class
public class FitnessCoachingSystem {
    private Map<String, Client> clients;
    private Map<String, WorkoutPlan> workoutPlans;
    private Map<String, DietPlan> dietPlans;
    private Scanner scanner;
    
    public FitnessCoachingSystem() {
        clients = new HashMap<>();
        workoutPlans = new HashMap<>();
        dietPlans = new HashMap<>();
        scanner = new Scanner(System.in);
    }
    
    public void addClient(Client client) {
        clients.put(client.getClientId(), client);
        System.out.println("✓ Client added successfully!");
    }
    
    public void addWorkoutPlan(WorkoutPlan plan) {
        if (clients.containsKey(plan.getClientId())) {
            workoutPlans.put(plan.getClientId(), plan);
            System.out.println("✓ Workout plan added successfully!");
        } else {
            System.out.println("✗ Client not found!");
        }
    }
    
    public void addDietPlan(DietPlan plan) {
        if (clients.containsKey(plan.getClientId())) {
            dietPlans.put(plan.getClientId(), plan);
            System.out.println("✓ Diet plan added successfully!");
        } else {
            System.out.println("✗ Client not found!");
        }
    }
    
    public void displayAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients registered.");
            return;
        }
        System.out.println("\n=== All Clients ===");
        for (Client client : clients.values()) {
            System.out.println(client);
        }
    }
    
    public void displayClientDetails(String clientId) {
        Client client = clients.get(clientId);
        if (client == null) {
            System.out.println("✗ Client not found!");
            return;
        }
        System.out.println("\n=== Client Details ===");
        System.out.println(client);
        
        WorkoutPlan workout = workoutPlans.get(clientId);
        if (workout != null) {
            System.out.println(workout);
        }
        
        DietPlan diet = dietPlans.get(clientId);
        if (diet != null) {
            System.out.println(diet);
        }
    }
    
    public void menu() {
        while (true) {
            System.out.println("\n=== Online Fitness Coaching System ===");
            System.out.println("1. Add New Client");
            System.out.println("2. Add Workout Plan");
            System.out.println("3. Add Diet Plan");
            System.out.println("4. Display All Clients");
            System.out.println("5. Display Client Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    addClientMenu();
                    break;
                case 2:
                    addWorkoutPlanMenu();
                    break;
                case 3:
                    addDietPlanMenu();
                    break;
                case 4:
                    displayAllClients();
                    break;
                case 5:
                    displayClientDetailsMenu();
                    break;
                case 6:
                    System.out.println("Thank you for using Fitness Coaching System!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
    
    private void addClientMenu() {
        System.out.print("Enter Client ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Weight (kg): ");
        double weight = scanner.nextDouble();
        System.out.print("Enter Height (cm): ");
        double height = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter Fitness Goal: ");
        String goal = scanner.nextLine();
        
        Client client = new Client(id, name, age, weight, height, goal);
        addClient(client);
    }
    
    private void addWorkoutPlanMenu() {
        System.out.print("Enter Client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter Plan ID: ");
        String planId = scanner.nextLine();
        System.out.print("Enter Plan Name: ");
        String planName = scanner.nextLine();
        System.out.print("Enter Duration (weeks): ");
        int weeks = scanner.nextInt();
        scanner.nextLine();
        
        WorkoutPlan plan = new WorkoutPlan(planId, clientId, planName, weeks);
        
        System.out.print("How many exercises to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < count; i++) {
            System.out.print("Enter exercise " + (i + 1) + ": ");
            plan.addExercise(scanner.nextLine());
        }
        
        addWorkoutPlan(plan);
    }
    
    private void addDietPlanMenu() {
        System.out.print("Enter Client ID: ");
        String clientId = scanner.nextLine();
        System.out.print("Enter Plan ID: ");
        String planId = scanner.nextLine();
        System.out.print("Enter Daily Calories: ");
        int calories = scanner.nextInt();
        scanner.nextLine();
        
        DietPlan plan = new DietPlan(planId, clientId, calories);
        
        System.out.print("How many meals to add? ");
        int count = scanner.nextInt();
        scanner.nextLine();
        
        for (int i = 0; i < count; i++) {
            System.out.print("Enter meal time (e.g., Breakfast): ");
            String time = scanner.nextLine();
            System.out.print("Enter meal description: ");
            String desc = scanner.nextLine();
            plan.addMeal(time, desc);
        }
        
        addDietPlan(plan);
    }
    
    private void displayClientDetailsMenu() {
        System.out.print("Enter Client ID: ");
        String clientId = scanner.nextLine();
        displayClientDetails(clientId);
    }
    
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════╗");
        System.out.println("║  ONLINE FITNESS COACHING SYSTEM          ║");
        System.out.println("║  Version 1.0                             ║");
        System.out.println("╚═══════════════════════════════════════════╝");
        
        FitnessCoachingSystem system = new FitnessCoachingSystem();
        system.menu();
    }
}
