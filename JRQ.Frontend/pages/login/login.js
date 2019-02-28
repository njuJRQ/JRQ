// pages/login/login.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')

Page({
  /**
   * 页面的初始数据
   */
  data: {
    
    systemInfo: {},
    status:true

  },




  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    this.judgeApp()

  },


  bindGetUserInfo: function(e) {
   
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
    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
       that.condition=res

      // wx.getSystemInfo({
        
      //   success: function (res) {
      //     console.log(that.condition)
      //     that.setData({
      //       systemInfo: res,
      //     })
      //     if (res.platform == "ios" && that.condition == false) {
      //       wx.navigateTo({
      //         url: '/pages/judge/judge',
      //       })
      //     }
      //   }
      // })
    })  
  },
})