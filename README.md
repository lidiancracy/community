
> > 2022  7/20

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

- 成功获取用户信息啦！ 将固定信息放在配置文件里面 😎

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

#### 实现登录持久化 
宕机也不怕😎
我们首先需要知道，token相当于用户身份证<br>
我们甚至可以将浏览器中的cookie和jsession删除掉，这样可以重新回到登录页面.`response.addcookie`可以new一个cookie
然后向里面添加东西，这与网页无关，它永远保存在浏览器本体里面.`request.getcookie`则是可以获取浏览器中的cookie对象，cookie
不是可以setattr嘛，我们给他设置一个token相当于用户身份证,然后根据token找到user,在session 里面将用户对象设置成属性.
- 小细节 😡 我也在纳闷数据库和entity里面查询的时候 直接select * 这样行得通吗？毕竟mb又不知道数据库与实体的属性映射关系啊，果然只有同名的赋值了
其他的都为null

#### 实现发布页面
做完了静态页面 💗 用到了bootstrap,栅格布局太酷了
- 实现发布问题功能
p21 goodnight

> 2022/7/23
- taeget list
- [X] 实现publish功能
- [X] 信息提示 表单信息验证
---

今天来做问题发布功能,数据库有问题提交记录了，有个细节注意一下<br>
`return "redirect:/";`中间不能有空格，这个我以前还没注意过
- 遇到了一些问题，我使用model 不能在重定向页面后把值传过去，这我能理解.但是我用redirectattr传值
也为空 😷 有点没搞懂，但是model 设置值直接`return html` 可以把值传给页面
- 另外说说html的问题，我们习惯了th:text是王页面上加东西，但是比如input这样的输入框，你希望往输入框里面添加东西，是给他
设置`th:value`而不是`th:text`
- 接下來做的是完善index页面

> 2022/7/24 

bootstrap icon 需要导入`<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">`,这样才能
直接使用i标签

- 现有一个question类，但是我希望question表格和user表联动，于是我需要在question表上加一个user
对象了，但是model里面的表格是和数据库一一对应的，本身就很完美了，需要添加其他的我在dto上面拓展
- ok 👌 让我们重新理一下思路,我`直接`操作数据库表格是用mapper提供的方法，记住只用操作一个表，mapper可以提供多表查询
但现在有了更好的解决办法:
  - 我引入service层，service层就是用来引入多个单表mapper，从而实现多表联合查询，返回对象可以使dto，相当于是model的升级版
  这样就可以实现多表查询了，而且思路很流畅

- thymeleaf时间格式化`th:text=" ${#dates.format(question.createtime, 'yyyy/MM/dd HH:mm')}"`
- 实现了index展示功能,tabnine不好用，卸载了
- 使用debug确实能快速找bug，以后多用用
- 下一步是做一个分页操作，视频讲了一个半小时我就直接用插件了
  - ```<!--        分页-->
    <dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper-spring-boot-starter</artifactId>
    <version>1.2.13</version>
    </dependency>```
    ```
    ```
    spring:
    main:
    allow-circular-references: true
#### @RequestParam
- 这里说明一下`public String index(@RequestParam(required = false,defaultValue = "1",value = "pagenum")int pagenum`中注解的作用
这种注解一般用于页面向控制层传递参数，不加注解必须前后端变量名一致才能传成功,加了可以自己设置映射,同时加了则get请求必须传参数(也可以设置`required`)
#### @RequestBody
- 用于控制层接收post请求

- 分页还没弄好，好烦，我觉得应该是分页start那里的问题 😠  明天再处理吧 今天就到这里了，再写就不礼貌了
