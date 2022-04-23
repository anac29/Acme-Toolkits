package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemShowTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/tool/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveToolTest(final int recordIndex,
		final String name, final String code, final String technology, final String description, 
		final String retailPrice, final String itemType, final String link) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor","List Tools");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();

	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/component/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(20)
	public void positiveComponentTest(final int recordIndex,
		final String name, final String code, final String technology, final String description, 
		final String retailPrice, final String itemType, final String link) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor","List Components");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, technology);

		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("link", link);
		
		super.signOut();

	}
}
