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
            console.log('jscode2session请求结果：', res)
            if (res.statusCode == 200) {
              if (res.data.errcode) {
                wx.showModal({
                  title: '获取openid失败',
                  content: '请检查appid和secret',
                  showCancel: false
                })
              } else {
                //console.log(res.data)
                var openid = res.data.openid;
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
                        username: that.getWechatUsername()
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
            }
          },
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
    //appid: "wxe022b5baf52ae923",//test
    //appid: "wx2e1011ad046ddc3f",//xulei
    appid: "wx917cbd6132554ae2",//used
    //secret: "67596e7ba8e837c29176f130490b752c", //小程序的 app secret
    //secret: "8a11779c7567ae184c50913df20a2f2e",//xulei
    secret: "55e365dcaf3d51b4159bf0e1017a4978",
    //used
    //backendUrl: "http://localhost:8080/",
    backendUrl: "http://junrongcenter.com:8080/",//used
    picUrl: "http://junrongcenter.com/"//used
    //picUrl: "http://localhost:8000/",
    //picUrl: "http://localhost/libs/"//xulei
  
  }
});