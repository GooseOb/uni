import java.util.HashMap;
import java.util.Map;

public final class Coder {
    private Coder() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    private static final Map<Character, Character> CODE_MAP = new HashMap<>();

    static {
        char[][] pairs = {
                { 'G', 'A' }, { 'D', 'E' }, { 'R', 'Y' }, { 'P', 'O' }, { 'L', 'U' }, { 'K', 'I' }
        };
        for (char[] pair : pairs) {
            CODE_MAP.put(pair[0], pair[1]);
            CODE_MAP.put(pair[1], pair[0]);
            CODE_MAP.put(Character.toLowerCase(pair[0]), Character.toLowerCase(pair[1]));
            CODE_MAP.put(Character.toLowerCase(pair[1]), Character.toLowerCase(pair[0]));
        }
    }

    public static String code(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            result.append(CODE_MAP.getOrDefault(c, c));
        }
        return result.toString();
    }
}
