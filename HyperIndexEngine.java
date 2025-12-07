package Final_Project;
import java.util.*;
import java.io.*;

public class HyperIndexEngine<K extends Comparable<K>, V> {
    private Treap<K, V> treap;
    private HashMap<K, V> hashMap;

    public HyperIndexEngine() {
        treap = new Treap<>();
        hashMap = new HashMap<>();
    }

    // Insert
    public void insert(K key, V value) {
        treap.insert(key, value);
        hashMap.put(key, value);
    }

    // Delete
    public void delete(K key) {
        treap.delete(key);
        hashMap.remove(key);
    }

    // Search (HashMap fast path)
    public V search(K key) {
        return hashMap.get(key);
    }

    // Range query (Treap)
    public List<V> rangeQuery(K lower, K upper) {
        return treap.rangeQuery(lower, upper);
    }

    // In-order traversal (sorted)
    public List<V> getSorted() {
        return treap.inOrder();
    }

    // Load from CSV
    public void loadCSV(String filePath, CSVParser<V> parser) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = br.readLine()) != null) {
            V value = parser.parse(line);
            K key = parser.getKey(value);
            insert(key, value);
        }
        br.close();
    }

    // Save to CSV
    public void saveCSV(String filePath, CSVParser<V> parser) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
        for (V value : getSorted()) {
            bw.write(parser.toCSV(value));
            bw.newLine();
        }
        bw.close();
    }

    // Top-K elements (based on Comparator)
    public List<V> topK(int k, Comparator<V> comparator) {
        PriorityQueue<V> heap = new PriorityQueue<>(comparator);
        for (V value : treap.inOrder()) {
            heap.offer(value);
            if (heap.size() > k)
                heap.poll();
        }
        List<V> result = new ArrayList<>();
        while (!heap.isEmpty()) result.add(heap.poll());
        Collections.reverse(result); // largest first
        return result;
    }
}
