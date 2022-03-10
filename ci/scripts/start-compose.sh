docker inspect --format="{{.Name}}" postgres-data
if [ $? -eq 0 ];
then
     echo "existing"
else
     sh ci/scripts/create-volumes-db.sh
     echo "missing"
fi

docker-compose -f ci/docker/docker-compose.yaml up