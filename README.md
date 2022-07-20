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

