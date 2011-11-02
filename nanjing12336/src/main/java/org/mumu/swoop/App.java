package org.mumu.swoop;

import org.mumu.swoop.analysis.QaContentLink;
import org.mumu.swoop.analysis.QaListLink;
import org.mumu.swoop.analysis.def.DefQaContentLink;
import org.mumu.swoop.analysis.def.DefQaListLink;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        QaListLink qlink = new DefQaListLink();
        QaContentLink contentLink = new DefQaContentLink();
        //根据网站，最大330
        int maxPage = 2;
        //列表URL
        String tempPage = "http://218.94.69.68/njlt/indexDetailList.do?docType=1&taxType=all&targetPageNum=";
        List<Model> models = new ArrayList<Model>();
        for (int i = 1; i < maxPage; i++) {
            List<String> linkLists = qlink.getQaLinks(tempPage + i);
            //休息2秒 防止屏蔽
            TimeUnit.SECONDS.sleep(2);
            for (String link : linkLists) {
                System.out.println(link);
                models.add(contentLink.getContent(link));
                //休息5秒
                TimeUnit.SECONDS.sleep(5);
            }
        }

        for (Model model : models) {
            System.out.println("问题："+ model.getQuestion());
            System.out.println("回答："+ model.getAnswer());
            System.out.println("依据："+ model.getGistLink());
        }
    }
}
