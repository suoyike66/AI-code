package com.suoyike.aicodespringboot.service;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

@Service
public interface ProjectDownloadService {

    /**
     * 下载项目为 ZIP 文件
     *
     * @param projectPath       项目路径
     * @param downloadFileName  下载文件名
     * @param response          HTTP 响应
     */
    void downloadProjectAsZip(String projectPath, String downloadFileName, HttpServletResponse response);
}
