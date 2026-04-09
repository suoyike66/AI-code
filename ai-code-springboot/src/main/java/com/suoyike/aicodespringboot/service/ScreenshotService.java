package com.suoyike.aicodespringboot.service;

public interface ScreenshotService {
    /**
     * 通用的截图服务，可以得到访问地址
     *
     * @param webUrl 网页URL
     * @return 截图上传的URL
     */
    String generateAndUploadScreenshot(String webUrl);
}
