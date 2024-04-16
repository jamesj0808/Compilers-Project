package backend;

// Data type abstract class
public abstract class Data {
    public abstract double getNumericValue();   // Get the numeric value of the data, throws if data is non-numeric
    public abstract String getType(); // Data description
    @Override
    public abstract String toString(); // Data converts to string representation
}

// Handles numeric data
class NumericData extends Data {
    private double value;

    public NumericData(double value) {
        this.value = value;
    }

    @Override
    public double getNumericValue() {
        return value;
    }

    @Override
    public String getType() {
        return "Numeric";
    }

    @Override
    public String toString() {
        return String.format("%.2f", value);
    }
}

// Handles string data
class StringData extends Data {
    private String value;

    public StringData(String value) {
        this.value = value;
    }

    // Error message for string data validity
    @Override
    public double getNumericValue() {
        throw new UnsupportedOperationException("String data isn't valid for numeric operations.");
    }

    @Override
    public String getType() {
        return "String";
    }

    @Override
    public String toString() {
        return value;
    }
}
