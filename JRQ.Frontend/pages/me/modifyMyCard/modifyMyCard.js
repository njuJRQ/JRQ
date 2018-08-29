// pages/modifyMyCard/modifyMyCard.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myInfo: {
      face: '../../../default/default-pic.png',
      username: 'USERNAME',
      phone: '13952146595',
      email: '13952146595@163.com',
      company: '美国永辉公司',
      city: '亚太区',
      department: 'IT技术部',
      position: 'T1初级经理',
      intro: '我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。'
    },
    newMyInfo: {}
  },
  updateFace: function () {
    var that = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        var tempFilePath = res.tempFilePaths[0]
        console.log(tempFilePath)
        that.data.myInfo.face = tempFilePath
        that.data.newMyInfo.face = tempFilePath
        that.setData(that.data)
      },
    })
  },
  updateName: function (e) {
    this.data.newMyInfo.name = e.detail.value;
  },
  updatePhone: function (e) {
    this.data.newMyInfo.phone = e.detail.value;
  },
  updateEmail: function (e) {
    this.data.newMyInfo.email = e.detail.value;
  },
  updateCity: function (e) {
    this.data.newMyInfo.city = e.detail.value;
  },
  updateCompany: function (e) {
    this.data.newMyInfo.company = e.detail.value;
  },
  updateDepartment: function (e) {
    this.data.newMyInfo.department = e.detail.value;
  },
  updatePosition: function (e) {
    this.data.newMyInfo.position = e.detail.value;
  },
  updateIntro: function (e) {
    this.data.newMyInfo.intro = e.detail.value;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    api.getMyInfo(app.getOpenid(), this)
    this.setData({
      newMyInfo: this.data.myInfo
    })
  },
  onSave: function(){
    console.log('save')
    api.modifyMyInfo(this)
    wx.showToast({
      title: '修改成功',
      icon: 'succes',
      duration: 1000,
      mask: true
    })
  }
})