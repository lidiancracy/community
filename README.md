> 2022  7/20

- 我多看看别人的项目怎么写的，慢慢学习，自己可能写几天写了几个静态页面，没什么实际意义. 先把演过放开. 项目有视频优先，有文档其次，啥都没有直接跳过
- 直接根据论坛项目[视频](https://www.bilibili.com/video/BV1r4411r7au?vd_source=afdbe5eeb7dd29283083f0417f15b5d0)

### 学习git托管代码

- Https每次push pull代码需要用户名密码 ，SSH方式不用 相当于电脑保存了

- 在idea终端 pwd，显示小蓝块名即可，git init 将项目变成git项目

- git status 查看git当前状态

- ```java
  git remote rm origin //删除一下，不然可能关联新仓库可能报错
  git config --global http.sslVerify "false" //删除身份认证
  // 然后复制命令就可以将一个我们本地项目导入github仓库了
      
  ```

- 我们新增一个文件后，可以查看git status 然后推送过去
  - ```java
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

  - ```html
      <link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/css/bootstrap.min.css">
      <script src="https://cdn.staticfile.org/jquery/3.2.1/jquery.min.js"></script>
      <script src="https://cdn.staticfile.org/popper.js/1.15.0/umd/popper.min.js"></script>
      <script src="https://cdn.staticfile.org/twitter-bootstrap/4.3.1/js/bootstrap.min.js"></script>
    ```

  - 组件[链接]([Dropdowns · Bootstrap v4 中文文档 v4.6 | Bootstrap 中文网 (bootcss.com)](https://v4.bootcss.com/docs/components/dropdowns/))

- 
