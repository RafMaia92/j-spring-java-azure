## Create Resouce Group 
`az group create --location westeurope --resource-group j-spring`

## Create App Service
`az deployment group create --resource-group 'j-spring' --template-file demo.bicep`

## deploy Java 
`az webapp deploy --resource-group  'j-spring' --name 'jsh-humans-svc-as' --src-path ./target/humans-svc-0.0.1-SNAPSHOT.jar --type 'jar'`


`az webapp deploy --resource-group  'j-spring' --name 'jsh-humans-svc-as' --src-path ./target/onboarding-fn-0.0.1-SNAPSHOT.jar --type 'jar'`


az spring app deploy --resource-group 'j-spring' --service 'jsh-auth-svc-spa' --name 'jsh-auth-svc-apps' --artifact-path 'target/auth-svr-0.0.1-SNAPSHOT'