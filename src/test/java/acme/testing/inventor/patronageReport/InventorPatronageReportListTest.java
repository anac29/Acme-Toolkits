package acme.testing.inventor.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class InventorPatronageReportListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronageReport/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String automaticSequenceNumber, final String creationMoment, 
		final String memorandum, final String link, final String patronage ) {
		
		super.signIn("inventor1", "inventor1");
		
		super.clickOnMenu("Inventor","Patronage Report list");
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0,creationMoment );
		super.checkColumnHasValue(recordIndex, 1, automaticSequenceNumber);
		super.checkColumnHasValue(recordIndex, 2, memorandum);

		

		super.signOut();
	}
}