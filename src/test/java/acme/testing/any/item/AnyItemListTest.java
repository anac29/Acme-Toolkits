package acme.testing.any.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class AnyItemListTest extends TestHarness {

	
	@ParameterizedTest
	@CsvFileSource(resources = "/any/item/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTestItems(final int recordIndex, final String name,final String code, final String technology,final String description, 
		 final String retailPrice, final String itemType, final String link,final String inventor,
		final String inventorName,final String inventorSurname, final String inventorEmail) {

		
		super.clickOnMenu("Anonymous","List Items");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, itemType);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, retailPrice);
		super.checkColumnHasValue(recordIndex, 5, link);


		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);
		super.checkInputBoxHasValue("itemType", itemType);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("inventorName", inventorName);
	    super.checkInputBoxHasValue("inventorSurname", inventorSurname);
	    super.checkInputBoxHasValue("inventorEmail", inventorEmail);

		

	}
	
	
}
