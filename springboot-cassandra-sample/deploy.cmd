docker-machine env --shell cmd default
@FOR /f "tokens=*" %%i IN ('docker-machine env --shell cmd default') DO %%i

mvn clean install

kubectl create -f deployment.yaml

kubectl create -f services.yaml
