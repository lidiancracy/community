> 2022  7/20

- 我多看看别人的项目怎么写的，慢慢学习，自己可能写几天写了几个静态页面，没什么实际意义. 先把演过放开. 项目有视频优先，有文档其次，啥都没有直接跳过
- 直接根据论坛项目[视频](https://www.bilibili.com/video/BV1r4411r7au?vd_source=afdbe5eeb7dd29283083f0417f15b5d0)

### 学习git托管代码

Https每次push pull代码需要用户名密码 ，SSH方式不用 相当于电脑保存了

在idea终端 pwd，显示小蓝块名即可，git init 将项目变成git项目

git status 查看git当前状态

```java
  git remote rm origin //删除一下，不然可能关联新仓库可能报错
  git config --global http.sslVerify "false" //删除身份认证
  // 然后复制命令就可以将一个我们本地项目导入github仓库了
      
  ```

我们新增一个文件后，可以查看git status 然后推送过去
```java
    // 命令是
    git add . 
    git commit -m "sth"
    git push 是提交到远程仓库
```

> 2022/7/21

### bootstrap

- 使用的版本和视频一样 3.4版本，有点老了，最新的5都出了

- 没有用cdn的导入方式，先下载了bs相关包再导入，但导入的时候路径不能从css开始，感觉很奇怪 :question:

- 结果测试可以用4版本，cdn导入

  ```html
      <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
      <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
      <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
      <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    ```


  - 组件[链接](https://v4.bootcss.com/docs/components/dropdowns/)
  


### github登录授权

ctrl + P是参数提示


先创键一个github授权 [API](https://github.com/settings/developers)


给我们的登录按钮绑定跳转到 github[授权网站](https://github.com/login/oauth/authorize) ,需要携带一些参数


```html
      需要携带 
      client_id  在我们注册的API上
      redirect_uri 授权完成后跳转回来的网页（也是在API上），
      我们直接用本机，因为没有部署服务器
      <a class="nav-link" 
      href="https://github.com/login/oauth/authorize?client_id=f4ae598eeffaf516d993
      &redirect_uri=http://localhost:8080/callback"&scope=user&state=1>登录</a>
```

```
    需要携带 
    client_id  在我们注册的API上
    redirect_uri 授权完成后跳转回来的网页（也是在API上），我们直接用本机，因为没有部署服务器
     <a class="nav-link" href="https://github.com/login/oauth/authorize?client_id=f4ae598eeffaf516d993&redirect_uri=http
     ://localhost:8080/callback"&scope=user&state=1>登录</a>               
```

- github账户密码授权成功后会返回一个code，下一步需要做的是调用access_token接口携带code,获取accesstoken，在根据accesstoken发送请求获取用户信息

- 两次请求涉及两个访问网站

  - ```java
    url("https://github.com/login/oauth/access_token") 这个是post提交，所以不用跟参数  获取token
    url("https://api.github.com/user?access_token="+token).header("Authorization","token "+token) 
    这个是get提交 要加参数获取user info
    ```


- 成功获取用户信息啦！ 将固定信息放在配置文件里 

- 成功获取用户信息啦！ 将固定信息放在配置文件里面 :smile:

### Cookie 和 Session
cookie 相当于银行卡，在同一个浏览器登录成功后，浏览器就会将你的身份记住了，除非你主动提出消除cookie记录或者换浏览器登录（去其他银行）
<br>我们return index页面网址会显示最终的网址,后面可能跟一堆? &之类的，我们重定向更加简约
<br>if 逻辑可以直接写在html页面上，页面也可以直接获取cookie，因为cookie这东西还想就是给页面用的
<br>目前实现了登录功能，但是每次服务器重启，所有用户“被迫下线”，我们不希望这样，所以需要借助数据库

> 2022/7/22
#### 实现点击数据导入数据库
- 导入依赖
- 用idea自带的数据库连接器，进行连接，connected type选成`embedded`
- url连接需要加上 jdbc:h2:~/community`;OLD_INFORMATION_SCHEMA=TRUE`
- 设置了一个新的快捷键 `shift + f `快速查找文件
- 没有使用h2数据库了 😃,使用我熟悉的mysql数据库
- 我可以在idea上面加入emoji啦 [插件](Yet another emoji support)
- 操作数据库使用mybatis+mapper，真的改了好久，然后主键是自增的
