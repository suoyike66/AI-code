package com.suoyike.aicodespringboot.service;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.service.IService;
import com.suoyike.aicodespringboot.model.dto.chathistory.ChatHistoryQueryRequest;
import com.suoyike.aicodespringboot.model.entiey.ChatHistory;
import com.suoyike.aicodespringboot.model.entiey.User;

import java.time.LocalDateTime;

/**
 * 对话历史 服务层。
 *
 * @author <a href="https://github.com/suoyike66">蓑衣客</a>
 * @since 2026-04-02
 */
public interface ChatHistoryService extends IService<ChatHistory> {

    /**
     * 添加用户消息
     *
     * @param appId       应用ID
     * @param message     消息内容
     * @param messageType 消息类型
     * @param userId      用户ID
     * @return 是否成功
     */
    boolean addChatMessage(Long appId, String message, String messageType, Long userId);

    /**
     * 删除应用关联的对话历史
     *
     * @param appId 应用ID
     * @return 是否成功
     */
    boolean deleteByAppId(Long appId);

    /**
     * 获取查询条件
     *
     * @param chatHistoryQueryRequest 查询条件
     * @return 查询条件
     */
    QueryWrapper getQueryWrapper(ChatHistoryQueryRequest chatHistoryQueryRequest);

    /**
     * 获取应用对话历史列表（分页）
     *
     * @param appId       应用ID
     * @param pageSize    页面大小
     * @param lastCreateTime 最后创建时间
     * @param loginUser   登录用户
     * @return 对话历史列表
     */
    Page<ChatHistory> listAppChatHistoryByPage(Long appId, int pageSize,
                                               LocalDateTime lastCreateTime,
                                               User loginUser);
}
