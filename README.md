# hadoop_disk-last-
### **这个是我的毕业设计，基于hadoopd的云盘系统**

**技术选型：前端：layui layim css3  后台：springboot themleaf hdfs hbase 阿里druid mysql**

**开源框架：kkFileView（预览）layui、layim（前端）**



#### 本系统有一下的功能

###### 1.登录和注册

​     (1) 登录有找回密码（通过给邮箱发送验证码来实现 这里的验证输入的验证码是否正确通过md5加密和解密的） 和 滑块验证的功能（利用的是layui 的第三方组件sliderVerify组件）

​    (2)注册有验证用户名是否重复 ，邮箱格式是否正确 ，两次输入密码是否相同，邮箱是否被占用，还有上传头像的功能，再这里我采用的layui 的第三方组件step 可以分步式注册。

  （3）在注册时会给他分配一个根目录的标识，并且其密码用md5加密，在数据库存储的是加密后的字符串。

###### 2.websocket通信

   （1）websocket的前端用的是layim 具有聊天的功能支持单聊和群聊 并且在群聊支持文件库的功能，群成员可以将云盘的文件上传到文件库中。

   （2）还支持查找好友和群，以及的群和好友的管理。如果是群主有接受和拒绝的权力。

   （3）对其聊天记录加以保存，使其可以离线推送和查看历史记录。

###### 3.文件和文件夹的管理

   （1）将文件进行上传到hdfs和hbase，10Mb以下的文件用hbase进行存储，10mb以上的用hdfs存储。并且支持多文件上传和单文件上传，多文件上传的大小我限制为20mb，单文件则没有限制。

   （2）文件和文件夹有复制和移动和删除的功能。这里移动和复制时要事先查看是否可以移动，即是否移动和复制到他的子文件夹下。这样可以防止发生无限复制的情况的发生。

   （3）由于这里的功能太多我利用的是layui 的第三方组件dropdown组件进行将操作折叠在一起。

   （4）这里的删除其实是一种软删除，只是在数据库中的是否删除改为是。

   （5）文件的分享类型有共享和私有，当找资源时别人不会搜索到该文件。

###### 4.分享功能的管理

​     （1）对文件进行分享，有两种方式第一创建分享链接和分享到组的文件库。分享链接创建前有自动生成的提取密码（由javascript进行自动生成用的是math函数的random数生成0-25个数然后加上ASCII码）和有效时间，随即生成分享链接。

​      （2）可以对我的分享进行管理可以提前结束和延迟结束时间。

​      （3）从分享提取文件支持选择部分文件保存在那个文件夹下。

​       （4）文件的分享其实是一种硬链接的分享，在数据库有一张表记录着上传的位置和数量。每一次的分享的保存和文件的复制其数量都会加一。

###### 5.回收站和快速清理

​        （1）删除的文件会出现在回收站中，可以还原和彻底删除（这里的删除实际上是删除数据库的元组），并将保存上传的位置的表的对应数量减一。

​         （2）快速清理有三种清理类型清理空文件夹（只有子文件夹和文件都没有且回收站也没有才可以删除），删除重复文件（有可能指向同一个文件所以可以删除重复文件），删除垃圾文件（如果被管理员认为是垃圾文件且已经删除）

###### 6.找资源

​         支持根据文件名和文件类型查找全网的共享的文件。

###### 7.管理员的功能

​          （1）管理员可以删除hdoop 和 hbase里面没有人要的文件。

​          （2）管理员可以下载和预览上传到系统的文件，并且可以选择删除。这里的删除是彻底删除。



#### 关于hdoop和hbase的一些事

###### 1.我是在window上安装hdoop和hbase.安装方式是伪分布部署。

###### 2.关于hbase的rowkey的设计我采用md5散列算法取前4位做前缀后加上了时间戳。









