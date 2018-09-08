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
    isGetOtherInfo: null,
    isAlreadyGetOtherInfo: null,
    otherid: null,
    encreptInfo: {
      phone: '************',
      wechatId: '****',
      email: '******'
    },
    myArticles: [{
      id: 1,
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
    }, {
      id: 2,
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
    }, {
      id: 3,
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
    if (options.id) {
      this.data.isGetOtherInfo = true
      this.data.isAlreadyGetOtherInfo = false
      this.data.otherid = options.id
      api.getOtherBasicInfo(this.data.otherid, this) //获取除联系方式外的其他信息
      api.getMyHistoryAbstractList(this.data.otherid, this) //获取文章历史记录
    }
    else {
      var that = this
      this.data.isGetOtherInfo = false
      api.getMyInfo(app.getOpenid(), this) //获取个人信息
      api.getMyHistoryAbstractList(app.getOpenid(), this) //获取个人历史文章列表信息
    }
  },
  //点击查看联系方式
  isMyInfoVisiableToggle: function () {
    var that = this
    if (this.data.isGetOtherInfo) {
      if (!this.data.isAlreadyGetOtherInfo) {
        api.getOtherInfo(app.getOpenid(), this.data.otherid, this, ()=>{
          that.setData({
            isMyInfoVisiable: !that.data.isMyInfoVisiable,
          })
        })
      }
    }
    else {
      api.getMyInfo(app.getOpenid(), this, ()=> {
        that.setData({
          isMyInfoVisiable: !that.data.isMyInfoVisiable,
        })
      })
    }
  },
  sendMyCard: function () {
    if (this.data.isGetOtherInfo) {
      api.sendMyCard(app.getOpenid(), this.data.otherid)
    } else {
      wx.showModal({
        content: '无需给自己发名片',
        showCancel: false
      })
    }
  }
})