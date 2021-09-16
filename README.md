# neighbor-back
backend java mysql
services:
  db:
    platform: linux/x86_64
    image: mysql:5.7
    ports:
      - "3306:3306"
    restart: always
    environment:
      - MYSQL_ALLOW_EMPTY_PASSWORD="true"
    container_name: mysql5.7

touch docker-compose.yml
open docker-compose.yml 
Add the above
docker-compose up -d 
docker exec -it 1d7aba917169(image id
docker exec -it  mysql5.7 bash            enter mysql terminal

create root user
create database grassp
create user 'grassp_dev' identified by 'abcd1234';
grant all privileges on grassp.* to 'grassp_dev';
(after forward engineering from mysql workbench)
use grassp;
copy and paste contents of grassp_client_provisioning.sql
or instead of MySQL Workbench, if useful, get a copy of grassp prod data



sudo nano /etc/hosts
127.0.0.1 grassp-dev.local

Host File - get the IP address of the docker server
Launch Terminal.
Type sudo nano /etc/hosts and press Return.
Enter your admin password.

https://stackoverflow.com/questions/41637013/docker-mysql-on-different-port/41637060
https://hub.docker.com/_/mysql/
docker run --name mysqllatest1 -e MYSQL_ROOT_PASSWORD=password -d mysql

docker run --detach --name=mysqllatest -p 52000:3306 --env="MYSQL_ROOT_PASSWORD=password" mysql



docker pull --platform linux/amd64 mysql
docker exec -it 0716d6ebcc1a bash

Install homebrew and that gets git




mysql -u root -p


create user 'neighbor_dev' identified by 'abcd1234';

create database neighbor;

grant all privileges on neighbor.* to 'neighbor_dev';

