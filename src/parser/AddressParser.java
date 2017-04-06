package parser;

import java.io.FileInputStream;
import java.io.IOException;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 * The class to parse a JSON file and print the output to the console.
 * 
 * @author Vlad Gorbich
 *
 */
public class AddressParser {

	public void parseAddress(String file) {

		try (FileInputStream json = new FileInputStream(file)) {
			JsonParser jr = Json.createParser(json);
			Event event = null;

			// Advance to "address" key
			while (jr.hasNext()) {
				event = jr.next();
				if (event == Event.KEY_NAME && "address".equals(jr.getString())) {
					event = jr.next();
					break;
				}
			}

			// Output contents of "address" object
			while (event != Event.END_OBJECT) {
				switch (event) {
				case KEY_NAME: {
					System.out.print(jr.getString());
					System.out.print(" = ");
					break;
				}
				case VALUE_FALSE: {
					System.out.println(false);
					break;
				}
				case VALUE_NULL: {
					System.out.println("null");
					break;
				}
				case VALUE_NUMBER: {
					if (jr.isIntegralNumber()) {
						System.out.println(jr.getInt());
					} else {
						System.out.println(jr.getBigDecimal());
					}
					break;
				}
				case VALUE_STRING: {
					System.out.println(jr.getString());
					break;
				}
				case VALUE_TRUE: {
					System.out.println(true);
					break;
				}
				default: {
				}
				}
				event = jr.next();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
