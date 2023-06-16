param applicationPrefix string = 'jsh' // J Spring Humans 
param location string = 'westeurope'
param sku string = 'B1'
// new 
param humansAppServiceName string = 'humans-svc'
param onboardingFunctionName string = 'onboarding'
param parkingFunctionName string = 'parking'


// Define ALL Components name 

// Humans App Service 
var humansAs = '${applicationPrefix}-${humansAppServiceName}-as'
var humansAi = '${applicationPrefix}-${humansAppServiceName}-ai'

var onboardingFn =  '${applicationPrefix}-${onboardingFunctionName}-fn'
var onboardingAi =  '${applicationPrefix}-${onboardingFunctionName}-ai'

var parkingFn =  '${applicationPrefix}-${parkingFunctionName}-fn'
var parkingAi =  '${applicationPrefix}-${parkingFunctionName}-ai'


var storageAccountConnetionString = 'DefaultEndpointsProtocol=https;AccountName=${storageAccount.name};AccountKey=${storageAccount.listKeys().keys[0].value};EndpointSuffix=${environment().suffixes.storage}'



resource appServicePlan 'Microsoft.Web/serverfarms@2022-03-01' = {
  name: '${applicationPrefix}-asp'
  location: location
  properties: {
    reserved: true
  }
  sku: {
    name: sku
  }
}

resource logAnalyticsWorkspace 'Microsoft.OperationalInsights/workspaces@2022-10-01' = {
  name: '${applicationPrefix}-law'
  location: location
  properties: {
    sku: {
      name: 'PerGB2018'
    }
    retentionInDays: 90
    workspaceCapping: {
      dailyQuotaGb: 1
    }
  }
}

// Creates an Azure Storage Account
resource storageAccount 'Microsoft.Storage/storageAccounts@2022-09-01' = {
  name: '${applicationPrefix}${onboardingFunctionName}sa'
  location: location
  sku: {
    name: 'Standard_LRS'
  }
  
  kind: 'StorageV2'
  properties: {
    supportsHttpsTrafficOnly: true
  }
}

resource appService 'Microsoft.Web/sites@2022-03-01' = {
  name: humansAs
  location: location
  properties: {
    serverFarmId: appServicePlan.id
    siteConfig: {
      linuxFxVersion: 'JAVA|17-java17' 
      appSettings: [
        {
          name: 'APPINSIGHTS_INSTRUMENTATIONKEY'
          value: appInsightsHumans.outputs.InstrumentationKey
        }
      ]  
    }
  }
}

module appInsightsHumans 'application-Insights.bicep'= {
  name: humansAi
  params: {
    appName: humansAi
    location: location
    logAnalyticsWorkspaceId: logAnalyticsWorkspace.id
  }
}

module appInsightsOnboarding 'application-Insights.bicep'= {
  name: onboardingAi
  params: {
    appName: onboardingAi
    location: location
    logAnalyticsWorkspaceId: logAnalyticsWorkspace.id
  }
}

module appInsightsParking 'application-Insights.bicep'= {
  name: parkingAi
  params: {
    appName: parkingAi
    location: location
    logAnalyticsWorkspaceId: logAnalyticsWorkspace.id
  }
}

module functionOnboardingApp 'function.bicep' = {
  name: onboardingFn
  params: {
    functionName: onboardingFn
    ApplicationInsightsInstrumentationKey: appInsightsOnboarding.outputs.InstrumentationKey
    location: location
    servicePlanId: appServicePlan.id
    storageAccountConnetionString: storageAccountConnetionString
  }
}

module functionParkingApp 'function.bicep' = {
  name: parkingFn
  params: {
    functionName: parkingFn
    ApplicationInsightsInstrumentationKey: appInsightsParking.outputs.InstrumentationKey
    location: location
    servicePlanId: appServicePlan.id
    storageAccountConnetionString: storageAccountConnetionString
  }
}








// resource springAppsInstance 'Microsoft.AppPlatform/Spring@2022-12-01' = {
//   name: '${applicationPrefix}-${springAppName}-spa'
//   location: location
// }

// resource authService 'Microsoft.AppPlatform/Spring/apps@2022-12-01' = {
//   name: '${applicationPrefix}-${springAppName}-apps'
//   location: location
//   parent: springAppsInstance
//   identity: {
//       type: 'SystemAssigned'
//     }
//   properties: {
//     public: true
//   }
// }

// resource asaDeployment 'Microsoft.AppPlatform/Spring/apps/deployments@2022-12-01' = {
//   name: 'jsh-auth-svc-apps'
//   parent: authService
//   properties: {
//     deploymentSettings: {
//       resourceRequests: {
//         cpu: '1'
//         memory: '2Gi'
//       }
//     }
//     source: {
//       type: 'Jar'
//       runtimeVersion: 'Java_17'
//       relativePath: '<default>'
//     }
//   }
// }




