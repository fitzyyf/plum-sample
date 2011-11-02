/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:DefQaListLinkTest.java  2011-11-02 下午10:16 poplar.yfyang ]
 */
package org.mumu.swoop.analysis.def;

import org.junit.Test;
import org.mumu.swoop.analysis.QaListLink;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午10:16
 * @since JDK 1.5
 */
public class DefQaListLinkTest {
    @Test
    public void testGetQaLinks() throws Exception {
        QaListLink qaListLink = new DefQaListLink();
        qaListLink.getQaLinks("http://218.94.69.68/njlt/indexDetailList.do?docType=1&taxType=all&targetPageNum=2");
    }

    @Test
    public void testTest() throws Exception {
        System.out.println(getUrlRegex("*detail/*/*.html"));
    }

    public static String getUrlRegex(String regex) {
           return regex.replaceAll("/", "\\/").replaceAll("\\.", "\\\\.").replaceAll("\\*", "[^&]*").replaceAll("\\?", "\\\\?");
       }

}
