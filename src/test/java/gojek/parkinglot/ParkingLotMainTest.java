package gojek.parkinglot;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gojek.parkinglot.processor.FileProcessor;
import gojek.parkinglot.processor.Processor;

public class ParkingLotMainTest {
	
	private final ByteArrayOutputStream	outContent	= new ByteArrayOutputStream();
	
	private final String inputText = "create_parking_lot 6\r\n" + 
			"park KA-01-HH-1234 White\r\n" + 
			"park KA-01-HH-9999 White\r\n" + 
			"park KA-01-BB-0001 Black\r\n" + 
			"park KA-01-HH-7777 Red\r\n" + 
			"park KA-01-HH-2701 Blue\r\n" + 
			"park KA-01-HH-3141 Black\r\n" + 
			"leave 4\r\n" + 
			"status\r\n" + 
			"park KA-01-P-333 White\r\n" + 
			"park DL-12-AA-9999 White\r\n" + 
			"registration_numbers_for_cars_with_colour White\r\n" + 
			"slot_numbers_for_cars_with_colour White\r\n" + 
			"slot_number_for_registration_number KA-01-HH-3141\r\n" + 
			"slot_number_for_registration_number MH-04-AY-1111";
	
	@Before
	public void init()
	{
		System.setOut(new PrintStream(outContent));
	}
	
	private void provideInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
    }
	
	@After
	public void cleanUp()
	{
		System.setOut(null);
	}
	
	@Test
	public void testMainIvalidArgs()
    {
		String [] args = { "one", "two", "three" };
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Invalid input"));
    }
	
	@Test
	public void testMainCommandLineExit()
    {
		String [] args = {};
		provideInput("Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Enter one of the following commands"));
    }
	
	@Test
	public void testMainCommandLineCreate()
    {
		String [] args = {};
		provideInput("create_parking_lot 6 \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Created a parking lot with 6 slots"));
    }
	
	@Test
	public void testMainCommandLinePark()
    {
		String [] args = {};
		provideInput("create_parking_lot 6 \n park KA-01-HH-1234 White \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Allocated slot number: 1"));
    }
	
	@Test
	public void testMainCommandLineParkFull()
    {
		String [] args = {};
		provideInput("create_parking_lot 1 \n park KA-01-HH-1234 White \n park KA-01-HH-9999 White \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Sorry, parking lot is full"));
    }
	
	@Test
	public void testMainCommandLineRegisterationNumbersForCarsWithColour()
    {
		String [] args = {};
		provideInput("create_parking_lot 1 \n park KA-01-HH-1234 White \n registration_numbers_for_cars_with_colour White \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("KA-01-HH-1234"));
    }
	
	@Test
	public void testMainCommandLineSlotNumbersForRegisterationNumber()
    {
		String [] args = {};
		provideInput("create_parking_lot 2 \n park KA-02-HH-2234 White \n slot_number_for_registration_number KA-02-HH-2234 \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("1"));
    }
	
	@Test
	public void testMainCommandLineSlotNumbersForCarsWithColour()
    {
		String [] args = {};
		provideInput("create_parking_lot 2 \n park KA-02-HH-2234 White \n slot_numbers_for_cars_with_colour White \n Exit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("1"));
    }
	
	@Test
	public void testMainCommandLineLeave()
    {
		String [] args = {};
		provideInput("create_parking_lot 6 \n park KA-01-HH-1234 White \n Leave 1 \nExit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("Slot number 1 is free"));
    }
	
	@Test
	public void testMainCommandLineStatus()
    {
		String [] args = {};
		provideInput("create_parking_lot 6 \n park KA-01-HH-1234 White \n Status \nExit");
		ParkingLotMain.main(args );
		assertTrue(outContent.toString().contains("1		KA-01-HH-1234		White"));
    }
	
	@Test
	public void testFileReader() throws IOException
    {
		Path path = Files.createTempFile("sample-file", ".txt");
        File file = path.toFile();
        Files.write(path, inputText.getBytes(StandardCharsets.UTF_8));
        file.deleteOnExit();
        Processor fileProcessor = new FileProcessor(file);
        fileProcessor.process();
		assertTrue(outContent.toString().contains("Created a parking lot with 6 slots"));
		assertTrue(outContent.toString().contains("Allocated slot number: 1"));
		assertTrue(outContent.toString().contains("Allocated slot number: 2"));
		assertTrue(outContent.toString().contains("Allocated slot number: 3"));
		assertTrue(outContent.toString().contains("Allocated slot number: 4"));
		assertTrue(outContent.toString().contains("Allocated slot number: 5"));
		assertTrue(outContent.toString().contains("Allocated slot number: 6"));
		assertTrue(outContent.toString().contains("Slot number 4 is free "));
		assertTrue(outContent.toString().contains("Slot No.	Registration No.	Colour"));
		assertTrue(outContent.toString().contains("1		KA-01-HH-1234		White"));
		assertTrue(outContent.toString().contains("2		KA-01-HH-9999		White"));
		assertTrue(outContent.toString().contains("3		KA-01-BB-0001		Black"));
		assertTrue(outContent.toString().contains("5		KA-01-HH-2701		Blue"));
		assertTrue(outContent.toString().contains("6		KA-01-HH-3141		Black"));
		assertTrue(outContent.toString().contains("Allocated slot number: 4"));
		assertTrue(outContent.toString().contains("Sorry, parking lot is full"));
		assertTrue(outContent.toString().contains("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333"));
		assertTrue(outContent.toString().contains("1, 2, 4"));
		assertTrue(outContent.toString().contains("6"));
		assertTrue(outContent.toString().contains("Not found "));
    }
	
	public void testApp()
    {
        assertTrue( true );
    }
}
