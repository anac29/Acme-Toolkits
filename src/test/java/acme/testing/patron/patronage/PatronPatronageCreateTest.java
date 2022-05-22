package acme.testing.patron.patronage;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class PatronPatronageCreateTest  extends TestHarness{
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status,final String code, final String legalStuff, final String budget, final String startMomentDate,final String finalMomentDate,
		final String link) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startMomentDate", startMomentDate);
		super.fillInputBoxIn("finalMomentDate", finalMomentDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 5, startMomentDate);
		super.checkColumnHasValue(recordIndex, 6, finalMomentDate);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void negativeTest(final int recordIndex, final String status,final String code, final String legalStuff, final String budget, final String startMomentDate,final String finalMomentDate,
		final String link) {
		super.signIn("patron1", "patron1");
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("legalStuff", legalStuff);
		super.fillInputBoxIn("budget", budget);
		super.fillInputBoxIn("startMomentDate", startMomentDate);
		super.fillInputBoxIn("finalMomentDate", finalMomentDate);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
	}

}
