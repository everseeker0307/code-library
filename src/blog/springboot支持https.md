# springboot支持https  
具体代码在me.wuhao.https目录下。

## 背景知识  
https的连接过程：客户端需要校验服务器端提供的证书是否有效，之后通过协商的密钥加密传输数据。  
https认证(ssl, tls)可以分为单向认证和双向认证。  
1、单项认证：服务器端不会校验客户端的来源。  
2、双向认证：客户端浏览器需要导入客户端证书，每次连接需要发送证书给服务器端进行校验，银行网银系统一般会采取双向认证。  

## springboot工程自建CA
```
keytool -genkey -alias tomcat -keypass 123456 -keyalg RSA -keysize 1024 -validity 365 -keystore tomcat_keystore.pfx -storepass 123456
```
通过jdk的keytool工具即可。-alias：别名；-keyalg：加密方式(指对会话密钥的加密)，默认RSA；-validity：证书有效期，默认90天。  
通过以上命令在服务器端生成证书，如果采用单项认证的话，对应的spring boot配置文件如下：  
```
server:
  ssl:
    key-store: src/main/resources/tomcat_keystore.pfx
    key-store-password: 123456
    key-password: 123456
    key-store-type: JKS
    key-alias: tomcat
    client-auth: want   # client-auth这个参数用来确定是否需要强制双向认证，want：提示；need：必须(即设置为need就不支持单项认证了)

```

## 测试方法
浏览器直接打开https://127.0.0.1:8443即可 或者 通过curl https://127.0.0.1:8443 -k命令。