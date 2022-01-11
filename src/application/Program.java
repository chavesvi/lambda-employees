package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Comparator;

import entities.Employee;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		List<Employee> employees = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = sc.next();
		
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while (line != null) {
				String[] d = line.split(";");
				employees.add(new Employee(d[0], d[1], Double.parseDouble(d[2])));
				line = br.readLine();
			}
			
			System.out.print("Enter salary: ");
			double salary = sc.nextDouble();
			
			Comparator<String> comp = (s1, s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
			
			List<String> email = employees.stream()
					.filter(s -> s.getSalary() > salary)
					.map(e -> e.getEmail()).sorted(comp)
					.collect(Collectors.toList());
			
			email.forEach(System.out::println);
			
			char m = 'M';
			
			double sumSal = employees.stream()
					.filter(n -> n.getName().charAt(0) == m)
					.map(s -> s.getSalary())
					.reduce(0.0, (x, y) -> x + y);
			
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sumSal));
					
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		sc.close();

	}

}
