/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:QaContentLink.java  2011-11-02 下午10:00 poplar.yfyang ]
 */
package org.mumu.swoop.analysis;

import org.mumu.swoop.Model;

/**
 * <p>
 *  获取文件内容.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午10:00
 * @since JDK 1.5
 */
public interface QaContentLink {
    /**
     * 获取文件内容
     * @param contentUrl 文件内容URL
     * @return 文件内容对象
     */
    Model getContent(String contentUrl);
}
