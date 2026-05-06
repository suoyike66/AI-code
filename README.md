# 🤖 AI 零代码生成平台（AI Zero-Code Generator）

> **一句话轻松创建网站应用** —— 基于 Spring AI 与 Vue 3 的全栈 AI 代码生成平台

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)](https://spring.io/projects/spring-boot)
[![Vue](https://img.shields.io/badge/Vue-3.x-4FC08D)](https://vuejs.org)
[![LangChain4j](https://img.shields.io/badge/LangChain4j-0.36-blue)](https://docs.langchain4j.dev)
[![License](https://img.shields.io/badge/license-MIT-blue)](LICENSE)

---

## ✨ 核心功能

| 功能模块 | 说明 |
|---------|------|
| 🧠 **智能代码生成** | 支持 `HTML`、`多文件（HTML+CSS+JS）`、`Vue 工程` 三种模式，AI 自动识别需求并生成完整代码 |
| 💬 **对话式交互** | 多轮对话，基于历史上下文持续优化代码；支持游标分页加载历史记录 |
| 🔧 **AI 工具体系** | 文件读写/修改/删除、目录读取等工具，AI 可自主调用完成复杂任务 |
| 🌐 **在线预览与部署** | 代码生成后即时预览（iframe 内嵌），支持一键部署并生成独立访问链接 |
| ✏️ **可视化编辑** | iframe 内元素悬浮高亮、点击选中，配合对话精准修改页面细节 |
| 📦 **代码下载** | 支持将完整项目打包为 ZIP 下载 |
| 🛡️ **安全护轨** | 输入恶意检查（防注入/越狱），输出敏感内容过滤 |
| ⚡ **限流控制** | 基于 Redisson 的分布式限流，支持用户/IP/接口级别 |
| 📊 **监控指标** | AI 模型调用次数、Token 消耗、响应时间等指标收集（Micrometer） |
| 👑 **管理后台** | 用户管理、应用管理、对话历史管理、精选应用设置 |
| 🖼️ **截图生成** | 部署后自动使用 Selenium 生成网页截图作为封面 |
| 🧩 **LangGraph4j 工作流** | 完整工作流引擎：图片收集 → 提示词增强 → 智能路由 → 代码生成 → 质量检查 → 项目构建 |

---

---

## 🚀 快速开始

### 环境要求

- **后端**：JDK 21+、Maven 3.8+、MySQL 8.0+、Redis 7+
- **前端**：Node.js 18+、pnpm / npm
- **AI 服务**：OpenAI 兼容 API（可配置 base-url）
- **ChromeDriver**（可选，用于截图功能）

### 后端启动

```bash
# 1. 克隆项目
git clone https://github.com/your-username/ai-code-platform.git
cd ai-code-platform/ai-code-springboot

# 2. 初始化数据库
# 执行 sql/init.sql 建库建表（见后端文档中的建表语句）

# 3. 修改配置文件
# src/main/resources/application.yml 中配置：
#   - 数据库连接（MySQL）
#   - Redis 连接
#   - AI 模型 API Key/Base URL/Model Name
```

### 前端启动

```# 安装依赖
pnpm install   # 或 npm install

# 修改环境变量（可选）
# .env 文件中配置 VITE_API_BASE_URL（后端地址）

# 启动开发服务器
pnpm dev       # 或 npm run dev
```
### 🔄 核心流程
```
用户输入需求（如"创建个人博客"）
        │
        ▼
┌──────────────────┐
│  AI 智能路由      │  → 判断生成类型：HTML / 多文件 / Vue 工程
└──────┬───────────┘
        │
        ▼
┌──────────────────┐
│  对话式代码生成    │  ← 多轮对话，AI 调用工具写文件
└──────┬───────────┘
        │
        ▼
┌──────────────────┐
│  流式 SSE 输出    │  → 前端实时渲染 Markdown + 代码块
└──────┬───────────┘
        │
        ▼
┌──────────────────┐
│  代码解析与保存   │  → 持久化到本地文件系统
└──────┬───────────┘
        │
        ▼
┌──────────────────┐
│  iframe 即时预览  │  ← 用户可进入"编辑模式"选中元素精准修改
└──────┬───────────┘
        │
        ▼
┌──────────────────┐
│  一键部署 / 下载  │  → Vue 项目先 npm build，再复制到部署目录
└──────────────────┘
```
### 🛠️ 技术亮点
#### 1. Spring AI + LangChain4j 深度集成
使用 @SystemMessage、@UserMessage、@MemoryId 等注解简化 AI 服务定义

自定义 InputGuardrail 防止提示词注入攻击

多模型配置：路由模型、流式模型、推理模型分别配置，支持多例模式解决并发问题

#### 2. AI 工具体系
提供文件写入、读取、修改、删除、目录读取等工具

AI 可自主调用工具完成代码修改，减少全量重新生成

ToolManager 统一管理，BaseTool 抽象基类提供模板方法

#### 3. 流式 SSE 响应
使用 EventSource 接收流式数据，前端逐字展示 AI 回复

支持 business-error 事件，流未中断时也能优雅提示错误

#### 4. LangGraph4j 工作流
完整工作流：图片收集→提示词增强→路由→代码生成→质检→构建

支持并发图片收集（Pexels、Undraw、Mermaid、DashScope Logo）

质检失败自动循环修复

#### 5. iframe 可视化编辑
注入脚本实现元素悬浮高亮、点击选中

通过 postMessage 与父页面通信，将选中元素信息传入对话上下文

实现"所见即所得"的精准修改

### 📊 效果展示
1、应用生成
<img width="1843" height="839" alt="屏幕截图 2026-05-01 194016" src="https://github.com/user-attachments/assets/0ab4bb6f-780a-4601-ba46-686b6455ca49" />
2、应用部署
<img width="885" height="640" alt="屏幕截图 2026-05-01 194217" src="https://github.com/user-attachments/assets/7260fb05-9fd9-4bcc-8712-372c39f05af3" />
3、可视化修改
<img width="1857" height="854" alt="屏幕截图 2026-05-01 194117" src="https://github.com/user-attachments/assets/aaea8e8f-71ce-4927-aeed-1743a0b5d89b" />
