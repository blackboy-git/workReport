
<div style="display: flex; align-items: center; gap: 20px;">
  <img src="src\assets\logo.pngg" alt="" style="height: 100px;"/>
  <div style="margin: 0;">
    <h1 style="margin: 0;">通联世界工作周报系统</h1>
  </div>
</div>

<font style="color:#666;"></font>

## 🌟 系统介绍
# SmartWorkReport
SmartWorkReport 是一款我首次尝试采用LLM辅助开发的工作周报系统，具有周报管理的常用功能，如个人周报、组内周报、定时任务、引用上周周报数据、周报统计、打印等，采用前后端分离设计，后端为springboot3，前端为vue3，这里是后端，前端点击这里：https://github.com/blackboy-git/workReport-vue.git

### ✨ 主要特性
+ 🤖 **功能完善** - 支持工作周报管理的常用功能；
+ 📊 **配置简单** - 设定分组、分组计划启用简单方便，支持最近周报预览
+ 📝 **统计分析** - 支持统计用户周报完成情况，及时提醒；
+ 🎨 **编辑功能** - 支持富文本功能
+ 💾 **本地数据存储** - 所有数据本地存储，安全可靠
+ 🌈 **界面美观** - 现代化浅色主题 UI 设计
+ 🌈 **双重加密** - 密码双重加密，防止泄露

## 🌟 安装部署

### Linux 系统
1. 安装java17、mysql8、nginx；
2. 在mysql8中创建workreportdb数据库，并导入workreportdb.sql文件创建数据表；
3. 创建一个文件夹，用于存放前后端文件，如:
+ /home/workReport：存放后端jar包，workreportdb.sql文件
+ /home/workReport-ui：存放前端文件，如：assets、index.html等；
4. 将后端jar包执行java -jar workReport.jar即可（如需要后台运行，执行以下命令（默认启用端口是8088）：
```shell
#后台运行
nohup java -jar /home/workReport/workReport.jar > /home/workReport/workReport-log.out  2>&1 &
```
5. 修改nginx配置文件，将默认目录录指向前端文件/home/workReport-ui，默认文件为index.html，并重启nginx即可。

## 🛠️ 默认账号
账号admin，密码：Admin@2025

## 🛠️ 使用步骤
1. 用admin登录系统；
2. 点击分组管理，创建分组，定义分组周报生成的周期、开放填写的天数（周报开启后多少天内允许编辑）；
3. 点击用户管理，创建用户，然后在分组管理中把成员加入到相应的分组；
4. 周报会按照分组定义的计划，定时生成，只有在计划每个周期开始当前会生成，其余时间不会，比如：计划时间是3月30日，周期是7天，就第一次生成，就是3月30日晚上12点，第二次生成就是4月6日晚上12点。
5. 每个用户在周报开放期间登录系统，即可编辑周报，并在分组周报中查看自己所在组的全部周报。

## 🛠️ 技术栈

<div align="center" style="width: 100%; max-width: 800px; margin: 0 auto;">

| 🏗️ 框架 | 🎨 UI | 🔧 工具 |
|:-------------:|:-------------:|:-------------:|
| ![Springboot](https://img.shields.io/badge/Springboot-brightgreen?logo=springboot)| ![Vue.js](https://img.shields.io/badge/-Vue.js-4FC08D?style=flat-square&logo=vue.js&logoColor=white) | ![Vite](https://img.shields.io/badge/-Vite-646CFF?style=flat-square&logo=vite&logoColor=white) |
| ![JavaScript](https://img.shields.io/badge/-JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black) | ![Element Plus](https://img.shields.io/badge/-Element%20Plus-409EFF?style=flat-square&logo=element&logoColor=white) | ![Security](https://img.shields.io/badge/springsecurity-brightgreen?logo=springsecurity) |
| ![Vue Router](https://img.shields.io/badge/-Vue%20Router-42B983?style=flat-square&logo=vue.js&logoColor=white) | ![Photoshop](https://img.shields.io/badge/Photoshop-brightgreen?logo=Photoshop) | ![Gitlab](https://img.shields.io/badge/Gitlab-brightgreen?logo=Gitlab) |
| ![Vuex](https://img.shields.io/badge/-Vuex-42B983?style=flat-square&logo=vue.js&logoColor=white) | ![Axios](https://img.shields.io/badge/Electron%20Store-brightgreen?logo=Electron%20Store) | ![Axios](https://img.shields.io/badge/Axios-brightgreen?logo=Axios) |

</div>

### 主要依赖
+ **框架核心**
    - Springboot - 后端应用开发框架
    - Vue 3 - 渐进式 JavaScript 框架
    - Vue Router - 官方路由管理器
    - Vuex - 状态管理模式和库
+ **UI 组件**
    - Element Plus - 基于 Vue 3 的组件库
    - Photoshop - 前端页面元素设计
    - Electron Store - 数据持久化存储
    - wangeditor - 富文本编辑器
+ **工具库**
    - Vite - 现代前端构建工具
    - Gitlab - 私有化部署操作库
    - Git - 公共仓库
    - Axios - HTTP 客户端
    - bcrypt - 密码加密库

## 🌟 系统效果
1. 登录页面
![alt text](image.png)
2. 首页
![alt text](image-1.png)
3. 我的周报
![alt text](image-2.png)
4. 组内周报
![alt text](image-9.png)
5. 组内周报明细
![alt text](image-4.png)
6. 个人设置
![alt text](image-5.png)
7. 用户管理
![alt text](image-6.png)
8. 分组管理
![alt text](image-7.png)
9. 分组周报定时计划配置
![alt text](image-8.png)
10. 周报开放日期允许编辑
![alt text](image-3.png)

## 🤝 参与贡献
欢迎参与项目贡献！

## 📄 开源协议
[MIT License](https://github.com/Estelle925/SmartBrief/blob/main/LICENSE) © 2024 

---

<p align="center" style="margin-top: 40px; color: #666;">
   <b>用 ❤️ 打造</b>
</p>

