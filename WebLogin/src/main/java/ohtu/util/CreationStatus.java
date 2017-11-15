package ohtu.util;

import java.util.ArrayList;
import java.util.List;

public class CreationStatus {
    public List<String> errors;

    public CreationStatus() {
        errors = new ArrayList<>();
    }

    public void addError(String error){
        errors.add(error);
    }
    
    public List<String> errors() {
        return errors;
    }

    public boolean isOk() {
        return errors.isEmpty();
    } 
}
