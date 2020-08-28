package fbLib;

public class FizzBuzz {

	private static String Fizz = "Fizz";
	private static String Buzz = "Buzz";
	private static boolean userInput = true;

	public class parameters {
		public parameters(String arg1, String arg2, boolean userInput) {
			FizzBuzz.Fizz = new String(arg1);
			FizzBuzz.Buzz = new String(arg2);
			FizzBuzz.userInput = userInput;
		}
	}

	public static void mainCode() {
		if (userInput) {
			String[] userNames = getUserInput();
			PrintLines(userNames[0], userNames[1]);
			return;
		}

		PrintLines(Fizz, Buzz);

		return;
	}

	private static String[] getUserInput() {
		String[] uInput = new String[2];

		System.out.printf("For default values, leave fields blank.\n\n");
		System.out.printf("Enter word for div 3: ");
		java.util.Scanner div3 = new java.util.Scanner(System.in);
		uInput[0] = div3.nextLine();
		if (uInput[0].isEmpty()) uInput[0] = Fizz;
		
		System.out.printf("Enter word for div 5: ");
		java.util.Scanner div5 = new java.util.Scanner(System.in);
		uInput[1] = div5.nextLine();
		if (uInput[1].isEmpty()) uInput[1] = Buzz;

		return uInput;
	}


	private static void PrintLines(String str1, String str2) {
		int i;
		for (i = 1; i <= 100; i++) {
			if ((i % 3 == 0) && ( i % 5 == 0)) {
				System.out.println(str1+str2);
				continue;
			}
			if (i % 3 == 0) {
				System.out.println(str1);
				continue;
			}
			if (i % 5 == 0) {
				System.out.println(str2);
				continue;
			}
			System.out.println(i);
		}
	}


}
