package sha256Lib;

import java.io.*;
import java.util.*;
import static java.lang.System.*;
import java.math.BigInteger;  
import java.nio.charset.StandardCharsets; 
import java.security.MessageDigest;  
import java.security.NoSuchAlgorithmException;

public class sha256 {

	private static byte[] getHash(String input) throws NoSuchAlgorithmException {
		MessageDigest msg = MessageDigest.getInstance("SHA-256");
		return msg.digest(input.getBytes(StandardCharsets.UTF_8));
	}

	private static String hexConvert(byte[] hashbytes) {
		StringBuilder hexStr = new StringBuilder();
		for (byte b : hashbytes) {
    			hexStr.append(String.format("%02x", b));
		}

		return hexStr.toString();
	}

	private static String strSHA(String initial) {
		try {
			initial = hexConvert(getHash(initial));
		}

		catch (NoSuchAlgorithmException error) {
			out.println(error);
		}
		
		return initial;
	}

	private static String getInput() {
		System.out.printf("Enter a value: ");
		Scanner lineInput = new Scanner(in);
		String uInput = lineInput.nextLine();
		String output = strSHA(uInput);
		return output;
	}

	private static String getSilentInput() {
		String sInput = new String();
		Console current = System.console();
		
		try {
			char[] passBytes = current.readPassword("Enter new value: ");
			sInput = String.valueOf(passBytes);
		}

		catch (Exception error) {
			System.out.println(error);
		}

		String outData = strSHA(sInput);
		return outData;
	}

	private static String getFileStr() {
		String data = new String();

		try {
			File compare = new File("data.txt");
			Scanner readFile = new Scanner(compare);
			String orig = readFile.nextLine();
			data = orig;
			readFile.close();
		}
		catch (Exception error) {
			System.out.println(error);
		}
		return data;
	}

	private static int writeFileStr(String progInput) {
		try {
			FileWriter tempfile = new FileWriter("data.txt", false);
			tempfile.write(progInput);
			tempfile.close();
		}

		catch (Exception error) {
			System.out.println(error);
			return 0;
		}
		return 1;
	}

	public static void mainCode() {
		boolean goodAnswer = false;

		System.out.printf("Do you want override the word to guess?[y/n] ");
		Scanner ans = new Scanner(System.in);
		char yorn = ans.next().charAt(0);
		System.out.println();

		if (yorn == 'y') {
			String overwrite = getSilentInput();
			if (writeFileStr(overwrite) == 1) {
				System.out.println("Overwrite Successful! Now guess...");
				System.out.println();
			}
		}

		String data = getFileStr();

		while (!(goodAnswer)) {
			String input = getInput();

			if (input.equals(data)) {
				goodAnswer = true;
				System.out.println("Correct guess!");
			}

			else System.out.println("Incorrect, try again!");
		}

		return;

	}

}
