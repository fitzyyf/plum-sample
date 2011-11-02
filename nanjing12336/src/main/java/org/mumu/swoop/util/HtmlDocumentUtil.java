/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:HtmlDocumentUtil.java  2011-11-02 下午10:09 poplar.yfyang ]
 */
package org.mumu.swoop.util;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * <p>
 * HTML文档工具类.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午10:09
 * @since JDK 1.5
 */
public class HtmlDocumentUtil {


    /**
     * 根据URL和网页URL字符编码获取jsoup的文档类型
     *
     * @param url URL网页URL
     * @return jsoup文档
     */
    public static Document urlToDocument(String url) {
        try {
            Connection conn = Jsoup.connect(url);
            conn.timeout(15000);
            conn.userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.2) Gecko/2008070208 Firefox/3.0.1");
            conn.header("Accept-Language", "zh-cn,zh;q=0.5");
            conn.header("Accept-Charset", "GB2312,utf-8,GBK;q=0.7,*;q=0.7");
            return conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 验证抓取的内容是否有内容存在，如果title也不存在，则同样判断为没有抓取到内容.
     *
     * @param doc Jsoup的内容
     * @return 如果有内容返回true
     */
    public static boolean validateDocument(Document doc) {
        return doc != null && (!StringUtils.isEmpty(doc.title()));
    }
}
