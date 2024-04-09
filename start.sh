#!/bin/sh
echo ${VUE_APP_HOST}
ROOT_DIR=/app/client
# Replace env vars in files served by NGINX
for file in $ROOT_DIR/js/*.js* $ROOT_DIR/index.html;
do
  sed -i 's|%VUE_APP_HOST_PLACEHOLDER%|'${VUE_APP_HOST}'|g' $file
  # Your other variables here...
done

service nginx restart
java -jar ./server/todo_server.jar