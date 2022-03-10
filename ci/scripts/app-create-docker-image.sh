mkdir ci/docker/build/jar
cp target/*-SNAPSHOT.jar ci/docker/build/jar/
docker build -t code-challenge ci/docker/build
rm -rf ci/docker/build/jar