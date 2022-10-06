import java.io.IOException;
import java.net.URI;



class Handler implements URLHandler {
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {  // in this if statement, if gets the path with "/" than it would return a number.
            return String.format("Number: %d", num);
        } else if (url.getPath().equals("/increment")) {  // If the url gets "/increment" then the num would increase by 1.
            num += 1;
            return String.format("Number incremented!");  // returning with a message saying that the number has increased.
        } else {
            System.out.println("Path: " + url.getPath());   // This prints out the path/
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");  // The parameters should be splited by the query.
                if (parameters[0].equals("count")) {  // If statement that  if parameter is equal to a "count".
                    num += Integer.parseInt(parameters[1]);
                    return String.format("Number increased by %s! It's now %d", parameters[1], num); // In the site it shows that number has increased.
                }
            }
            return "404 Not Found!";  /// if it can't find the path, then it would print a error.
        }
    }
}


class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());   // This is server that makes the handleRequest work.
    } 
}



