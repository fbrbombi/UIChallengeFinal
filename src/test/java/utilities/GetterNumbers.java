package utilities;

public class GetterNumbers {

    public static String getNumbers(String element) {
        char[] arrayElement = element.toCharArray();
        StringBuilder n = new StringBuilder();
        for (int i = 0; i < arrayElement.length; i++) {
            if (Character.isDigit(arrayElement[i])) {
                n.append(arrayElement[i]);
            }
        }
        return n.toString();
    }
}
