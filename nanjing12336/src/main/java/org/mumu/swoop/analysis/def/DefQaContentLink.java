/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:DefQaContentLink.java  2011-11-02 下午11:10 poplar.yfyang ]
 */
package org.mumu.swoop.analysis.def;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.mumu.swoop.Model;
import org.mumu.swoop.analysis.QaContentLink;
import org.mumu.swoop.util.HtmlDocumentUtil;

/**
 * <p>
 *  抓取内容实现.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午11:10
 * @since JDK 1.5
 */
public class DefQaContentLink implements QaContentLink {
    /**
     * 问题jsoup的表达式
     */
    private static final String CONTENT_SELECT = "div.main_contain div:eq(1)";
    /**
     * 回答jsoup的表达式
     */
    private static final String ANSWER_SELECT = "div.main_contain>p";
    /**
     * 依据jsoup的表达式
     */
    private static final String GIST_SELECT = "div.main_contain div:eq(4)";

    @Override
    public Model getContent(String contentUrl) {
        Document doc = HtmlDocumentUtil.urlToDocument(contentUrl);
        if (HtmlDocumentUtil.validateDocument(doc)) {
            Model model = new Model();
            model.setQaLink(contentUrl);
            //问题
            Elements elements = doc.select(CONTENT_SELECT);
            model.setQuestion(elements.get(1).text());

            //答案
            elements = doc.select(ANSWER_SELECT);
            model.setAnswer(elements.get(0).text());


            //依据
            elements = doc.select(GIST_SELECT);
            model.setGistLink(elements.get(0).text());
            return model;
        }
        return null;
    }
}
