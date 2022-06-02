package acme.testing.inventor.item;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorItemDeleteTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/delete-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndex,
		final String name, final String code, final String technology, final String description, 
		final String retailPrice, final String itemType, final String link, final String nameAd, 
		final String codeAd,
		final String technologyAd, final String descriptionAd) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("itemType", itemType);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, itemType);
		super.checkColumnHasValue(recordIndex, 3, technology);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);

		super.clickOnSubmit("Delete");

		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, nameAd);
		super.checkColumnHasValue(recordIndex, 1, codeAd);
		super.checkColumnHasValue(recordIndex, 3, technologyAd);
		super.checkColumnHasValue(recordIndex, 4, descriptionAd);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", nameAd);
		super.checkInputBoxHasValue("code", codeAd);
		super.checkInputBoxHasValue("technology", technologyAd);
		super.checkInputBoxHasValue("description", descriptionAd);
		
		super.clickOnSubmit("Delete");
		super.checkNotErrorsExist();
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/item/delete-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndex,
		final String name, final String code, final String technology, final String description, 
		final String retailPrice, final String itemType, final String link) {
		
		super.signIn("inventor2", "inventor2");
		
		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.clickOnButton("Create");
		super.fillInputBoxIn("name", name);
		super.fillInputBoxIn("code", code);
		super.fillInputBoxIn("technology", technology);
		super.fillInputBoxIn("description", description);
		super.fillInputBoxIn("retailPrice", retailPrice);
		super.fillInputBoxIn("itemType", itemType);
		super.fillInputBoxIn("link", link);
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 2, itemType);
		super.checkColumnHasValue(recordIndex, 3, technology);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("retailPrice", retailPrice);

		super.clickOnSubmit("Publish");

		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		
		super.checkColumnHasValue(recordIndex, 0, name);
		super.checkColumnHasValue(recordIndex, 1, code);
		super.checkColumnHasValue(recordIndex, 3, technology);
		super.checkColumnHasValue(recordIndex, 4, description);
		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		
		super.checkInputBoxHasValue("name", name);
		super.checkInputBoxHasValue("code", code);
		super.checkInputBoxHasValue("technology", technology);
		super.checkInputBoxHasValue("description", description);
		
		super.clickOnMenu("Inventor","List my Items");
		super.checkListingExists();
		super.sortListing(1, "asc"); 
		super.clickOnListingRecord(recordIndex);
		super.checkNotButtonExists("Delete");
	}	
	
}
