public class BruteForce {
    public static void main(String[] args) {
        String hay = "mason_made_muffins_for_march_madness_on_monday";
        String needle = "monday";
        int counter = 0;
        for (int i = 0; i < hay.length(); i++) {
            int orig = i;
            for (int j = 0; j < needle.length(); j++) {
                counter ++;
                if (hay.charAt(orig) == needle.charAt(j)) {
                    orig++;
                    if ((orig - i) == needle.length()) {
                        System.out.println("We Found it!");
                        System.out.println("We Compared " + counter + " times!");
                        return;
                    }
                } else {
                    break;
                }
            }
        }
        System.out.println("No Found.");
        System.out.println("We Compared " + counter + " times!");
    }
}
