# LemmiToolsNg7

This is the front end side of this uber jar application

Built using Angular 7 

Commands 
For the Development: Use npm start. This uses the proxy file configured to look at localhost:8082 for http calls

For Deployment: Use npm run build.
-npm build is configured to build and output resources to META-INF/resources/ui so that spring boot can pick up the resources
-ui subfolder is necessary to isolate angular builds to one folder so that security can permit everything within ui folder


NOTE: 
1. AVOid referring to assets directly via inline attributes since this prevents the asset from being loaded when deployed
e.g. src="/assets/image.jp"  --> resolves fine locally
but when deployed the src resolves to localhsot:/8082/assets/image.jpg which doesn't exist since it is in the ui folder
2. spring source should not have any context paths other than the root

