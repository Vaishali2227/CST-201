package algorthims_topic1;
import java.util.Arrays;
import java.util.Scanner;


public class Anagram {
	  public static boolean areAnagrams(String word1, String word2) {
	        // check if the lengths of the words are equal
	        if (word1.length() != word2.length()) {
	            return false;
	        }

	        // convert each word  to array of character  eat => e,a,t  tea=> t,e,a
	        char[] charArray1 = word1.toCharArray();
	        char[] charArray2 = word2.toCharArray();

	        // sort the character arrays   a,e,t    a,e,t
	        Arrays.sort(charArray1);
	        Arrays.sort(charArray2);

	        // compare sorted arrays
	        return Arrays.equals(charArray1, charArray2);
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // read two words from the user
	        System.out.println("Enter the first word:");
	        String word1 = scanner.nextLine();
	        System.out.println("Enter the second word:");
	        String word2 = scanner.nextLine();
	        // Check if the words are anagrams
	        boolean result = areAnagrams(word1, word2);

	        // the result
	        System.out.println("Are the words anagrams? " + result);
	    }

}
