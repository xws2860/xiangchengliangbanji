# 使用OpenJDK 8作为基础镜像
FROM cjkang/java1.8

# 设置工作目录
WORKDIR /app

# 将宿主机中的所有文件拷贝到容器的/app目录中
COPY ./build/libs/xiangchengliangbanji-0.0.1-SNAPSHOT.jar xiangchengliangbanji-0.0.1-SNAPSHOT.jar

# 暴露8080端口
EXPOSE 8080

# 启动应用程序
CMD ["java", "-jar", "xiangchengliangbanji-0.0.1-SNAPSHOT.jar"]
