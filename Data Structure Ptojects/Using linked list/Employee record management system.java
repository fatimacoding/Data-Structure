/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructuresproject;

/**
 *
 * @author fatima
 */
import java.util.Scanner;

class Employee {
    String name;
    int id;
    String firstDayOfWork;
    String phoneNumber;
    String address;
    int workHours;
    double salary;
    Employee next;
    Employee prev;

    public Employee(String name, int id, String firstDayOfWork, String phoneNumber, String address, int workHours,
            double salary) {
        this.name = name;
        this.id = id;
        this.firstDayOfWork = firstDayOfWork;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.workHours = workHours;
        this.salary = salary;
        this.next = null;
        this.prev=null;
    }
}

class EmployeeRecordManagementSystem {
    Employee head;

    public EmployeeRecordManagementSystem() {
        this.head = null;
    }
    
    //check if a record with given id is already exists or does not exist
    public boolean checkRecord(int id) { 
        Employee temp = head;
        while (temp != null) {
            if (temp.id == id) {
                return true; // Record already exists
            }
            temp = temp.next;
        }
        return false; // Record does not exist
    }
    
    //Insert a new emplyee record into the linked list in ascending oeder of the id
    public void insertEmployeeRecord(String name, int id, String firstDayOfWork, String phoneNumber,
            String address,int workHours, double salary) {
        
        Employee newEmployee = new Employee(name, id, firstDayOfWork, phoneNumber, address, workHours, salary);
        if(checkRecord(id)){
           System.out.println("Employee record with ID " + id + " is already exists! \nThe record will not be inserted.");
            return; 
        }
        if (head == null || id < head.id) {
            newEmployee.next = head;
            if (head != null) {
                head.prev = newEmployee;
            }
            head = newEmployee;
        }else {
            Employee temp = head;
            while (temp.next != null && temp.next.id < id) {
                temp = temp.next;
            }
            newEmployee.next = temp.next;
            if (temp.next != null) {
            temp.next.prev = newEmployee;
        }
            temp.next = newEmployee;
            newEmployee.prev = temp;
        }
        System.out.println("Employee records inserted successfully.");  
    }
    
    //delete an employee record with the given id
    public int deleteEmployeeRecord(int id) {
           
    Employee temp = head;
    while (temp != null && temp.id != id) {
        temp = temp.next;
    }
    if (temp == null) {
        System.out.println("Employee record with ID " + id +" not found.");
        return -1;
    }else{

    if (temp == head) {
        head = temp.next;
        if (head != null) {
            head.prev = null;
        }
    } else {
        temp.prev.next = temp.next;
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
    }
    System.out.println("Employee record with ID " + id + " has been deleted.");
    return 0;
       }
    }
    
    //searches for an Employee with a matching ID.
    public Employee smartSearchRecord(int id) {
        Employee current = head;
        while (current != null) {
            if (current.id == id) {
                return current;
            }
            current = current.next;
        }
        return null; // Employee with the specified id not found
    }
    
    //update an employee record with the given id
    public void updateEmployeeRecord(int id) {
        
        Employee temp = smartSearchRecord(id);
        Scanner scanner = new Scanner(System.in);
        int choose=1;
        int choice;

        if (temp == null) {
            System.out.println("Employee record with ID " + id + " not found."); 
        }
        else{
            while(choose!=0){            
            System.out.println("1- Name");
            System.out.println("2- First day of work");
            System.out.println("3- Phone number");
            System.out.println("4- Address");
            System.out.println("5- Work hours");
            System.out.println("6- Salary");
            System.out.print("Choose the record you want to update:");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("\nEnter new name: ");
                    String name = scanner.nextLine();
                    temp.name = name;
                    System.out.println("Employee record updated successfully.");
                    break;
                case 2:
                    System.out.print("\nEnter new first day of work: ");
                    String firstDayOfWork = scanner.nextLine();
                    temp.firstDayOfWork = firstDayOfWork;
                    System.out.println("Employee record updated successfully.");
                    break;
                case 3:
                    System.out.print("\nEnter new phone number: ");
                    String phoneNumber = scanner.nextLine();
                    temp.phoneNumber = phoneNumber;
                    System.out.println("Employee record updated successfully.");
                    break;
                case 4:
                    System.out.print("\nEnter new address: ");
                    String address = scanner.nextLine();
                    temp.address = address;
                    System.out.println("Employee record updated successfully.");
                    break;
                case 5:
                    System.out.print("\nEnter new work hours: ");
                    int workHours = scanner.nextInt();
                    while(workHours<32){
                        System.out.print("Invalid input. Work hours cannot be less than 32 (default),"+
                                " try again.\nEnter work hours: ");
                        workHours = scanner.nextInt();
                    }
                    temp.workHours = workHours;
                    System.out.println("Employee record updated successfully.");
                    break;
                    case 6:
                    System.out.print("\nEnter new salary: ");
                    double salary = scanner.nextDouble();
                    temp.salary =salary;
                    System.out.println("Employee record updated successfully.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
            System.out.print("\nwant to update another record? 1 for Yes, 0 for No:");
            choose = scanner.nextInt();
            scanner.nextLine();
        }
        }
    }
    
    //show the details of an employee record with the given id
    public void showEmployeeDetails(int id) {

        Employee temp =head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Employee record with ID " + id + " not found.");
        }
        else{
        System.out.println("****************************************************************");
        System.out.println("Name: " + temp.name);
        System.out.println("ID: " + temp.id);
        System.out.println("First Day of Work: " + temp.firstDayOfWork);
        System.out.println("Phone Number: " + temp.phoneNumber);
        System.out.println("Address: " + temp.address);
        System.out.println("Work Hours: " + temp.workHours);
        System.out.println("Salary: " + temp.salary);
        System.out.println("****************************************************************");
        }
    }
    
    //search for an employee record with the given id
    public void searchEmployee(int id) {

        Employee temp = head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Employee record with ID " + id + " not found.");
            return;
        }
        System.out.println("Employee record with ID " + id + " found.");
    }
    
    //update the salary of an employee record with the given id based on the number of extra hours worked
    public void updateEmployeeSalary(int id) {

        Employee temp = head;
        while (temp != null && temp.id != id) {
            temp = temp.next;
        }

        if (temp == null) {
            System.out.println("Employee record with ID " + id + " not found.");
        }
        else{            
            int extraHours = temp.workHours - 32;
            double extraSalary = 0.0;

            if (extraHours > 0) {
                extraSalary = temp.salary * (extraHours * 0.02);
            }
            temp.salary += extraSalary;

            if (extraSalary > 0) {
                System.out.println("Salary updated successfully.");
            } else {
                System.out.println("No extra hours! Salary remains the same.");
            }  
        }
    }
}



public class DataStructuresProject {
    public static void main(String[] args) {
        EmployeeRecordManagementSystem recordSystem = new EmployeeRecordManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-------------$ Employee Record Management System $--------------\n");
            System.out.println("----------------------------------------------------------------");
            System.out.println("*************      1- Insert employee record       *************");
            System.out.println("*************      2- Delete employee record       *************");
            System.out.println("*************      3- Update employee record       *************");
            System.out.println("*************    4- Show details of an employee    *************");
            System.out.println("*************         5- Search an employee        *************");
            System.out.println("*************    6- Update salary of an employee   *************");
            System.out.println("*************               0- Exit                *************");
            System.out.println("----------------------------------------------------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    System.out.println("Done, thank you");
                    System.exit(0);
                case 1:
                    System.out.print("Enter name: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();                    
                    int id = IntegerInput("Enter ID: ");
                    System.out.print("Enter first day of work: ");
                    String firstDayOfWork = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter work hours: ");
                    int workHours = scanner.nextInt();
                    while(workHours<32){
                        System.out.print("Invalid input. Work hours cannot be less than 32 (default),"
                                +" try again.\nEnter work hours: ");
                        workHours = scanner.nextInt();
                    }
                    System.out.print("Enter salary: ");
                    double salary = scanner.nextInt();
                    scanner.nextLine();
                    recordSystem.insertEmployeeRecord(name, id, firstDayOfWork, phoneNumber, address, workHours, salary);
                    break;
                case 2:
                    int deleteId = IntegerInput("Enter ID of employee to delete: ");
                    recordSystem.deleteEmployeeRecord(deleteId);
                    break;
                case 3:
                    int updateId = IntegerInput("Enter ID of employee to update: ");
                    recordSystem.updateEmployeeRecord(updateId);
                    break;
                case 4:
                    int showId = IntegerInput("Enter ID of employee to show details: ");
                    recordSystem.showEmployeeDetails(showId);
                    break;
                case 5:
                    int searchId = IntegerInput("Enter ID of employee to search: ");
                    recordSystem.searchEmployee(searchId);
                    break;
                case 6:
                    int salaryId = IntegerInput("Enter ID of employee to update salary: ");
                    recordSystem.updateEmployeeSalary(salaryId);
                    break;
                default:
                    System.out.println("Invalid choice! try again.");
                    break;
            }
            System.out.println();
        }
        
    }
    private static int IntegerInput(String print) {
    int id = 0;
    boolean isValidId = false;
    Scanner scanner = new Scanner(System.in);

    while (!isValidId) {
        System.out.print(print);
        String idInput = scanner.nextLine();

        try {
            id = Integer.parseInt(idInput);
            if (id >= 1 && id <= 20) {
                isValidId = true;
            } else {
                System.out.println("Invalid ID, Please enter ID from 1 to 20.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID, Please enter an integer ID.");
        }
    }

    return id;
    }
}