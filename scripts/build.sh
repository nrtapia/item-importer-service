java --version

# Build service artifact
./mvnw clean package -Dmaven.test.skip=true

# Build docker image
docker build -t ntapia/item-importer-service .