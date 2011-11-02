/*
 * Copyright (c) 2010-2011 NOO. All Rights Reserved.
 * [Id:QaListLink.java  2011-11-02 下午10:00 poplar.yfyang ]
 */
package org.mumu.swoop.analysis;

import java.util.List;

/**
 * <p>
 * 分析列表页面获取QA连接的接口.
 * </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2011-11-02 下午10:00
 * @since JDK 1.5
 */
public interface QaListLink {
    /**
     * 根据列表页面获取对应问答的URL列表。
     * @param listUrl 列表URL
     * @return 对应问答的URL列表
     */
    List<String> getQaLinks(String listUrl);
}
