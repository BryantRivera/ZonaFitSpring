package gm.zona_fit;
import java.util.Scanner;

public class subCadena {
    public static boolean uniqueCharacters(String input) {
        return input.chars().distinct().count() == input.length(); //saber si la cadena tiene datos unicos con respuesta booleana
    }

    public static String getLargestCharacters(String input) {
        String max = "";//Registrar las cadenas unicas
        for (int i = 0; i < input.length(); i++) { //saber la longitud de la cadena con un doble for
            for (int j = i + 1; j <= input.length(); j++) {
                String word = input.substring( i, j );
                if (uniqueCharacters( word ) && word.length() > max.length()) { // entra la condicion si es verdadero la cadena unica y si la palabra es mas que la palabra maxima
                    max = word;
                }
            }
        }
        return max;
    }

    public static int getUniqueCharacters(String string) {
        return getLargestCharacters(string).length();//hacer el conteo de la subcadena de datos unicos
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the data:");
        String data = sc.nextLine();

        System.out.println("Data: " + data);
        System.out.println("Out: " + getUniqueCharacters(data));
        System.out.println("Explain: " + getLargestCharacters(data));

    }
}
