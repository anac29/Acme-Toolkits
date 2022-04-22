package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListMineTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationMomentDate, final String startMomentDate, 
		final String finalMomentDate, final String link, final String inventor, final String patron ) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","Patronage list");
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
		
		// Falta comprobar el perfil del inventor
		
		super.signOut();
	}
}
