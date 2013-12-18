/**
 * Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */
package com.donglinma;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import com.google.common.base.Function;

/**
 * @author donglinma
 *
 * @version  $Id$ 2013年12月18日 下午2:40:05
 */
public class FileUtil {
    public static final Function<Integer, Integer> ADD_ONE = new Function<Integer, Integer>() {

        public Integer apply(Integer input) {
            return input + 1;
        }
    };
    public static final Function<Integer, Integer> MINUS_ONE = new Function<Integer, Integer>() {

        public Integer apply(Integer input) {
            return input - 1;
        }
    };

    /**
     * 根据浏览器agent编码文件名
     * 
     * @param agent
     * @param fileName
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String encodeFileName(String agent, String fileName) throws UnsupportedEncodingException {
        if (agent != null && agent.contains("MSIE")) {
            fileName = URLEncoder.encode(fileName, "UTF8");
            fileName = fileName.replace("+", "%20");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        return fileName;
    }

    /**
     * 修正cvs文件每行的行号
     * 
     * @param lines cvs数据文件行的集合
     * @param startIndex 数据行开始的行号
     * @param lineNumIndex 数据行号在line数组中的index位置
     * @param operation 应用于每一行的运算规则
     * @return 下一个可用行号
     */
    public static int fixCvsFileLineNumber(List<String[]> lines, int startIndex, int lineNumIndex,
            Function<Integer, Integer> operation) {
        Integer index = startIndex;
        for (String[] line : lines) {
            line[lineNumIndex] = String.valueOf(index);
            index = operation.apply(index);
        }

        return index;
    }
}
