FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER service@bingzo.cn

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /missile-auth

WORKDIR /missile-auth

EXPOSE 3000

ADD ./missile-auth/target/missile-auth.jar ./

CMD sleep 30;java -Djava.security.egd=file:/dev/./urandom -jar missile-auth.jar
