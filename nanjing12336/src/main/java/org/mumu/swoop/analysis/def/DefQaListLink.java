/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:DefQaListLink.java  2011-11-02 下午10:03 poplar.yfyang ]
 */
package org.mumu.swoop.analysis.def;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.mumu.swoop.analysis.QaListLink;
import org.mumu.swoop.util.HtmlDocumentUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;

/**
 * <p>
 * 默认获取问答连接的实现类.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午10:03
 * @since JDK 1.5
 */
public class DefQaListLink implements QaListLink, Serializable {
    private static final long serialVersionUID = -1308763765184468733L;

    private static final Logger logger = Logger.getLogger("DefQaListLink");

    /**
     * http://218.94.69.68/njlt/indexDetailList.do?docType=1&taxType=all&targetPageNum=2
     * 南京网站中的文档URL规则
     */
    private static final String NANGJING_QA_LINK_RULE = "viewDetail.do?docType=";

    @Override
    public List<String> getQaLinks(String listUrl) {
        logger.info("查询：" + listUrl);
        Set<String> urlLinks = new HashSet<String>();
        Document doc = HtmlDocumentUtil.urlToDocument(listUrl);
        if (HtmlDocumentUtil.validateDocument(doc)) {
            Elements links = doc.select("a[href]");
            String parseUrl;
            try {
                for (Element link : links) {
                    parseUrl = link.attr("abs:href").trim();
                    if(StringUtils.isEmpty(parseUrl))
                        continue;
                    if (parseUrl.contains(NANGJING_QA_LINK_RULE)) {
                        urlLinks.add(parseUrl);
                    }
                }
            } catch (PatternSyntaxException e) {
                return new ArrayList<String>(urlLinks);
            }

        }
        return new ArrayList<String>(urlLinks);
    }

}
