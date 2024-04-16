// Runtime.java
package backend;

import java.util.HashMap;
import java.util.Map;

public class Runtime {
    private Map<String, Data> symbolTable = new HashMap<>();

    public void setVariable(String name, Data value) {
        symbolTable.put(name, value);
    }

    public Data getVariable(String name) {
        return symbolTable.get(name);
    }
}
