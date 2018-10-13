//app.js
App({
  onLaunch: function () {
    console.log('App Launch')
  },
  getToken: function () {
    return wx.getStorageSync('token')
  },
  getOpenid: function () {
    return wx.getStorageSync('openid')
  },
  getWechatUsername: function () {
    return wx.getStorageSync('wechatUsername')
  },
  getDate: function () {
    var date = new Date()
    return date.getFullYear() + '-' + (date.getMonth() + 1) + '-' + date.getDate()
  },
  onShow: function () {
    var that = this;
    wx.login({
      success: function (res) {
        var js_code = res.code
        wx.request({
          url: that.globalData.backendUrl + "getOpenIdAndSessionKey",
          header: {
            'content-type': 'application/x-www-form-urlencoded'
          },
          data: {
            "jsCode": js_code
          },
          method: 'GET',
          success: function (res) {
            //获得从后端获取认证信息
            if (res.statusCode == 200) {
              if (res.data.errcode) {
                wx.showModal({
                  title: '获取openid失败',
                  content: '请检查appid和secret',
                  showCancel: false
                })
              } else {
                /*console.log(res)*/
                var openid = res.data.openId;
                wx.setStorageSync("openid", openid);
                var sessionKey = res.data.sessionKey;
                wx.setStorageSync("sessionKey", sessionKey);
                //获取个人微信号信息
                wx.getUserInfo({
                  success: function (data) {
                    console.info('个人微信号信息', data)
                    wx.setStorageSync("wechatUsername", data.userInfo.nickName);
                    wx.setStorageSync("wechatFaceUrl", data.userInfo.avatarUrl)
                    wx.request({
                      url: that.globalData.backendUrl + "loginMyUser",
                      header: {
                        'content-type': 'application/x-www-form-urlencoded'
                      },
                      data: {
                        openid: openid,
                        username: that.getWechatUsername(),
                        faceUrl: wx.getStorageInfoSync("wechatFaceUrl")
                      },
                      method: 'GET',
                      success: (res) => {
                        wx.setStorageSync("token", res.data.token);
                      },
                      fail: (res) => {
                        wx.showModal({
                          title: '连接服务器失败',
                          content: res.errMsg,
                          showCancel: false,
                          success: (res) => {
                            that.onShow()
                          }
                        })
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
            } else {
              console.log(res)
              wx.showModal({
                title: '获取openid失败',
                content: res.data.status + ' ' + res.data.error,
                showCancel: false
              })
            }
          },
          fail: function (res) {
            wx.showModal({
              title: '连接服务器失败',
              content: res.errMsg,
              showCancel: false,
              success: (res) => {
                that.onShow()
              }
            })
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
    openid: "",
    sessionKey: "",
    wechatUsername: "",
    token: "",
    defaultPic: '../../default/default-pic.png',

    //appid: "wxe022b5baf52ae923", //小程序唯一标识
    //secret: "67596e7ba8e837c29176f130490b752c", //小程序的 app secret

    //appid: "wxe022b5baf52ae923",//test
    //appid: "wx2e1011ad046ddc3f",//xulei

    //secret: "8a11779c7567ae184c50913df20a2f2e",//xulei

    appid: "wx917cbd6132554ae2",//used
    secret: "55e365dcaf3d51b4159bf0e1017a4978",//used

    //backendUrl: "http://localhost:3389/",
    backendUrl: "https://junrongcenter.com:3389/",//used
    picUrl: "https://www.junrongcenter.com/"//used
    //picUrl: "http://localhost:8000/",
    //picUrl: "http://localhost/libs/"//xulei
  }
});