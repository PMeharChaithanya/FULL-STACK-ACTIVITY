#!/bin/zsh
# Starts MySQL (if not already running) and runs the Student Management System
cd "$(dirname "$0")"
pgrep -x mysqld > /dev/null || (~/.local/mysql/bin/mysqld --basedir=$HOME/.local/mysql --datadir=$HOME/.local/mysql-data-parent/data --port=3306 --socket=/tmp/mysql.sock --mysqlx=OFF > /tmp/mysqld.log 2>&1 &) && sleep 3
~/.jdks/jdk-27+35/Contents/Home/bin/java -cp target/classes:$HOME/.m2/repository/com/mysql/mysql-connector-j/9.4.0/mysql-connector-j-9.4.0.jar com.example.jdbc.StudentManagementSystem
