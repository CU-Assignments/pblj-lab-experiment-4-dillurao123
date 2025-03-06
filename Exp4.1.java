import java.util.ArrayList;
import java.util.Iterator;

class Employee {
    int id;
    String name;
    double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

public class EmployeeManagementSystem {
    private ArrayList<Employee> employees;

    public EmployeeManagementSystem() {
        employees = new ArrayList<>();
    }

     public void addEmployee(int id, String name, double salary) {
         for (Employee e : employees) {
            if (e.id == id) {
                System.out.println("Error: Employee with ID " + id + " already exists.");
                return;
            }
        }
        employees.add(new Employee(id, name, salary));
        System.out.println("Employee Added: ID=" + id + ", Name=" + name + ", Salary=" + salary);
    }

     public void updateEmployeeSalary(int id, double newSalary) {
        for (Employee e : employees) {
            if (e.id == id) {
                e.salary = newSalary;
                System.out.println("Employee ID " + id + " updated successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

     public void searchEmployee(String query) {
        for (Employee e : employees) {
            if (String.valueOf(e.id).equals(query) || e.name.equalsIgnoreCase(query)) {
                System.out.println("Employee Found: " + e);
                return;
            }
        }
        System.out.println("No employee found with ID or Name: " + query);
    }

     public void removeEmployee(int id) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee e = iterator.next();
            if (e.id == id) {
                iterator.remove();
                System.out.println("Employee ID " + id + " removed successfully.");
                return;
            }
        }
        System.out.println("Error: Employee with ID " + id + " not found.");
    }

     public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee e : employees) {
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        EmployeeManagementSystem system = new EmployeeManagementSystem();

         System.out.println("Test Case 1: Display Employees");
        system.displayEmployees(); 

         System.out.println("\nTest Case 2: Add Employees");
        system.addEmployee(101, "Anish", 50000);  
        system.addEmployee(102, "Bobby", 60000);  
         System.out.println("\nTest Case 3: Update Employee Salary");
        system.updateEmployeeSalary(101, 55000); 

         System.out.println("\nTest Case 4: Search Employee by ID");
        system.searchEmployee("102");  
         System.out.println("\nTest Case 5: Remove Employee");
        system.removeEmployee(101);  

         System.out.println("\nTest Case 6: Display Employees");
        system.displayEmployees();   

         System.out.println("\nTest Case 7: Adding Duplicate Employee ID");
        system.addEmployee(101, "Charlie", 70000);  
    }
}
