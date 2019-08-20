FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER service@bingzo.cn

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /missile-upms

WORKDIR /missile-upms

EXPOSE 4000

ADD ./missile-upms/missile-upms-biz/target/missile-upms-biz.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar missile-upms-biz.jar
