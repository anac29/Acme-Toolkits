package acme.testing.inventor.patronageReport;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageReportCreateTest extends TestHarness
{
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-positive.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void positiveTest(final int recordIndexAccepted,
		final int recordIndex,
		final String automaticsequencenumber,
		final String memorandum,final String link,final String patronagecode) 
	{
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List Accepted Patronages");
		
		super.clickOnListingRecord(recordIndexAccepted);
        super.checkFormExists();
        super.clickOnButton("Create Report");
        
        super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("confirmation", "true");
		super.clickOnSubmit("Create");
		
		super.clickOnMenu("Inventor","List Patronage Reports");
		super.sortListing(0, "desc");
		super.clickOnListingRecord(recordIndex);
		
		super.checkFormExists();
		super.checkInputBoxHasValue("memorandum", memorandum);
		super.checkInputBoxHasValue("link", link);
		super.checkInputBoxHasValue("patronageCode", patronagecode);
		super.checkInputBoxHasValue("automaticSequenceNumber", automaticsequencenumber);
		
		super.clickOnButton("Return");
		
		super.signOut();
		
	}
	
	@ParameterizedTest
	@CsvFileSource(resources = "/inventor/patronage-report/create-negative.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)
	public void negativeTest(final int recordIndexAccepted,
		final int recordIndex,
		final String automaticsequencenumber,
		final String memorandum,final String link,final String patronagecode,
		final String confirmation) 
	{
		super.signIn("inventor1", "inventor1");
		super.clickOnMenu("Inventor", "List Accepted Patronages");
		
		super.clickOnListingRecord(recordIndexAccepted);
        super.checkFormExists();
        super.clickOnButton("Create Report");
        
        super.fillInputBoxIn("memorandum", memorandum);
		super.fillInputBoxIn("link", link);
		
		super.fillInputBoxIn("confirmation", confirmation);
		super.clickOnSubmit("Create");
		
		super.checkErrorsExist();
		
	}
}
