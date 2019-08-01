[![码云Gitee](https://gitee.com/binary/weixin-java-mp-demo-springboot/badge/star.svg?theme=blue)](https://gitee.com/binary/weixin-java-mp-demo-springboot)
[![Github](http://github-svg-buttons.herokuapp.com/star.svg?user=binarywang&repo=weixin-java-mp-demo-springboot&style=flat&background=1081C1)](https://github.com/binarywang/weixin-java-mp-demo-springboot)

[![Build Status](https://travis-ci.org/binarywang/weixin-java-mp-demo-springboot.svg?branch=master)](https://travis-ci.org/binarywang/weixin-java-mp-demo-springboot)
-----------------------

# Spring Boot微信开发，实现了简单的消息处理、生成二维码及扫码事件处理（基于SDK-WxJava）

### 本 Demo 基于 Spring Boot 构建，实现微信公众号后端开发功能。

### 本项目修改自 WxJava 的 Demo 演示程序 weixin-java-mp-demo-springboot，更多官方 Demo 请[查阅此处](https://github.com/Wechat-Group/WxJava/blob/master/demo.md)。

> [WxJava](https://github.com/Wechat-Group/WxJava) 是业界一款广受好评的 Java 微信开发 SDK，本 Demo 旨在创建一个快速可运行的微信公众号服务，降低入门门槛，让初学者们快速体会到使用 WxJava 的方便之处。

#### 如有问题请[【在此提问】](https://github.com/binarywang/weixin-java-mp-demo-springboot/issues)。
## 使用步骤：
1. 请注意，本 demo 为简化代码编译时加入了 lombok 支持，如果不了解 lombok 的话，请先学习下相关知识，比如可以阅读[此文章](https://mp.weixin.qq.com/s/cUc-bUcprycADfNepnSwZQ)；
1. 另外，新手遇到问题，请务必先阅读[【开发文档首页】](https://github.com/Wechat-Group/WxJava/wiki)的常见问题部分，可以少走很多弯路，节省不少时间。
1. 配置： `/src/main/resources/application.yml` 
```
wx:
  mp:
    configs:
      - appId: 1111 （一个公众号的appid）
        secret: 1111（公众号的appsecret）
        token: 111 （接口配置里的Token值）
        aesKey: 111 （接口配置里的EncodingAESKey值）
      - appId: 2222 （另一个公众号的appid，以下同上）
        secret: 1111
        token: 111
        aesKey: 111
```
3. 运行Java程序：`WxMpDemoApplication`；
4. 配置微信公众号中的接口地址：http://公网可访问域名/wx/portal/xxxxx （注意，xxxxx为对应公众号的appid值）；
5. 根据自己需要修改各个handler的实现，加入自己的业务逻辑。
	



## 环境搭建教程

#### 环境准备

1. 到微信公众平台申请测试号一个，[申请地址](https://developers.weixin.qq.com/sandbox)

2. 不需要服务器，用外网映射工具，本次使用 `Sunny-Ngrok`

   > 这个映射工具在开发过程起到非常重要的作用，因为微信服务器是在外网的，我们本地开发的代码是部署在本地 tomcat 服务器的，本地tomcat是不能访问外网的微信服务器的，所以要借助 Ngrok 这个工具把我们本地 ip 映射到外网，这样本地代码就可以跟微信服务器交互了，这样调试代码就非常方便了。
   >
   > Ngrok 是外国的一款软件，服务器在美国，正常情况下，国内网络也是不能访问的(被墙了，大家都懂的)。在这里真的非常感谢大神 Sunny 把服务器部署到了香港，防止被墙让我们能够访问美国 Ngrok 服务器。更重要的是**免费使用啊!免费使用啊!免费使用啊!**

3. 开发工具，本次使用并将长期使用 `IntelliJ IDEA`

#### 部署 Ngrok

1、下载Sunny-Ngrok客户端：https://www.ngrok.cc/

2、注册Sunny-Ngrok后台管理账号：https://www.ngrok.cc/login/register

3、登录之后开通隧道：隧道名字随便填;前置域名就是你要映射的外网地址;本地端口就是本地ip:127.0.0.1+tomcat端口号：8080(本地的都可以修改，但是微信只能访问80和443端口，所以最好用默认的，不要修改)http验证用户名和密码不要填，因为到时这个地址要部署到微信后台的，设置之后微信服务器就不能访问这个映射地址了，微信服务器不知道你的用户名和密码。

![这里写图片描述](img\1.bmp)

![这里写图片描述](img\2.bmp)

4、本地运行Sunny-Ngrok启动工具.bat，将隧道id复制到启动工具，这样本地ip就映射到外网了。

![这里写图片描述](https://www.2cto.com/uploadfile/Collfiles/20161006/201610061033231149.png)

![](img\success-ngrok.bmp)



#### Clone我的代码，并运行起来[代码下载地址](https://github.com/DavidWhom/weixin-java-mp-demo-springboot)



#### 配置微信测试号页面

1. 项目成功运行后，并且用 Ngrok完成外网映射后才能填写接口配置信息

   ![1564133645425](img\1564133645425.png)

**注意：配置微信公众号中的接口地址：http:// ngrok分配给你的域名/wx/portal/xxxxx （注意，xxxxx为对应公众号的appid值）；**

​	点击提交，微信后台完成接口校验。

2. 扫码关注你的测试公众号进行功能验证

#### 实现效果

目前仅实现了简单的消息处理和扫码demo

![](img\demo.jpg)



#### 欢迎交流，微信：weinixieshi210（备注：WxJava）

<p align="center">
    <img src="https://github.com/DavidWhom/recruit/raw/master/img/weixin.jpg" width="250px" height="250px" style="text-align: center;"/>
</p>



#### 撸码不易，如果对你有所帮助，欢迎您的赞赏(1分钱就行，让我知道我写这些东西有用)

<p align="center">
    <img src="img/money-qrcode.jpg"/>
</p>



