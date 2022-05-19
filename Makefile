build:
	mvn clean package spring-boot:repackage

image:
	docker build -t pizza-lab:latest .

push:
	docker push pizza-lab:latest

start:
	docker run -p 8080:8080 pizza-lab:latest
