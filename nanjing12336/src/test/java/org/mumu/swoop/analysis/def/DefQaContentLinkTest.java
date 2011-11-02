/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:DefQaContentLinkTest.java  2011-11-02 下午11:21 poplar.yfyang ]
 */
package org.mumu.swoop.analysis.def;

import org.junit.Test;
import org.mumu.swoop.Model;
import org.mumu.swoop.analysis.QaContentLink;

/**
 * <p>
 * .
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午11:21
 * @since JDK 1.5
 */
public class DefQaContentLinkTest {
    @Test
    public void testGetContent() throws Exception {
        QaContentLink contentLink = new DefQaContentLink();
       Model model = contentLink.getContent("http://218.94.69.68/njlt/viewDetail.do?docType=1&docId=AF101BF271FD6148E0438D14C8026148");
        System.out.println(model.getAnswer());
        System.out.println("q:" + model.getQuestion());
        System.out.println("g:"+model.getGistLink());
    }
}
