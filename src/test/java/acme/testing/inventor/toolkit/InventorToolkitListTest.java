package acme.testing.inventor.toolkit;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorToolkitListTest extends TestHarness{


	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/toolkit/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex, final String code, final String title, 
		final String description, final String assemblyNotes, final String link, final String published,final String totalPrice) { 
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","List Toolkits");
		super.checkListingExists();
		super.sortListing(0, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, code);
		super.checkColumnHasValue(recordIndex, 1, title);
		super.checkColumnHasValue(recordIndex, 2, description);
		super.checkColumnHasValue(recordIndex, 3, published);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("assemblyNotes", assemblyNotes);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("totalPrice", totalPrice);
		super.checkInputBoxHasValue("published",published);
		
		super.clickOnButton("See Tools");
		super.checkListingExists();
		super.clickOnButton("Return");
		super.clickOnButton("See Components");
		super.checkListingExists();
		
		super.signOut();
	}
}
