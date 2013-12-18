/**
 * Copyright (c) 2011 Qunar.com. All Rights Reserved.
 */
package com.donglin.ma.file.download;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import au.com.bytecode.opencsv.CSVWriter;

import com.donglinma.FileUtil;
//import com.qunar.base.meerkat.monitor.QMonitor;
//import com.qunar.hotel.settlement.provider.reconcile.common.MonitorConstant;

/**
 * @author donglinma
 * 
 * @version $Id$ 2013年12月18日 下午2:44:36
 */
public class DownloadCVSController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/downloadDetailFile")
    public void downloadDetailFile(HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "id") int id, @RequestParam(value = "totalItems") int totalItems) {

        logger.info("下载diffDetail文件：id={},totalItems={}", id, totalItems);

        /**
         * 设置response头信息
         */
        String charset = "utf-8";
        response.setCharacterEncoding(charset);
        String agent = request.getHeader("USER-AGENT");
        String fileName = "差异详情_" + id;
        try {
            fileName = FileUtil.encodeFileName(agent, fileName);
        } catch (UnsupportedEncodingException e1) {
            logger.error("生成文件名错误：id={}，{}", id, e1.toString());
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".csv");

        long start = System.currentTimeMillis();
        ResultIterator iter = null;//reconcileDiffQuery.downloadDiffDetailFile(id, totalItems);
        
        /**
         * CVS文件写入response流
         */
        boolean isFirst = true;
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(response.getWriter());
            while (iter.hasNext()) {
//                Pair<String[], List<String[]>> batchData = iter.getBatchData();
//                if (isFirst) {
//                    writer.writeNext(batchData.getKey());
//                    isFirst = false;
//                }
//                writer.writeAll(batchData.getValue());
//                writer.flush();
            }
        } catch (Exception e) {
            logger.error("输出下载文件失败：id={},totalItems={}, {}", id, totalItems, e);
//            QMonitor.recordOne(MonitorConstant.DOWNLOAD_DIFF_DETAIL_ERROR);
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logger.error("关闭csvWriter失败{}", e);
                }
            }
        }
//        QMonitor.recordOne(MonitorConstant.DOWNLOAD_DIFF_DETAIL_TIME, System.currentTimeMillis() - start);
    }

}
