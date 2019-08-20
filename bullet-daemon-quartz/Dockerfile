FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER service@bingzo.cn

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /missile-daemon-quartz

WORKDIR /missile-daemon-quartz

EXPOSE 5007

ADD ./missile-visual/missile-daemon-quartz/target/missile-daemon-quartz.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar missile-daemon-quartz.jar
