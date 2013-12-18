/**
 * Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */
package com.donglin.ma.file.download;

import java.util.List;

/**
 * @author donglinma
 * 
 * @version $Id$ 2013年12月13日 上午11:36:23
 */
public interface ResultIterator {
    /**
     * 批量获取数据
     * 
     * @return
     */
//    Pair<String[], List<String[]>> getBatchData();

    /**
     * 是否有下一批数据
     * 
     * @return
     */
    boolean hasNext();
    
}
