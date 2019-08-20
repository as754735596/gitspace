FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER service@bingzo.cn

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /missile-codegen

WORKDIR /missile-codegen

EXPOSE 5003

ADD ./missile-visual/missile-codegen/target/missile-codegen.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar missile-codegen.jar
