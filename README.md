# vote
投票网站

该项目采用前后端分离，前端主要使用vue3.0以及ElementUI组件开发，后端使用了springboot+mybatis+redis，安全方面使用spring security框架对controller的方法进行权限控制以及用户密码加密
实现的功能有
* 用户登录和注册：通过security将/login和/regist开放所有权限，再通过security进行密码加密处理
* 管理员功能
  * 发布投票活动，对投票选项编辑，通知用户，用户管理如开放是否可以投票的权限
* 普通用户功能
  * 参与投票，查看投票过的活动，接收信息

项目部署在nginx上，利用Nginx的反向代理解决前端的跨域问题，也可以将后端项目运行到多台服务器进行负载均衡

