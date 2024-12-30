import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Document;
import org.jsoup.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class JsoupUtil {

    public static void getData(){
        String url = "https://uri";
        Connection connection = Jsoup.connect(url)
                .data("commit","Search+Requests")
                .data("filters[per_page]","1000")
                .header("Cookie","token");

        Document doc = connection.get();
    }
}
