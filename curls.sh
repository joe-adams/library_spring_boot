#!/bin/bash
curl -X GET -i http://localhost:8080/books/title/dog
curl -X GET -i http://localhost:8080/books/author/smith

curl -X POST -i http://localhost:8080/books -H "Content-Type: application/json" --data '{
  "title":"Great book",
  "author":"dave"
}'

curl -X POST -i http://localhost:8080/books -H "Content-Type: application/json" --data '{
  "title":"Great book 2",
  "author":"dave"
}'