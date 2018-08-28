// pages/modifyMyCard/modifyMyCard.js
const app = getApp();
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    oldInfo: {
      face: '../../../default/default-pic.png',
      username: 'USERNAME',
      phone: '13952146595',
      email: '13952146595@163.com',
      company: '美国永辉公司',
      department: 'IT技术部',
      position: 'T1初级经理',
      intro: '我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。'
    }
  },
  updateFace: function () {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePath = res.tempFilePaths[0];
        that.data.face = tempFilePath;
        that.data.oldInfo.face = tempFilePath;
        that.setData(that.data);
      },
    })
  },
  updateName: function (e) {
    this.data.name = e.detail.value;
  },
  updatePhone: function (e) {
    this.data.phone = e.detail.value;
  },
  updateEmail: function (e) {
    this.data.email = e.detail.value;
  },
  updateCompany: function (e) {
    this.data.company = e.detail.value;
  },
  updateDepartment: function (e) {
    this.data.department = e.detail.value;
  },
  updatePosition: function (e) {
    this.data.position = e.detail.value;
  },
  updateIntro: function (e) {
    this.data.intro = e.detail.value;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  onSave: function(){
    console.log('save')
    api.updateUser(this)
  }
})