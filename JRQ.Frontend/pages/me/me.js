// pages/me/me.js
const app = getApp();
var api = require('../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myInfo: {
      username: 'USERNAME',
      medals: [
        '../../default/default-icon.png',
        '../../default/default-icon.png',
        '../../default/default-icon.png',
        '../../default/default-icon.png'],
      phone: '123456789',
      email: '123456789@163.com',
      company: '美国永辉有限公司',
      department: 'IT技术部',
      position: 'IT初级经理',
      intro: '我要在代码的世界里飞翔。'
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    var that = this
    api.getMyInfo(app.getOpenid(), this) //获取个人信息
  },
  onPullDownRefresh: function () {
    this.onShow()
  },
  //发布信息
  onPublish: function () {
    console.log('publish')
    wx.navigateTo({
      url: 'publishMyArticle/publishMyArticle',
    })
  },
  //递名片
  onSendMe: function() {
    wx.navigateTo({
      url: 'myCardHolder/myCardHolder',
    })
  }
})