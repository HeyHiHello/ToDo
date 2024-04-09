FROM node:lts-slim as build-stage
WORKDIR /app
COPY ./todo_client/package*.json .
RUN npm install
COPY ./todo_client/ .
RUN npm run build


FROM ubuntu:latest as production-stage

RUN apt update -y && apt upgrade -y
RUN apt install openjdk-21-jdk -y

WORKDIR /app

COPY --from=build-stage /app/dist client

COPY ./todo_server/target/todo_server-0.0.1-SNAPSHOT.jar ./server/todo_server.jar

RUN apt install nginx -y
COPY ./nginx.conf /etc/nginx/nginx.conf
COPY ./start.sh ./start.sh

EXPOSE 80

CMD [ "sh", "./start.sh" ]



# FROM nginx:stable-alpine as production-stage
# COPY --from=build-stage /app/dist /usr/share/nginx/html
# EXPOSE 80
# CMD ["nginx", "-g", "daemon off;"]