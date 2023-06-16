param appName string
param location string
param logAnalyticsWorkspaceId string


resource applicationInsightsAppService 'Microsoft.Insights/components@2020-02-02' = {
  name: appName
  location: location
  kind: 'java'
  properties: {
    Application_Type: 'web'
    WorkspaceResourceId: logAnalyticsWorkspaceId
  }
}

output InstrumentationKey string = applicationInsightsAppService.properties.InstrumentationKey
