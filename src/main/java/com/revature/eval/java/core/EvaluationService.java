package com.revature.eval.java.core;

import java.time.temporal.Temporal;
import java.time.temporal.ChronoUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;


public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		char[] reversed = new char[string.length()];
		for (int i = reversed.length - 1, j=0; i >= 0; i--, j++) {
			reversed[j] = string.charAt(i);
		}
		return new String(reversed);
	}

	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		// The punctuation "-" is used to separate two words in a
		// pseudo-compound word construction, so it is handled 
		// separately from other punctuation. 
		phrase = phrase.replaceAll("-", " ");
		phrase = phrase.replaceAll("\\p{Punct}", "");
		String[] split_phrase = phrase.split(" ");
		char[] acr = new char[split_phrase.length];
		for (int i = 0; i < split_phrase.length; i++){
			acr[i] = Character.toUpperCase(split_phrase[i].charAt(0));
		}
		return new String(acr);
		
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}
		
		private int numEqualSides() {
			if (this.sideOne == this.sideTwo && this.sideOne == this.sideThree){
				return 3;
			} else if (this.sideOne == this.sideTwo || this.sideOne == this.sideThree || this.sideTwo == this.sideThree) {
				return 2;
			} else {
				return 0;
			}
			
		}

		public boolean isEquilateral() {
			return (this.numEqualSides() == 3);
		}

		public boolean isIsosceles() {
			return (this.numEqualSides() >= 2);
		}

		public boolean isScalene() {
			return (this.numEqualSides() == 0);
		}

	}

	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		HashMap<Character, Integer> scores = new HashMap<Character, Integer>();
		char[][] lookup = {{ 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T' }, { 'D', 'G' }, { 'B', 'C', 'M', 'P' }, { 'F', 'H', 'V', 'W', 'Y' }, { 'K' }, {}, {}, {'J', 'X'}, {}, {'Q', 'Z'}}; 
		for (int i = 0; i < lookup.length; i++) {
			for (int j = 0; j < lookup[i].length; j++) {
				scores.put(lookup[i][j], i + 1); 
			}
		}
		int curr_score = 0;
		for (int i = 0; i < string.length(); i++) {
			curr_score += scores.get(Character.toUpperCase(string.charAt(i)));
		}
		return curr_score;
	}

	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) {
		string = string.replaceAll( "[^\\d]", "" );
		if (string.length() == 10) {
			return string;
		} else if (string.length() == 11 && string.charAt(0) == '1') {
			return string.substring(1);
		} else {
			throw new IllegalArgumentException("Invalid phone number");
		}
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		string = string.replaceAll("\n", "");
		string = string.replaceAll("[^a-zA-Z]", " ");
		String[] arr = string.split(" ");
		Map<String, Integer> counts = new HashMap<String, Integer>();
		for (int i = 0; i < arr.length; i++) {
			counts.putIfAbsent(arr[i], 0);
			counts.replace(arr[i], counts.get(arr[i]) + 1);
		}
		return counts;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			int low = 0;
			int high = this.sortedList.size();
			int mid; 
			while (low < high) {
				mid = (low + high) / 2;
				T curr = this.sortedList.get(mid);
				if (curr.compareTo(t) == 0) {
					return mid;
				} else if (curr.compareTo(t) == 1) {
					high = mid;
				} else {
					low = mid;
				}
				
			}
			throw new IllegalArgumentException("The queried element doesn't exist in the list");
		}

		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}

		public List<T> getSortedList() {
			return sortedList;
		}

		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}

	}

	/**
	 * 8. Implement a program that translates from English to Pig Latin.
	 * 
	 * Pig Latin is a made-up children's language that's intended to be confusing.
	 * It obeys a few simple rules (below), but when it's spoken quickly it's really
	 * difficult for non-children (and non-native speakers) to understand.
	 * 
	 * Rule 1: If a word begins with a vowel sound, add an "ay" sound to the end of
	 * the word. Rule 2: If a word begins with a consonant sound, move it to the end
	 * of the word, and then add an "ay" sound to the end of the word. There are a
	 * few more rules for edge cases, and there are regional variants too.
	 * 
	 * See http://en.wikipedia.org/wiki/Pig_latin for more details.
	 * 
	 * @param string
	 * @return
	 */
	
	/**
	 * Below we assume that a vowel letter always means a vowel sound at the 
	 * beginning of a word. This isn't actually true, however, as words such 
	 * as university (yuniversity) demonstrate. 
	 * @return
	 */
	public Boolean startsWithVowel(String string) {
		return (string.charAt(0) == 'a' || string.charAt(0) == 'e' || string.charAt(0) == 'i' || string.charAt(0) == 'o' || string.charAt(0) == 'u');
	}
	
	public String toPigLatin(String string) {
		string = string.toLowerCase();
		String[] phrase = string.split(" ");
		
		for (int i = 0; i < phrase.length; i++) {
			String s = phrase[i];
			if (this.startsWithVowel(s)) {
				s = s.concat("ay");
			} else if (s.charAt(0) == 'q' && s.charAt(1) == 'u') {
				s = s.substring(2).concat("quay");
			} else {
				String suffix = "";
				while (!this.startsWithVowel(s)) {
					suffix = suffix.concat(String.valueOf(s.charAt(0)));
					s = s.substring(1);
				}
				s = s.concat(suffix).concat("ay");
			}
			phrase[i] = s;
		}
		return String.join(" ", phrase);
	}

	/**
	 * 9. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		char[] digits = (Integer.toString(input)).toCharArray();
		int numPlaces = digits.length;
		int count = 0;
		for (char c : digits) {
			count += (int) Math.pow(Double.valueOf(Character.getNumericValue(c)), Double.valueOf(numPlaces));
		}
		
		return (count == input);
	}

	/**
	 * 10. Compute the prime factors of a given natural number.
	 * 
	 * A prime number is only evenly divisible by itself and 1.
	 * 
	 * Note that 1 is not a prime number.
	 * 
	 * @param l
	 * @return
	 */
	// Is x divisible by y? 
	public boolean isDivisibleBy(long x, long y) {
		return (x % y == 0);
	}
	
	public List<Long> calculatePrimeFactorsOf(long l) {
		List<Long> primeFactors = new ArrayList<Long>();
		long i = 2L;
		while (l >= i) {
			if (this.isDivisibleBy(l, i)) {
				primeFactors.add(i);
				l = l / i;
			} else {
				i++;
			}
		}
		return (List<Long>) primeFactors;
	}

	/**
	 * 11. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}
		
		
		public char rotateChar(char c) {
			if (!Character.isLetter(c)) {
				return c;
			} else {
				int upperA = 65;
				int lowerA = 97;
				int ascii = (int) c;
				int a = Character.isUpperCase(c) ? upperA : lowerA;
				return ((char) ((((int) ascii - a + this.key) % 26) + a));
			}
		}
		public String rotate(String string) {
			String encoded = "";
			for (int i = 0; i < string.length(); i++) {
				encoded = encoded.concat(String.valueOf(this.rotateChar(string.charAt(i))));
			}
			return encoded;
		}
	}

	/**
	 * 12. Given a number n, determine what the nth prime is.
	 * 
	 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
	 * that the 6th prime is 13.
	 * 
	 * If your language provides methods in the standard library to deal with prime
	 * numbers, pretend they don't exist and implement them yourself.
	 * 
	 * @param i
	 * @return
	 */
	
	public boolean isDivisibleBy(int i, int j) {
		return (i % j == 0); 
	}
	
	public int calculateNthPrime(int i) {
		if (i < 1) {
			throw new IllegalArgumentException("Only accepts positive integers");
		}
		List<Integer> primes = new ArrayList<Integer>();
		primes.add(2);
		int j; 
		while (primes.size() < i) {
			j = primes.get(primes.size() - 1);
			while (true) {
				j++;
				boolean isPrime = true;
				for (int k = 0; k < primes.size(); k++) {
					if (this.isDivisibleBy(j, primes.get(k))){
						isPrime = false;
					}
				}
				if (isPrime) {
					primes.add(j);
					break;
				}
			}
		}
		return primes.get(primes.size() - 1);
	}

	/**
	 * 13 & 14. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {

		/**
		 * Question 13
		 * 
		 * @param string
		 * @return
		 */
		public static List<String> Split(String string, int i){
			List<String> ans = new ArrayList<String>((string.length() + i - 1) / i);
			for (int j = 0; j < string.length(); j += i) {
				ans.add(string.substring(j, Math.min(string.length(), j + i)));
			}
			return ans; 
		}
		
		public static int mapToAlphabet(char c) {
			int upperA = 65;
			int lowerA = 97;
			int ascii = (int) c;
			int a = Character.isUpperCase(c) ? upperA : lowerA;
			return (ascii - a); 
		}
		
		public static String rotate(String string) {
			String ans = "";
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				int n = mapToAlphabet(c);
				RotationalCipher cipher = new RotationalCipher((25 - 2 * n));
				ans = ans.concat(cipher.rotate(Character.toString(c)));
			}
			return ans;
		}
		public static String encode(String string) {
			string = rotate(string.replaceAll("\\p{Punct}", "").toLowerCase()).replaceAll(" ", "");
			List<String> l = Split(string, 5);
			String ans = String.join(" ", l);
			return ans;
		}

		/**
		 * Question 14
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			return (encode(string).replaceAll(" ", ""));
		}
	}

	/**
	 * 15. The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		int END = 9;
		string = string.replaceAll("-", "");
		if (string.length() != 10) {
			return false; 
		}
		
		int ctr = 0;
		for (int i = 0; i < string.length() - 1; i++) {
			if (!Character.isDigit(string.charAt(i))) {
				return false;
			}
			
			int j = (string.charAt(i) == 'X') ? 10 : Character.getNumericValue(string.charAt(i));
			ctr += j * (10 - i);
		}
		if (!Character.isDigit(string.charAt(9)) && string.charAt(9) != 'X') {
			return false;
		}
		int check = (string.charAt(END) == 'X') ? 10 : Character.getNumericValue(string.charAt(END));
		return ((ctr + check) % 11 == 0);
	}

	/**
	 * 16. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public int sumArray(int[] nums) {
		int ctr = 0;
		for (int i = 0; i < nums.length; i++) {
			ctr += nums[i];
		}
		return ctr;
	}
	public boolean isPangram(String string) {
		string = string.toLowerCase();
		int lowerA = 97;
		int[] check = new int[26];
		for (int i = 0; i < string.length(); i++) {
			if (Character.isLetter(string.charAt(i))){
				int ascii = (int) string.charAt(i);
				check[ascii - lowerA] = 1;
			}
		}
		return (this.sumArray(check) == 26);
	}

	/**
	 * 17. Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		try {
			return (given.plus(1000000000L, ChronoUnit.SECONDS));
		}
		catch (Exception UnsupportedTemporalTypeException){
			LocalDateTime modified = ((LocalDate) given).atStartOfDay();
			return (modified.plus(1000000000L, ChronoUnit.SECONDS));
		}
	}

	/**
	 * 18. Given a number, find the sum of all the unique multiples of particular
	 * numbers up to but not including that number.
	 * 
	 * If we list all the natural numbers below 20 that are multiples of 3 or 5, we
	 * get 3, 5, 6, 9, 10, 12, 15, and 18.
	 * 
	 * The sum of these multiples is 78.
	 * 
	 * @param i
	 * @param set
	 * @return
	 */
	
	
	
	public int getSumOfMultiples(int i, int[] set) {
		ArrayList<Integer> s = new ArrayList<Integer>();
		for (int j = 0; j < i ; j ++) {
			for (int x : set) {
				if (this.isDivisibleBy(j, x) && !s.contains(j)) {
					s.add(j);
					break;
				}
			}
		}
		int ctr = 0;
		for (int x : s) {
			ctr += x;
		}
		return ctr;
	}

	/**
	 * 19. Given a number determine whether or not it is valid per the Luhn formula.
	 * 
	 * The Luhn algorithm is a simple checksum formula used to validate a variety of
	 * identification numbers, such as credit card numbers and Canadian Social
	 * Insurance Numbers.
	 * 
	 * The task is to check if a given string is valid.
	 * 
	 * Validating a Number Strings of length 1 or less are not valid. Spaces are
	 * allowed in the input, but they should be stripped before checking. All other
	 * non-digit characters are disallowed.
	 * 
	 * Example 1: valid credit card number 1 4539 1488 0343 6467 The first step of
	 * the Luhn algorithm is to double every second digit, starting from the right.
	 * We will be doubling
	 * 
	 * 4_3_ 1_8_ 0_4_ 6_6_ If doubling the number results in a number greater than 9
	 * then subtract 9 from the product. The results of our doubling:
	 * 
	 * 8569 2478 0383 3437 Then sum all of the digits:
	 * 
	 * 8+5+6+9+2+4+7+8+0+3+8+3+3+4+3+7 = 80 If the sum is evenly divisible by 10,
	 * then the number is valid. This number is valid!
	 * 
	 * Example 2: invalid credit card number 1 8273 1232 7352 0569 Double the second
	 * digits, starting from the right
	 * 
	 * 7253 2262 5312 0539 Sum the digits
	 * 
	 * 7+2+5+3+2+2+6+2+5+3+1+2+0+5+3+9 = 57 57 is not evenly divisible by 10, so
	 * this number is not valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isAllDigit(String string) {
		for (int i = 0; i < string.length(); i++) {
			if (!Character.isDigit(string.charAt(i))){
				return false;
			}
		}
		return true;
	}
	
	public boolean isLuhnValid(String string) {
		string = string.replaceAll(" ", "");
		if (!this.isAllDigit(string)){
			return false;
		}
		
		int[] nums = new int[string.length()];
		for (int i = 0; i < string.length(); i++) {
			if (i % 2 == 0) {
				nums[i] = Character.getNumericValue(string.charAt(i));
			} else {
				int times = Character.getNumericValue(string.charAt(i)) * 2;
				nums[i] = (times > 9) ? (times - 9) : times;
			}
		}
		
		int ctr = 0;
		for (int i = 0; i < nums.length; i++) {
			ctr += nums[i];
		}
		return (ctr % 10 == 0);
	}

	/**
	 * 20. Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		// TODO Write an implementation for this method declaration
		return 0;
	}

}
