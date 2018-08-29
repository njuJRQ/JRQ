// pages/myHistory/myHistory.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    myInfo: {
      username: 'USERNAME',
      medals: [
        '../../../default/default-icon.png',
        '../../../default/default-icon.png',
        '../../../default/default-icon.png',
        '../../../default/default-icon.png'],
      phone: '18512346956',
      email: '123456789@163.com',
      company: '美国永辉有限公司',
      department: 'IT技术部',
      position: 'IT初级经理',
      intro: '我要在代码的世界里飞翔。'
    },
    isMyInfoVisiable: false,
    encreptInfo: {
      phone: '',
      wechatId: '',
      email: ''
    },
    myArticles: [{
      text: '《有效识别金融项目》课程。',
      images: [
        '../../../default/default-pic.png',
        '../../../default/default-pic.png',
        '../../../default/default-pic.png'
      ],
      writerFace: '../../../default/default-icon.png',
      writerName: 'USERNAME',
      date: '2020-01-01',
      likeNum: 8965
    },{
      text: '《有效识别金融项目》课程。',
      images: [
        '../../../default/default-pic.png',
        '../../../default/default-pic.png',
        '../../../default/default-pic.png'
      ],
      writerFace: '../../../default/default-icon.png',
      writerName: 'USERNAME',
      date: '2020-01-01',
      likeNum: 8965
    },{
      text: '《有效识别金融项目》课程。',
      images: [
        '../../../default/default-pic.png',
        '../../../default/default-pic.png',
        '../../../default/default-pic.png'
      ],
      writerFace: '../../../default/default-icon.png',
      writerName: 'USERNAME',
      date: '2020-01-01',
      likeNum: 8965
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.init()
    api.getMyInfo(app.getOpenid(), this) //获取个人信息
    this.encrept() //加密个人信息
    api.getMyHistoryAbstractList(app.getOpenid(), this) //获取个人历史文章列表信息
  },
  isMyInfoVisiableToggle: function () {
    this.setData({
      isMyInfoVisiable: !this.data.isMyInfoVisiable,
    })
  },
  init: function () {
    this.setData({
      wechatId: app.getOpenid(),
    })
  },
  encrept: function () {
    var phone = this.data.myInfo.phone
    this.setData({
      encreptInfo: { 
        phone: phone.substr(0, 3) + '****' + phone.substr(7),
        wechatId: '****',
        email: '******'
      }
    })
  }
})