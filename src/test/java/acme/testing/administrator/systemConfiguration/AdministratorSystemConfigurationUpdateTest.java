package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationUpdateTest extends TestHarness{

	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final String strongSpamTerms, final String weakSpamTerms,
		final String acceptedCurrencies, final String defaultCurrency,final String weakThreshold,
		final String strongThreshold) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System Configuration");

		super.checkFormExists();
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		
		super.clickOnSubmit("Update");

		super.clickOnMenu("Administrator", "System Configuration");
		
		super.checkFormExists();
		
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("weakThreshold", weakThreshold);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold);
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/update-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final String strongSpamTerms, final String weakSpamTerms,
		final String acceptedCurrencies, final String defaultCurrency,final String weakThreshold,
		final String strongThreshold) {
		
		super.signIn("administrator", "administrator");

		super.clickOnMenu("Administrator", "System Configuration");

		super.checkFormExists();
		super.fillInputBoxIn("strongSpamTerms", strongSpamTerms);
		super.fillInputBoxIn("weakSpamTerms", weakSpamTerms);
		super.fillInputBoxIn("acceptedCurrencies", acceptedCurrencies);
		super.fillInputBoxIn("defaultCurrency", defaultCurrency);
		super.fillInputBoxIn("weakThreshold", weakThreshold);
		super.fillInputBoxIn("strongThreshold", strongThreshold);
		super.clickOnSubmit("Update");

		super.checkErrorsExist();

		super.signOut();
	}
	
}
