import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChainedTable<K, V> {
	LinkedList<Entry<K, V>>[] bucketArray;
	int collisions;

	@SuppressWarnings("unchecked")
	/**
	 * Create a ChainedTableSolution instance with the specified
	 * number of buckets.
	 * 
	 * @param buckets the number of buckets to make in this table
	 */
	public ChainedTable(int buckets) {
		bucketArray = (LinkedList<Entry<K, V>>[]) new LinkedList[buckets];
		collisions = 0;
		for (int i = 0; i < bucketArray.length; i++) {
			bucketArray[i] = new LinkedList<Entry<K,V>>();
		}
	}

	/**
	 * _Part 1: Implement this method._
	 *
	 * Puts an entry into the table. If the key already exists,
	 * it's value is updated with the new value and the previous
	 * value is returned.
	 * 
	 * @param key
	 *            the object used as a key to retrieve the value
	 * @param value
	 *            the object stored in association with the key
	 * 
	 * @throws IllegalArgumentException
	 *            if the value parameter is null
	 *
	 * @return the previously stored value or null if the key is new
	 */
	public V put(K key, V value) {
		// Calculates the index
		int n = bucketArray.length;
		int index = (n + (key.hashCode() % n)) % n;
		// Replaces previous value with new value if keys match and returns previous value
		for (Entry<K,V> e : bucketArray[index]) {
			if (e.key.equals(key)) {
				V prevValue = e.value;
				e.value = value;
				return prevValue;
			}
		}
		// Adds Entry to front of bucket if no key exists and returns null
		bucketArray[index].addFirst(new Entry<K,V>(key, value));
		return null;
	}

	/**
	 * _Part 2: Implement this method._
	 *
	 * Retrieves the value associated with the specified key. If
	 * it exists, the value stored with the key is returned, if no
	 * value has been associated with the key, null is returned.
	 * 
	 * @param key
	 *            the key object whose value we wish to retrieve
	 * @return the associated value, or null
	 */
	public V get(K key) {
		// Calculates index
		int n = bucketArray.length;
		int index = (n + (key.hashCode() % n)) % n;
		// Walks through bucket until key is found and returns its value
		for (Entry<K,V> e : bucketArray[index]) {
			if (e.key.equals(key)) return e.value;
		}
		// Return null if key isn't found
		return null;
	}

	/**
	 * _Part 3: Implement this method._
	 *
	 * Looks through the entire bucket where the specified key
	 * would be found and counts the number of keys in this bucket
	 * that are not equal to the current key, yet still have the
	 * same hashcode. Be efficient! (i.e., recall that calling .get()
	 * on a linked list is O(n) where n is the size of the list)
	 *
	 *
	 * @param key
	 * @return a count of collisions
	 */
	public int countTrueCollisions(K key) {
		// Calculates index
		int n = bucketArray.length;
		int index = (n + (key.hashCode() % n)) % n;
		int collisionNum = 0;
		// Walks through each Entry and increments collisionNum if key doesn't match
		for (Entry<K,V> e : bucketArray[index]) {
			if (!e.key.equals(key) && e.key.hashCode() == key.hashCode()) {
				collisionNum++;
			}
		}
		return collisionNum;
	}


	/**
	 * _Part 4: Implement this method._
	 *
	 * Returns the number of entries (i.e., Entry instances) in
	 * this table
	 * 
	 * @return the number of entries.
	 */
	public int size() {
		int totalEntries = 0;
		for (LinkedList<Entry<K, V>> ll : bucketArray) {
			totalEntries += ll.size();
		}
		return totalEntries;
	}


	public static class Entry<K, V> {
		K key;
		V value;

		public Entry(K k, V v) {
			key = k;
			value = v;
		}
	}

	public static void testing(String text, String watch) throws FileNotFoundException {
		//Scanner s = new Scanner(new File("./smalltext.txt"));
		Scanner s = new Scanner(text);
		// The Key is the alphabet/numerals and the Value is the frequency
		ChainedTable<String, Integer> map = new ChainedTable<String, Integer>(5);
		String str;
		Integer value;
		System.out.println("Reading");
		int i = 0;
		long start = System.nanoTime();
		// Scans through each word in the text
		while (s.hasNext()) {
			str = s.next();
			if (str.equals(watch)) System.out.println("Found " + watch);
			value = map.get(str);
//			System.out.println("Got "+str);
			// Demonstrates that put() works because the value gets updated
			if (value == null) {
				map.put(str, 1);
			} else {
				map.put(str, value + 1);
			}
			i++;
			if (i % 5000 == 0)
				System.out.println("Read " + i + " words");

		}
		System.out.println("Hashed!");
		// Demonstrates that the get() function works (frequency of character/word)
		System.out.println(watch + " (hash code: " + watch.hashCode() + ") was seen " + map.get(watch) + " times");
		System.out.println("It took " + (System.nanoTime() - start) / 1.0E9 + " seconds");
		// Demonstrates that countTrueCollisions() works (number of other characters/words in the same bucket)
		System.out.println("Number of collisions: "+map.countTrueCollisions(watch));
		// Demonstrates that size() works (total number of unique characters/words)
		System.out.println("Number of unique words found: " + map.size());

		// Prints the bucketArray for visualization. Hashtable is working as intended.
		for (LinkedList<Entry<String, Integer>> ll : map.bucketArray) {
			System.out.print("[ ");
			for (Entry<String, Integer> e : ll) {
				System.out.print(e.key + " ");
			}
			System.out.print("]\n");
		}
		System.out.println();
	}


	public static void main(String[] args) throws FileNotFoundException {
		// Aa and BB should count as 1 collision
		String text = "A A B C D E F G H I J K L M N O P Q R S T U V W X Y Z Z Z Z 0 1 2 3 4 5 6 7 8 9 Aa Aa BB ";
		// Code observes this character/word
		testing(text, "Aa");
		testing(text, "BB");
		testing(text, "Z");
	}

}
