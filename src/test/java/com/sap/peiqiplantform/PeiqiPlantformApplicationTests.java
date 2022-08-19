package com.sap.peiqiplantform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.util.Objects;

@SpringBootTest
class PeiqiPlantformApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
        String object = restTemplate.getForObject("https://int.repositories.cloud.sap/artifactory/build-snapshots/com/sap/it/spc/ianode/ianode.cf.assembly/", String.class);
//        System.out.println(object);

        Document doc = Jsoup.parse(object);
        Elements elements = doc.getElementsByTag("a");
//        System.out.println(elements);
        for (Element element : elements) {
            if (element.hasText() && element.text().startsWith("1.")){
                System.out.println(element.text());
            }
        }
    }

    @Test
    void contextDownFileByUrl() throws Exception {
        URL url = new URL("https://int.repositories.cloud.sap/artifactory/build-snapshots/com/sap/it/spc/ianode/ianode.cf.assembly/1.75.0-SNAPSHOT/ianode.cf.assembly-1.75.0-20220803.072412-2.mtar");
        ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("file.mtar"))) {
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        }
    }

    public static void main(String[] args) {
        String s1 = null;
        String s2 = "mm";
        System.out.println(Objects.equals(s1,s2));
    }

}
