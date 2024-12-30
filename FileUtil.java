import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import org.springfrmework.util.StringUtils;

public class FileUtil {

    public static ArrayList<String> csvReader(String filePath){
        File csv = new File(filePath);
        csv.setReadable(true);
        BufferedReader br = null;
        ArrayList<String> list = new ArrayList<>();

        try(InputStreamReader isr = new InputStreamReader(new FileInputStream(csv))) {
            br = new BufferedReader(isr);
            String line ;
            while((line = br.readLine()) != null){
                if(StringUtils.hasText(line)){
                    list.add(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }
}
