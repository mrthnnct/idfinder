import java.net.*;
import java.io.*;
import java.util.Scanner;

//ogrenci numaralarini bilmedigim icin deneyemiyorum ama calisicak gibi duruyo
public class IdFinder {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter id:  ");

        String id = scanner.nextLine();
        boolean isfound = false;

        if (!id.isEmpty()) {
            try {
                URI destination = new URI("https://www.ecs.soton.ac.uk/people/" + id);
                URL url = destination.toURL();
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                Scanner webscanner = new Scanner(connection.getInputStream());

                String readline = webscanner.nextLine();

                while (readline != null) {
                    if (readline.contains("og:title")) {
                        
                        isfound = true;

                        int len = readline.length();
                        String finalname = readline.substring(35, len-4);

                        System.out.println("name is: " + finalname);

                        break;
                    }
                    readline = webscanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println(e);
            }
            if (isfound) {
                System.out.println("name not found. ");
            }
            
        }
        else {
            System.out.println("enter valid id. ");
        }
        scanner.close();
    }
}
