package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageDeleteTest extends TestHarness { 
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String status,final String code, final String legalStuff, final String budget, final String startMomentDate,final String finalMomentDate,
		final String link, final String statusAd,final String codeAd, final String legalStuffAd, final String budgetAd, final String startMomentDateAd,final String finalMomentDateAd,
		final String linkAd) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
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
	
		super.checkColumnHasValue(recordIndex, 6, finalMomentDate);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("finalMomentDate", finalMomentDate);

		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
	
		super.checkColumnHasValue(recordIndex, 0, statusAd);
		super.checkColumnHasValue(recordIndex, 1, codeAd);
		super.checkColumnHasValue(recordIndex, 2, budgetAd);
		super.checkColumnHasValue(recordIndex, 5, startMomentDateAd);
		super.checkColumnHasValue(recordIndex, 6, finalMomentDateAd);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("code", codeAd);
		super.checkInputBoxHasValue("legalStuff", legalStuffAd);
		super.checkInputBoxHasValue("budget", budgetAd);
		super.checkInputBoxHasValue("startMomentDate", startMomentDateAd);
		super.checkInputBoxHasValue("finalMomentDate", finalMomentDateAd);
		super.checkInputBoxHasValue("link", linkAd);
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
		
		super.signOut();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex, final String status,final String code, final String legalStuff, final String budget, final String startMomentDate,final String finalMomentDate,
		final String link) {
		super.signIn("patron1", "patron1");

		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
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
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.clickOnSubmit("Publish");
		
		super.clickOnMenu("Patron", "List my Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc");
		super.clickOnListingRecord(recordIndex);
		super.checkNotButtonExists("Delete");

		super.signOut();
	}

}
