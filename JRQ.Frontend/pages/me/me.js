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
    },
    publishInputValue: "",
    publishPhotos: null
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
  bindPublishInput: function(e) {
    this.data.publishInputValue = e.detail.value;
    console.log("Input: " + this.data.publishInputValue);
  },
  onUploadPhotos: function () {
    var that = this;
    wx.chooseImage({
      count: 3,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        var tempFilePaths = res.tempFilePaths;
        that.setData({
          publishPhotos: tempFilePaths
        })
      },
    })
  },
  //发布信息
  onPublish: function () {
    console.log('publish');
    
  },
  //递名片
  onSendMe: function() {
    console.log('send me');
  }
})