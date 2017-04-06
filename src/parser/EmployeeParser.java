package parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

import domain.Employee;

/**
 * The class to parse a JSON file and populate Employee class in "domain"
 * package
 * 
 * @author Vlad Gorbich
 *
 */
public class EmployeeParser {

	Employee employee;
	List<Employee> employeeList = new ArrayList<Employee>();

	public void parseEmployee(String file) {

		try (FileInputStream json = new FileInputStream(file)) {
			JsonParser jp = Json.createParser(json);

			while (jp.hasNext()) {
				// Return the event for the next parsing state
				Event event = jp.next();

				// Start of JSON object. Position the parser after '{'
				if (event.equals(Event.START_OBJECT)) {
					employee = new Employee();
					employeeList.add(employee);
				} else if (event.equals(Event.KEY_NAME)) {
					String keyName = jp.getString();
					switch (keyName) {
					case "firstName":
						jp.next();
						employee.setFirstName(jp.getString());
						break;
					case "lastName":
						jp.next();
						employee.setLastName(jp.getString());
						break;
					case "email":
						jp.next();
						employee.setEmail(jp.getString());
						break;
					case "employeeId":
						jp.next();
						employee.setEmployeeId(jp.getInt());
						break;
					case "hireDate":
						jp.next();
						// Convert date string from JSON into java.util.Date
						// object.
						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						Date hireDate;
						try {
							hireDate = dateFormat.parse(jp.getString());
							employee.setHireDate(hireDate);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						break;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showAllEmployees() {
		for (Employee employee : employeeList) {
			System.out.println(employee.getFirstName() + " " + employee.getLastName() + " " + employee.getEmail() + " "
					+ employee.getEmployeeId() + " " + employee.getHireDate());
		}

	}
}
