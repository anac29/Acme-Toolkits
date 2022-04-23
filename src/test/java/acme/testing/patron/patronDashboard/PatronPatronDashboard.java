package acme.testing.patron.patronDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronDashboard extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patron-dashboard/form.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","Dashboard");
		super.checkFormExists();
		
		super.signOut();
	}
}
