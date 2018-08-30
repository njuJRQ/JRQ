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
  onLoad: function (options) {
    this.showMyInfo();
  },
  showMyInfo: function() {
    api.getMyInfo(app.getOpenid(), this)
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