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
    //获得openid,token
    var that = this;
    wx.login({
      success: function (res) {
        wx.request({
          url: 'https://api.weixin.qq.com/sns/jscode2session',
          data: {
            //小程序唯一标识
            appid: 'wxe022b5baf52ae923',
            //小程序的 app secret
            secret: '67596e7ba8e837c29176f130490b752c',
            grant_type: 'authorization_code',
            js_code: res.code
          },
          method: 'GET',
          success: function (res) {
            //获得从后端获取认证信息
            if (res.statusCode == 200) {
              console.log(res)
              var openid = res.data.openid;
              //console.log(res.data)
              wx.setStorageSync("openid", openid);
              var sessionKey = res.data.session_key;
              wx.setStorageSync("sessionKey", sessionKey);
              //获取个人微信号信息
              wx.getUserInfo({
                success: function (data) {
                  console.log(data)
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
                  console.info("用户拒绝授权");
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
    backendUrl: "http://localhost:8080/",
    //backendUrl: "https://www.sandc.xyz/",
  }
});