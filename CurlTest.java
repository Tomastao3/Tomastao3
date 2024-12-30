import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CurlTest {
    public static String getToken(String url) throws IOException {
        String curlCommand = String.format("curl -svk %s username:pwd", url);
        Process process = Runtime.getRuntime().exec(curlCommand);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        String line;
        while((line = reader.readLine()) != null){

            if(line.contains("Location")){
                return line;
            }
        }
        return "";
    }

}
