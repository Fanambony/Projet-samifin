package generator.utils;

public class ExtractSubstring {


    public static String getBetweenString(String mainString , String startSubstring) {

        // Trouver l'index de début
        int startIndex = mainString.indexOf(startSubstring);
        if (startIndex != -1) {
            startIndex += startSubstring.length(); // Déplacer l'index au début de la sous-chaîne recherchée

            // Extraire la sous-chaîne depuis l'index de début jusqu'à la fin de la chaîne principale
            String extractedSubstring = mainString.substring(startIndex);
            System.out.println("Sous-chaîne extraite : " + extractedSubstring);

            return extractedSubstring.replace("/", ".");
        } else {
            System.out.println("La chaîne de début n'a pas été trouvée.");
        }
        return null;
    }

    public  static String getSeparatorBefore() {
        String separator = "\\\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        return separator;
    }
    public  static String getSeparatorAfter() {
        String separator = "\\";
        if(System.getProperty("os.name").equals("Linux"))
            separator = "/";
        return separator;
    }

        // Trouver l'index de début

}
