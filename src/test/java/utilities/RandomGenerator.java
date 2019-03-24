package utilities;

import helpers.ConfigLoader;

public class RandomGenerator {
    public static int generateRandomNumber(int to, int from) {
        return ((int) (Math.random() * to) + from);
    }

    public static String generateRandomString(int size) {
        String AlphaNumericString = ConfigLoader.getValueByKey("random");
        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            int index = generateRandomNumber((AlphaNumericString.length() - 1), 0);
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
