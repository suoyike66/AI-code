package com.suoyike.aicodescreenshot.service.impl;

import com.suoyike.aicodeclient.innerservice.InnerScreenshotService;
import jakarta.annotation.Resource;

@DubboService
public class InnerScreenshotServiceImpl implements InnerScreenshotService {

    @Resource
    private ScreenshotService screenshotService;

    @Override
    public String generateAndUploadScreenshot(String webUrl) {
        return screenshotService.generateAndUploadScreenshot(webUrl);
    }
}