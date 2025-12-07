package Final_Project;
public class ExampleRecord {
    public String id;
    public String relatedInformation1;
    public String relatedInformation2;

    public ExampleRecord(String id, String relatedInformation1, String relatedInformation2) {
        this.id = id;
        this.relatedInformation1 = relatedInformation1;
        this.relatedInformation2 = relatedInformation2;
    }

    @Override
    public String toString() {
        return id + "," + relatedInformation1 + "," + relatedInformation2;
    }
}
