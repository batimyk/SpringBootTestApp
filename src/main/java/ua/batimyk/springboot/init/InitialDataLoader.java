package ua.batimyk.springboot.init;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.batimyk.springboot.model.Feature;
import ua.batimyk.springboot.model.FeatureDAO;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by N on 02-Oct-16.
 * SpringBootTestApp
 */
@Component
public class InitialDataLoader {

    @Value("${datasource.url}")
    private String DATASOURCE_URL;

    @PostConstruct
    private void LoadData() throws Exception {
        try {
            URL dataSourceURL = new URL(DATASOURCE_URL);
            for (Feature feature : parseHTML(dataSourceURL)) {
                featureDAO.create(feature);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Feature> parseHTML(URL url) throws IOException {
        List<Feature> features = new ArrayList<>();
        Document doc = Jsoup.parse(url, 3000);

        Element table = doc.select("tbody").get(0);
        Elements rows = table.select("tr");

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            String[] values = new String[cols.size()];
            for (int j = 0; j < cols.size(); j++) {
                values[j] = cols.get(j).text();
            }
            features.add(new Feature(values));
        }
        return features;
    }

    @Autowired
    private FeatureDAO featureDAO;
}
