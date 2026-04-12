package com.suoyike.aicodeapp.service;

import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.suoyike.aicodemodel.model.dto.app.AppAddRequest;
import com.suoyike.aicodemodel.model.dto.app.AppQueryRequest;
import com.suoyike.aicodemodel.model.entity.App;
import com.suoyike.aicodemodel.model.entity.User;
import com.suoyike.aicodemodel.model.vo.AppVO;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 * 应用 服务层。
 *
 * @author <a href="https://github.com/suoyike66">蓑衣客</a>
 * @since 2026-03-30
 */
public interface AppService extends IService<App> {

    /**
     * 获取应用封装类
     *
     * @param app
     * @return
     */
    AppVO getAppVO(App app);

    /**
     * 获取应用列表封装类
     *
     * @param appList
     * @return
     */
    List<AppVO> getAppVOList(List<App> appList);

    /**
     * 创建应用
     *
     * @param appAddRequest
     * @param loginUser
     * @return
     */
    Long createApp(AppAddRequest appAddRequest, User loginUser);

    /**
     * 构造应用查询条件
     *
     * @param appQueryRequest
     * @return
     */
    QueryWrapper getQueryWrapper(AppQueryRequest appQueryRequest);

    /**
     * 通过对话生成代码
     *
     * @param appId 应用id
     * @param message 对话内容
     * @param loginUser 登录用户
     * @return
     */
    Flux<String> chatToGenCode(Long appId, String message, User loginUser);

    /**
     * 部署应用
     *
     * @param appId 应用id
     * @param loginUser 登录用户
     * @return 可访问的部署地址
     */
    String deployApp(Long appId, User loginUser);

    /**
     * 异步生成应用截图
     *
     * @param appId 应用id
     * @param appUrl 应用地址
     */
    void generateAppScreenshotAsync(Long appId, String appUrl);
}