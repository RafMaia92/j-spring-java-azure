param functionName string
param location string
param servicePlanId string
param storageAccountConnetionString string
param ApplicationInsightsInstrumentationKey string


resource functionOnboardingApp 'Microsoft.Web/sites@2022-03-01' = {
  name: functionName
  location: location
  kind: 'functionapp'
  properties: {
    serverFarmId: servicePlanId
    siteConfig: {
      appSettings: [
        {
          name: 'FUNCTIONS_WORKER_RUNTIME'
          value: 'java'
        }
        {
          name: 'AzureWebJobsStorage'
          value: storageAccountConnetionString
        }
        {
          name: 'FUNCTIONS_EXTENSION_VERSION'
          value: '~3'
        }
        {
          name: 'APPINSIGHTS_INSTRUMENTATIONKEY'
          value: ApplicationInsightsInstrumentationKey
        }
      ]
    }
  }
}


