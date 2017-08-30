package testngpackage;

public class TestCase {
	
public static void main(String[] args) {
		
		TestNGClass testcase = new TestNGClass();
		TestNGClass.setUpTest();
		testcase.completeForm();
		testcase.messageTest();
		TestNGClass.tearDownTest();

		}
}