/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:Model.java  2011-11-02 下午9:53 poplar.yfyang ]
 */
package org.mumu.swoop;

import java.io.Serializable;

/**
 * <p>
 * 问答实体
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午9:53
 * @since JDK 1.5
 */
public class Model implements Serializable {
    private static final long serialVersionUID = 1857944412172005709L;
    /**
     * 问题
     */
    private String question;
    /**
     * 答案
     */
    private String answer;

    /**
     * 依据连接或者文字
     */
    private String gistLink;

    /**
     * 问题答案所对应的URL
     */
    private String qaLink;

    /**
     * 问题
     * @return 问题
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 问题
     * @param question 问题
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 答案
     * @return 答案
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 答案
     * @param answer 答案
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 依据连接或者文字
     * @return 依据连接或者文字
     */
    public String getGistLink() {
        return gistLink;
    }

    /**
     * 依据连接或者文字
     * @param gistLink 依据连接或者文字
     */
    public void setGistLink(String gistLink) {
        this.gistLink = gistLink;
    }

    /**
     * 依据连接或者文字
     * @return 依据连接或者文字
     */
    public String getQaLink() {
        return qaLink;
    }

    /**
     * 依据连接或者文字
     * @param qaLink 依据连接或者文字
     */
    public void setQaLink(String qaLink) {
        this.qaLink = qaLink;
    }
}
