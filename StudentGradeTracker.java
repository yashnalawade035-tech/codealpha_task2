import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Student class
class Student {
    private String name;
    private double grade;

    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }
}

// Main class
public class StudentGradeTracker {

    public static void main(String[] args) {

        ArrayList<Student> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("     STUDENT GRADE TRACKER    ");
            System.out.println("==============================");
            System.out.println("1. Add Student");
            System.out.println("2. View Summary Report");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear newline
            } catch (InputMismatchException e) {
                System.out.println("⚠ Invalid input! Please enter a number (1-3).");
                scanner.nextLine(); // clear wrong input
                continue;
            }

            switch (choice) {

                case 1:
                    // Add Student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();

                    double grade = -1;

                    while (true) {
                        System.out.print("Enter student grade (0-100): ");
                        try {
                            grade = scanner.nextDouble();
                            scanner.nextLine();

                            if (grade >= 0 && grade <= 100) {
                                break;
                            } else {
                                System.out.println("⚠ Grade must be between 0 and 100.");
                            }

                        } catch (InputMismatchException e) {
                            System.out.println("⚠ Invalid input! Enter numeric value only.");
                            scanner.nextLine();
                        }
                    }

                    students.add(new Student(name, grade));
                    System.out.println("✅ Student added successfully!");
                    break;

                case 2:
                    if (students.isEmpty()) {
                        System.out.println("⚠ No student records available.");
                        break;
                    }

                    double total = 0;
                    double highest = students.get(0).getGrade();
                    double lowest = students.get(0).getGrade();
                    String highestName = students.get(0).getName();
                    String lowestName = students.get(0).getName();

                    for (Student s : students) {
                        total += s.getGrade();

                        if (s.getGrade() > highest) {
                            highest = s.getGrade();
                            highestName = s.getName();
                        }

                        if (s.getGrade() < lowest) {
                            lowest = s.getGrade();
                            lowestName = s.getName();
                        }
                    }

                    double average = total / students.size();

                    System.out.println("\n========== SUMMARY REPORT ==========");
                    System.out.println("Total Students  : " + students.size());
                    System.out.printf("Class Average   : %.2f\n", average);
                    System.out.printf("Highest Score   : %.2f (%s)\n", highest, highestName);
                    System.out.printf("Lowest Score    : %.2f (%s)\n", lowest, lowestName);
                    System.out.println("====================================");
                    break;

                case 3:
                    running = false;
                    System.out.println("👋 Exiting program... Thank you!");
                    break;

                default:
                    System.out.println("⚠ Invalid choice! Please select 1-3.");
            }
        }

        scanner.close();
    }
}
