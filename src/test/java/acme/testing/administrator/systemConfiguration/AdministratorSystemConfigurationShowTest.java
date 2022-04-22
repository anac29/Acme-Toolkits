package acme.testing.administrator.systemConfiguration;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class AdministratorSystemConfigurationShowTest extends TestHarness
{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/administrator/systemConfiguration/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String strongSpamTerms, final String weakSpamTerms,
		final String acceptedCurrencies, final String defaultCurrency,final String weakThreshold,
		final String strongThreshold) 
	{
		super.signIn("administrator", "administrator");
		
		super.clickOnMenu("Administrator","System Configuration");
		super.checkFormExists();
		super.checkInputBoxHasValue("strongSpamTerms", strongSpamTerms);
		super.checkInputBoxHasValue("weakSpamTerms", weakSpamTerms);
		super.checkInputBoxHasValue("acceptedCurrencies", acceptedCurrencies);
		super.checkInputBoxHasValue("defaultCurrency", defaultCurrency);
		super.checkInputBoxHasValue("strongThreshold", strongThreshold);
	
	}

}
