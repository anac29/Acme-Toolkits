package acme.testing.patron.patronage;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageListMineTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronage/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String status, final String code, 
		final String legalStuff, final String budget, final String creationMomentDate, final String startMomentDate, 
		final String finalMomentDate, final String link, final String name, final String surname, final String email,final String publish ) {

		
super.signIn("patron1", "patron1");
		    
		super.clickOnMenu("Patron","List my Patronages");
		super.checkListingExists();
		super.sortListing(4, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, status);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, budget);
		super.checkColumnHasValue(recordIndex, 3, publish);
		super.checkColumnHasValue(recordIndex, 4, creationMomentDate);
		super.checkColumnHasValue(recordIndex, 5, startMomentDate);
		super.checkColumnHasValue(recordIndex, 6, finalMomentDate);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("legalStuff", legalStuff);
		super.checkInputBoxHasValue("budget", budget);
		if(publish.equals("true")) {
			super.checkInputBoxHasValue("creationMomentDate", creationMomentDate);
			super.checkInputBoxHasValue("inventorName", name);
			super.checkInputBoxHasValue("inventorSurname", surname);
			super.checkInputBoxHasValue("inventorEmail", email);
		}
		super.checkInputBoxHasValue("startMomentDate", startMomentDate);
		super.checkInputBoxHasValue("finalMomentDate", finalMomentDate);
		super.checkInputBoxHasValue("link", link);

	}
	
	@Test
	@Order(30)
	public void hackingTest() {
		super.checkNotLinkExists("Patron");
		super.navigate("/patron/patronage/list");
		super.checkPanicExists();

		super.signIn("inventor1", "inventor1");
		super.navigate("/patron/patronage/list");
		super.checkPanicExists();
		super.signOut();

		super.signIn("administrator", "administrator");
		super.navigate("/patron/patronage/list");
		super.checkPanicExists();

		super.signOut();
	}
}
