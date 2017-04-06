package parser;

public class ParserDemo {

	public static void main(String[] args) throws Exception {

		AddressParser ap = new AddressParser();
		ap.parseAddress("input/address.json");
		
		System.out.println("---");

		EmployeeParser ep = new EmployeeParser();
		ep.parseEmployee("input/employee.json");
		ep.showAllEmployees();
		
	}

}
