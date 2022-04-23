package acme.testing.administrator.administratorDashboard;

import org.junit.jupiter.api.Order;

import acme.testing.TestHarness;

public class AdministratorAdministratorDashboardShowTest extends TestHarness {

	@Order(10) 
	public void positiveTest() {
		
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","Dashboard");
		super.checkFormExists();
		
		super.signOut();
	}
}
