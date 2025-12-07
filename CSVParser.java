package Final_Project;
public interface CSVParser<V> {
    V parse(String line);        // 将 CSV 一行转成对象
    String toCSV(V value);       // 将对象转成 CSV 一行
    <K> K getKey(V value);       // 提取 key 用于 HashMap / Treap
}
