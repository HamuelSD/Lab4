package com.samuel.lab4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceTest {

    //Setting up mock resource repo & mock email provider
    @Mock
    ResourceRepository mockRepository;

    @Mock
    EmailProvider mockEmailProvider;

    @Test
    //Test Cases TC1.1 (Null Resource ID)
    public void TC1_1 () throws Exception {
        //Test Data
        UUID testID = null;
        String testEmail = "test.address@email.com";

        LibraryService testService = new LibraryService(mockEmailProvider,mockRepository);

        boolean result = testService.checkoutResource(testID, testEmail);

        assertFalse(result, "False expected with null resource ID" );
    }

    @Test
    //Test Case TC 1.2 (Everything works!)
    public void TC1_2 () throws Exception {
        //Test Data
        UUID testID = UUID.randomUUID();
        String testEmail = "test.address@email.com";
        String message = "Resource ID: " + testID + " checked out.";

        //Turn on the Mocks
        when(mockRepository.isResourceAvailable(testID)).thenReturn(true);
        when(mockRepository.updateStatus(testID,false)).thenReturn(true);
        when(mockEmailProvider.sendEmail(testEmail, message)).thenReturn(true);

        //start test
        LibraryService testService = new LibraryService(mockEmailProvider,mockRepository);

        boolean result = testService.checkoutResource(testID, testEmail);

        assertTrue(result, "Expected true for a fully successful checkout");
    }

    @Test
    //Test Case TC 1.3 (Email does not send.)
    public void TC1_3 () throws Exception {
        //Test Data
        UUID testID = UUID.randomUUID();
        String testEmail = "test.address@email.com";
        String message = "Resource ID: " + testID + " checked out.";

        //Turn on the Mocks
        when(mockRepository.isResourceAvailable(testID)).thenReturn(true);
        when(mockRepository.updateStatus(testID,false)).thenReturn(true);
        when(mockEmailProvider.sendEmail(testEmail, message)).thenReturn(false);

        //start test
        LibraryService testService = new LibraryService(mockEmailProvider,mockRepository);

        assertThrows(EmailFailureException.class, () -> {
            testService.checkoutResource(testID, testEmail);
        }, "Expected the Email Failure Excpetion when email does not work.");
        }

    @Test
    //Test Case TC 1.4 (Update Status Fails.)
    public void TC1_4 () throws Exception {
        //Test Data
        UUID testID = UUID.randomUUID();
        String testEmail = "test.address@email.com";
        String message = "Resource ID: " + testID + " checked out.";

        //Turn on the Mocks
        when(mockRepository.isResourceAvailable(testID)).thenReturn(true);
        when(mockRepository.updateStatus(testID,false)).thenReturn(false);

        //start test
        LibraryService testService = new LibraryService(mockEmailProvider,mockRepository);


        assertThrows(DatabaseFailureException.class, () -> {
            testService.checkoutResource(testID, testEmail);
        }, "Expected the Database update Failure Excpetion when update does not work.");
    }

    @Test
    //Test Case TC 1.5 (Resource Not Available.)
    public void TC1_5 () throws Exception {
        //Test Data
        UUID testID = UUID.randomUUID();
        String testEmail = "test.address@email.com";
        String message = "Resource ID: " + testID + " checked out.";

        //Turn on the Mocks
        when(mockRepository.isResourceAvailable(testID)).thenReturn(false);

        //start test
        LibraryService testService = new LibraryService(mockEmailProvider,mockRepository);

        boolean result = testService.checkoutResource(testID, testEmail);

        assertFalse(result, "Expected false when resourve is not available.");
    }
    }


