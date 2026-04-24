-- 修改 chat_history 表的 message 字段为 LONGTEXT 类型
-- 原因: AI 生成的完整代码可能超过 TEXT 类型的 64KB 限制
-- LONGTEXT 最大可存储 4GB 内容
ALTER TABLE chat_history MODIFY COLUMN message LONGTEXT NOT NULL COMMENT '消息';
