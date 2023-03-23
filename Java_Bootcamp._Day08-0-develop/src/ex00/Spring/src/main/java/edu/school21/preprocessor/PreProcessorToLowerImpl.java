package edu.school21.preprocessor;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String process(String str) {
        return str.toLowerCase();
    }
}
