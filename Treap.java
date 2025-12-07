package Final_Project;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

class TreapNode<K extends Comparable<K>, V> {
    K key;
    V value;
    int priority;
    TreapNode<K, V> left, right;

    TreapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.priority = new Random().nextInt();
        left = right = null;
    }
}

public class Treap<K extends Comparable<K>, V> {
    private TreapNode<K, V> root;

    public Treap() {
        root = null;
    }

    // Right rotation
    private TreapNode<K, V> rotateRight(TreapNode<K, V> y) {
        TreapNode<K, V> x = y.left;
        TreapNode<K, V> T = x.right;
        x.right = y;
        y.left = T;
        return x;
    }

    // Left rotation
    private TreapNode<K, V> rotateLeft(TreapNode<K, V> x) {
        TreapNode<K, V> y = x.right;
        TreapNode<K, V> T = y.left;
        y.left = x;
        x.right = T;
        return y;
    }

    // Insert node
    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    private TreapNode<K, V> insert(TreapNode<K, V> node, K key, V value) {
        if (node == null)
            return new TreapNode<>(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = insert(node.left, key, value);
            if (node.left.priority > node.priority)
                node = rotateRight(node);
        } else if (cmp > 0) {
            node.right = insert(node.right, key, value);
            if (node.right.priority > node.priority)
                node = rotateLeft(node);
        } else {
            node.value = value; // update existing key
        }
        return node;
    }

    // Delete node
    public void delete(K key) {
        root = delete(root, key);
    }

    private TreapNode<K, V> delete(TreapNode<K, V> node, K key) {
        if (node == null)
            return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0)
            node.left = delete(node.left, key);
        else if (cmp > 0)
            node.right = delete(node.right, key);
        else { // found key
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;
            else {
                if (node.left.priority < node.right.priority) {
                    node = rotateLeft(node);
                    node.left = delete(node.left, key);
                } else {
                    node = rotateRight(node);
                    node.right = delete(node.right, key);
                }
            }
        }
        return node;
    }

    // Search
    public V search(K key) {
        TreapNode<K, V> node = root;
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node.value;
        }
        return null;
    }

    // In-order traversal (for range query / sorted output)
    public List<V> inOrder() {
        List<V> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(TreapNode<K, V> node, List<V> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.value);
            inOrder(node.right, list);
        }
    }

    // Range query
    public List<V> rangeQuery(K lower, K upper) {
        List<V> result = new ArrayList<>();
        rangeQuery(root, lower, upper, result);
        return result;
    }

    private void rangeQuery(TreapNode<K, V> node, K lower, K upper, List<V> list) {
        if (node == null)
            return;
        if (lower.compareTo(node.key) < 0)
            rangeQuery(node.left, lower, upper, list);
        if (lower.compareTo(node.key) <= 0 && upper.compareTo(node.key) >= 0)
            list.add(node.value);
        if (upper.compareTo(node.key) > 0)
            rangeQuery(node.right, lower, upper, list);
    }
}
