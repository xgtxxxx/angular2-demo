
# angular2 demo
## 1. 如果只需要看web前端效果，可以把restful.service.ts文件里面的isDemo修改为true，这样就可以直接获取mock数据而不需要发送http请求了。
## 2. 如果前后端需要联调使用，则把isDemo修改成true，并且需要安装redis，并且在applicationContext.xml里面和redis.properties里面配置redis信息。
## 3. 安装nodeJs
## 4. 在odoo-web的根目录运行npm install。
## 5. 如果需要联调，则在api根目录运行mvn clean package, 然后把target目录下面的odoo-1.0-SNAPSHOT.war修改成odoo.war, 并发布到tomcat下面启动应用。如果只看web效果，跳过此步。
## 6. 在odoo-web根目录运行npm start，启动web。
## 7. 在浏览器地址栏输入http://localhost:3000?account=yourname&pwd=yourpassword

## 注：如果需要了解用webpack打包angular2项目，可以参考branch webpackNg2Demo.<url>https://github.com/xgtxxxx/angular2-demo/tree/webpackNg2Demo</url>

#如果有朋友对此感兴趣，希望可以分享讨论
