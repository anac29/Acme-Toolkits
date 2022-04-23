package acme.testing.administrator.administratorDashboard;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorAdministratorDashboardShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/administrator-dashboard/form.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex) {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","Dashboard");
		super.checkFormExists();
		
		super.signOut();
	}
}
