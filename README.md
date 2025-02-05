ms-books-payments


Comandos para crear la base de datos

windows

docker run -d --name mysql_payments  -e MYSQL_ROOT_PASSWORD=AdminRoot -e MYSQL_DATABASE=payments -e MYSQL_USER=usr_db_payments -e MYSQL_PASSWORD=book_2025 -p 3307:3306 -v D:\server\datamysql:/var/lib/mysql  --restart unless-stopped mysql:latest

