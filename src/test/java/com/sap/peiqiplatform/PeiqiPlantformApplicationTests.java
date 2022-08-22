package com.sap.peiqiplatform;

import com.sap.peiqiplatform.entity.po.AppTable;
import com.sap.peiqiplatform.mapper.AppTableMapper;
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
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

@SpringBootTest
class PeiqiPlantformApplicationTests {

    @Autowired
    private AppTableMapper appTableMapper;

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

    @Test
    void contextInsertAppTable(){
        ArrayList<AppTable> list = new ArrayList<>();
        AppTable appTable = new AppTable();
        appTable.setApplicationName("IAClientNode");
        appTable.setCreateBy("Darry");
        list.add(appTable);
        AppTable appTable1 = new AppTable();
        appTable1.setApplicationName("Server");
        appTable1.setCreateBy("Darry");
        list.add(appTable1);
        AppTable appTable2 = new AppTable();
        appTable2.setApplicationName("WorkSpace");
        appTable2.setCreateBy("Darry");
        list.add(appTable2);

        appTableMapper.bulkInsertAppTable(list);

    }

}
