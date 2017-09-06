# webrtc工程整合


整合了几个github上的工程在一起 android ios chrome之间使用webrtc进行语音视频通讯 bug还有很多 API也没包装出来再
支持自建webrtc服务整合到自己的即时通讯系统里

# 安装turnserver
turnserver可以手动安装 然后修改apprtc-node-server 下routes/index.js


~~~~
router.get('/turn', function(req, res, next) {
  console.log(req);
  res.send({uris:'turn://vm.xiaominfc.com:8222',username:'rtc',password:'rtc'});
});
~~~~

返回自己的turnserver的信息

