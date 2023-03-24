import java.io.IOException;
import java.net.*;
import java.util.Scanner;
import java.util.regex.*;


public class HtmlAnalyzer {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Por favor, insira uma URL como argumento.");
            return;
        }
        //Testa conexao com try/catch
        try {
            URL url = new URL(args[0]);
            Scanner scanner = new Scanner(url.openStream());

            while(scanner.hasNext()){
                String line = scanner.nextLine();
                if (checaFormacaoHTML(line)) {
                    System.out.println("malformed HTML");
                    break;
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("URL connection error: " + e.getMessage());
        }
    }

    //Identificando estruturas mal-formadas do HTML utilizando if else.
    public static boolean checaFormacaoHTML(String html) {
        int contaTags = 0;

        for (int i = 0; i < html.length(); i++) {
            if (html.charAt(i) == '<' && i + 1 < html.length() && html.charAt(i + 1) != '/' && html.charAt(i + 1) != '!') {
                contaTags++;
            } else if (html.charAt(i) == '<' && i + 2 < html.length() && html.charAt(i + 1) == '/' && html.charAt(i + 2) != '>') {
                contaTags--;
            }
        }

        return contaTags != 0;
    }


    //Há uma outra forma de identificar estruturas mal-formadas do HTML utilizando "java.util.regex.*".
    /*
    public static boolean checaFormacaoHTML(String html) {
        int contadorTags = 0;
        Pattern aberturaTag = Pattern.compile("<[^/!][^>]*>");
        Pattern fechamentoTag = Pattern.compile("</[^>]*>");
        Matcher checaAbertura = aberturaTag.matcher(html);
        Matcher checaFechamento = fechamentoTag.matcher(html);

        while (checaAbertura.find()) {
            contadorTags++;
        }

        while (checaFechamento.find()) {
            contadorTags--;
        }
        return contadorTags != 0;
    }
    */
}
