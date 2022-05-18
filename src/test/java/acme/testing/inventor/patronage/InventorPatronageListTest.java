package acme.testing.inventor.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationMomentDate, final String startMomentDate, 
		final String finalMomentDate, final String link, final String inventor, final String patron,
		final String patronName,final String patronSurname,final String patronEmail) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","List Patronages");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, creationMomentDate);
		super.checkColumnHasValue(recordIndex, 4, startMomentDate);
		super.checkColumnHasValue(recordIndex, 5, finalMomentDate);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("status", status);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		super.checkInputBoxHasValue("creationMomentDate", creationMomentDate);
		super.checkInputBoxHasValue("startMomentDate", startMomentDate);
		super.checkInputBoxHasValue("finalMomentDate", finalMomentDate);
		super.checkInputBoxHasValue("link", link);
	    super.checkInputBoxHasValue("patronName", patronName);
        super.checkInputBoxHasValue("patronSurname", patronSurname);
        super.checkInputBoxHasValue("patronEmail", patronEmail);
		
	 

		
		super.signOut();
	}
}
