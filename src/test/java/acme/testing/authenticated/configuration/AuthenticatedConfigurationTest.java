package acme.testing.authenticated.configuration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AuthenticatedConfigurationTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/authenticated/configuration/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex ,final String acceptedCurrencies, final String defaultCurrency, final String link) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Authenticated","System Configuration");
		
		super.checkFormExists();
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("link", link);
		
		
		super.signOut();
	}

}
