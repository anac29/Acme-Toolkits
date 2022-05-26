package acme.testing.patron.patronageReport;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.TestHarness;

public class PatronPatronageReportListTest extends TestHarness {

	@ParameterizedTest
	@CsvFileSource(resources = "/patron/patronageReport/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10) 
	public void positiveTest(final int recordIndex, final String automaticSequenceNumber, final String creationMoment, 
		final String memorandum, final String link, final String patronageCode ) {
		
		super.signIn("patron1", "patron1");
		
		super.clickOnMenu("Patron","List my Patronage Reports"); 
		super.checkListingExists();
		super.sortListing(1, "asc");
		
		super.checkColumnHasValue(recordIndex, 0, automaticSequenceNumber);
		super.checkColumnHasValue(recordIndex, 1,creationMoment );
		super.checkColumnHasValue(recordIndex, 2, memorandum);

		
		super.clickOnListingRecord(recordIndex);
		super.checkFormExists();
		super.checkInputBoxHasValue("automaticSequenceNumber", automaticSequenceNumber);
		super.checkInputBoxHasValue("creationMoment", creationMoment);
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronage.code", patronageCode);

		

		super.signOut();
		
		
	}
}