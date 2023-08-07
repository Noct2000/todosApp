setup:
	cp .env_sample .env

up:
	docker compose -f docker-compose.yml up

build:
	docker compose -f docker-compose.yml  build

stop:
	docker compose -f docker-compose.yml  stop

down:
	docker compose -f docker-compose.yml down

native-down:
	docker compose -f docker-compose-native.yml down

native-up:
	docker compose -f docker-compose-native.yml up

native-build:
	docker compose -f docker-compose-native.yml build

native-stop:
	docker compose -f docker-compose-native.yml  stop

clear:
	docker system prune -a --volumes --force
