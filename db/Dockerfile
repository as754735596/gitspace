FROM mysql:5.7

MAINTAINER bingzo(service@bingzo.cn)

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

COPY ./db/1schema.sql /docker-entrypoint-initdb.d

COPY ./db/2missile.sql /docker-entrypoint-initdb.d

COPY ./db/3missile_ac.sql /docker-entrypoint-initdb.d

COPY ./db/4missile_job.sql /docker-entrypoint-initdb.d

COPY ./db/5missile_mp.sql /docker-entrypoint-initdb.d

COPY ./db/6missile_config.sql /docker-entrypoint-initdb.d