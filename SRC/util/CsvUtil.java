package util;

 // Utility for CSV escaping/unescaping to handle fields that may contain commas.
public class CsvUtil {

    private static final char COMMA = ',';
    private static final char ESCAPE_CHAR = ';'; // simple replacement

     // Escape a field by replacing commas with a placeholder.
    public static String escape(String field) {
        if (field == null) return "";
        return field.replace(COMMA, ESCAPE_CHAR);
    }

    
    // Unescape a field by replacing placeholder back to commas.
    
    public static String unescape(String field) {
        if (field == null) return "";
        return field.replace(ESCAPE_CHAR, COMMA);
    }
}