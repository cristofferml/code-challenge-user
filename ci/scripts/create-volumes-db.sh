docker volume create postgres-data


docker inspect --format="{{.State.Running}}" $CONTAINER