package Generator;

import java.util.HashSet;
import java.util.Set;

/*
 * Generate identifier names.
 *
 */
public class MyIdentifier {
    final String javaLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    final String javaLetterDigit = "ABCDEFGHIJKLMNOPQRSTUVWXYabcdefghjijklmnopqrstuvwxyzZ12345674890";
    final static java.util.Random rand = new java.util.Random();
    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final static Set<String> identifiers = new HashSet<String>();

    private String identifier_name;

    public void generateIdentifier() {
        this.setIdentifier_name(randomIdentifier());
    }

    private String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                if (i == 0) {
                    builder.append(javaLetter.charAt(rand.nextInt(javaLetter.length())));
                }
                builder.append(javaLetterDigit.charAt(rand.nextInt(javaLetterDigit.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public String getIdentifier_name() {
        return identifier_name;
    }

    public void setIdentifier_name(String identifier_name) {
        this.identifier_name = identifier_name;
    }
}
