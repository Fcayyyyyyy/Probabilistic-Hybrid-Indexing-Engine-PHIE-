package Final_Project;

public class ExampleRecordParser implements CSVParser<ExampleRecord> {
    @Override
    public ExampleRecord parse(String line) {
        String[] tokens = line.split(",");
        return new ExampleRecord(tokens[0], tokens[1], tokens[2]);
    }

    @Override
    public String toCSV(ExampleRecord value) {
        return value.toString();
    }

    @Override
    public String getKey(ExampleRecord value) {
        return value.id; // ID 作为 key
    }
}
