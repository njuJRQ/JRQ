//app.js
App({
  onLaunch: function () {
    console.log('App Launch')
  },
  getToken: function () {
    return wx.getStorageSync("token");
  },
  getOpenid: function () {
    return wx.getStorageSync("openid");
  },
  getWechatUsername() {
    return wx.getStorageSync("wechatUsername");
  },
  onShow: function () {
    var that = this;
    wx.login({
      success: function (res) {
        //用户登录成功
        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session',
          data: {
            appid: that.globalData.appid,
            secret: that.globalData.secret,
            grant_type: 'authorization_code',
            js_code: res.code
          },
          method: 'GET',
          success: function (res) {
            //成功从jscode2session请求到openid和session_key
            if (res.statusCode == 200) {
              console.log('jscode2session请求结果：', res)
              var openid = res.data.openid;
              //console.log(res.data)
              wx.setStorageSync("openid", openid);
              var sessionKey = res.data.session_key;
              wx.setStorageSync("sessionKey", sessionKey);
              //获取个人微信号信息
              wx.getUserInfo({
                success: function (data) {
                  console.log('个人微信号信息', data)
                  wx.setStorageSync("wechatUsername", data.userInfo.nickName);
                  wx.request({
                    url: that.globalData.backendUrl + "loginMyUser",
                    header: {
                      'content-type': 'application/x-www-form-urlencoded'
                    },
                    data: {
                      openid: openid,
                      username: getApp().getWechatUsername()
                    },
                    method: 'GET',
                    success: (res) => {
                      wx.setStorageSync("token", res.data.token);
                    }
                  })
                },
                fail: function (failData) {
                  console.log("用户拒绝授权");
                  wx.redirectTo({
                    url: '/pages/login/login',
                  })
                }
              });
            }
          },
          fail: function (res) {
            console.log("获取openid失败")
          }
        })
      },
      fail: function (res) {
        console.log("用户登录失败")
      }
    })
    console.log('App Show')
  },
  onHide: function () {
    console.log('App Hide')
  },
  globalData: {
    hasLogin: false,
    token: "",
    defaultPic: '../../default/default-pic.png',
    appid: "wxe022b5baf52ae923", //小程序唯一标识
    secret: "67596e7ba8e837c29176f130490b752c", //小程序的 app secret
    backendUrl: "http://localhost:8080/",
    //backendUrl: "https://www.sandc.xyz/",
  }
});