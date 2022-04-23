package acme.testing.inventor.patronageReport;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.core.annotation.Order;

import acme.testing.TestHarness;

public class InventorPatronageReportTest extends TestHarness
{
	@ParameterizedTest
    @CsvFileSource(resources = "/inventor/patronage-report/list.csv", encoding = "utf-8", numLinesToSkip = 1)
    @Order(10)
    public void positiveTest(final int recordIndex, final String automaticSequenceNumber, 
        final String creationMoment, final String memorandum, final String link, final String patronageCode) {
        
        super.signIn("inventor1", "inventor1");
        
        super.clickOnMenu("Inventor","Patronage Report list");
        super.checkListingExists();
        super.sortListing(1, "asc");
        
        super.checkColumnHasValue(recordIndex, 0, automaticSequenceNumber);
        super.checkColumnHasValue(recordIndex, 1, creationMoment);
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
