// pages/login/login.js
const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    systemInfo: {},

  },




  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.judgeApp()

  },


  bindGetUserInfo: function(e) {
    this.judgeApp()
    console.log(e)
    wx.setStorageSync("wechatUsername", e.detail.userInfo.nickName);
    wx.setStorageSync("wechatFaceUrl", e.detail.userInfo.avatarUrl);
    wx.request({
      url: app.globalData.backendUrl + "loginMyUser",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: {
        openid: getApp().getOpenid(),
        username: getApp().getWechatUsername(),
        faceWxUrl: wx.getStorageSync('wechatFaceUrl')
      },
      method: 'GET',
      success: (res) => {
        //登录成功，显示小程序主页
      }
    })
    wx.switchTab({
      url: '/pages/index/index',
    })
  },
  //判断
  judgeApp: function() {
    var that = this;
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          systemInfo: res,
        })
        if (res.platform == "devtools") {
          //PC
        } else if (res.platform == "ios") {
          //IOS
          
         

        } else if (res.platform == "android") {
          wx.showModal({
            title: '该小程序暂不支持IOS用户访问！',
            content: '敬请期待！',
            success: (res) => {
              if (res.confirm) {
                wx.navigateTo({
                  url: '/pages/judge/judge',
                })

                // wx.navigateBack({
                //   delta: -1
                // })
              } else {
                wx.navigateTo({
                  url: '/pages/judge/judge',
                })
                // wx.navigateBack({
                //   delta: -1
                // })

              }
            }
          })

        }
      }
    })
  },
})