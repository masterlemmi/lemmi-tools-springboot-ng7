# LemmiToolsNg7

This is the front end side of this uber jar application

Built using Angular 7 

Commands 
For the Development: Use npm start. This uses the proxy file configured to look at localhost:8082 for http calls

For Deployment: npm build is configured to build and output resources to META-INF/resources so that spring boot can pick up the resources


AVOid referring to assets directly via inline attributes since this prevents the asset from being loaded when deployed
e.g. src="/assets/image.jp"  --> resolves fine locally
but when deployed the src resolves to localhsot:/8082/assets/image.jpg which doesn't exist since these are deployed in a ui folder
