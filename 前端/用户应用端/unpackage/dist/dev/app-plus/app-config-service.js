
var isReady=false;var onReadyCallbacks=[];
var isServiceReady=false;var onServiceReadyCallbacks=[];
var __uniConfig = {"pages":["pages/index/index","pages/user/index","pages/user/userHome","pages/user/userInfoSetting","pages/user/setting","pages/user/site","pages/service/index","pages/forum/index","pages/forum/dynamicInfo","pages/forum/addForum","pages/news/index","pages/user/login","pages/user/register","pages/user/registerInfo"],"window":{"navigationBarTextStyle":"black","navigationBarTitleText":"uni-app","navigationBarBackgroundColor":"#F8F8F8","backgroundColor":"#F8F8F8","titleNView":false,"softinputMode":"adjustResize"},"tabBar":{"list":[{"pagePath":"pages/index/index"},{"pagePath":"pages/user/index"},{"pagePath":"pages/news/index"},{"pagePath":"pages/forum/index"}]},"nvueCompiler":"uni-app","nvueStyleCompiler":"uni-app","renderer":"auto","splashscreen":{"alwaysShowBeforeRender":true,"autoclose":false},"appname":"用户应用端","compilerVersion":"3.3.11","entryPagePath":"pages/index/index","networkTimeout":{"request":60000,"connectSocket":60000,"uploadFile":60000,"downloadFile":60000}};
var __uniRoutes = [{"path":"/pages/index/index","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/user/index","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/user/userHome","meta":{},"window":{}},{"path":"/pages/user/userInfoSetting","meta":{},"window":{}},{"path":"/pages/user/setting","meta":{},"window":{}},{"path":"/pages/user/site","meta":{},"window":{}},{"path":"/pages/service/index","meta":{},"window":{}},{"path":"/pages/forum/index","meta":{"isQuit":true,"isTabBar":true},"window":{"enablePullDownRefresh":true,"onReachBottomDistance":150}},{"path":"/pages/forum/dynamicInfo","meta":{},"window":{}},{"path":"/pages/forum/addForum","meta":{},"window":{}},{"path":"/pages/news/index","meta":{"isQuit":true,"isTabBar":true},"window":{}},{"path":"/pages/user/login","meta":{},"window":{}},{"path":"/pages/user/register","meta":{},"window":{}},{"path":"/pages/user/registerInfo","meta":{},"window":{}}];
__uniConfig.onReady=function(callback){if(__uniConfig.ready){callback()}else{onReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"ready",{get:function(){return isReady},set:function(val){isReady=val;if(!isReady){return}const callbacks=onReadyCallbacks.slice(0);onReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
__uniConfig.onServiceReady=function(callback){if(__uniConfig.serviceReady){callback()}else{onServiceReadyCallbacks.push(callback)}};Object.defineProperty(__uniConfig,"serviceReady",{get:function(){return isServiceReady},set:function(val){isServiceReady=val;if(!isServiceReady){return}const callbacks=onServiceReadyCallbacks.slice(0);onServiceReadyCallbacks.length=0;callbacks.forEach(function(callback){callback()})}});
service.register("uni-app-config",{create(a,b,c){if(!__uniConfig.viewport){var d=b.weex.config.env.scale,e=b.weex.config.env.deviceWidth,f=Math.ceil(e/d);Object.assign(__uniConfig,{viewport:f,defaultFontSize:Math.round(f/20)})}return{instance:{__uniConfig:__uniConfig,__uniRoutes:__uniRoutes,global:void 0,window:void 0,document:void 0,frames:void 0,self:void 0,location:void 0,navigator:void 0,localStorage:void 0,history:void 0,Caches:void 0,screen:void 0,alert:void 0,confirm:void 0,prompt:void 0,fetch:void 0,XMLHttpRequest:void 0,WebSocket:void 0,webkit:void 0,print:void 0}}}});
