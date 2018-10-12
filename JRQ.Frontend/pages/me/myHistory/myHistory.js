// pages/myHistory/myHistory.js
const app = getApp()
var api = require('../../../util/api.js')
import articleItem from '../../../template/articleItem/articleItem'

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cardLimits: 0,
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
      api.getOtherBasicInfo.call(this, this.data.otherid) //获取除联系方式外的其他信息
      api.getMyHistoryAbstractList.call(this, this.data.otherid) //获取文章历史记录
    } else {
      var that = this
      this.data.isGetOtherInfo = false
      api.getMyInfo.call(this, app.getOpenid()) //获取个人信息
      api.getMyHistoryAbstractList.call(this, app.getOpenid()) //获取个人历史文章列表信息
    }
    api.getMyCardLimits.call(this, app.getOpenid())
  },

  //点击查看联系方式
  isMyInfoVisiableToggle: function () {
    var that = this
    if (this.data.isGetOtherInfo) {        //获取别的用户信息
      if (!this.data.isAlreadyGetOtherInfo) {        //还没有获取当前用户信息
        //未获取他人详细信息
        wx.showModal({
          title: '是否确认查看用户信息?',
          content: '查看用户信息会消耗1次当天查看次数，您剩余查看次数为：' + that.data.cardLimits + '次',
          success: (res) => {
            if(res.confirm) {
              api.getOtherInfo.call(this, app.getOpenid(), this.data.otherid, () => {
                that.setData({
                  isMyInfoVisiable: !that.data.isMyInfoVisiable,
                })
                api.getMyCardLimits.call(this, app.getOpenid())
                that.data.isAlreadyGetOtherInfo = true
              })
            }
          }
        })
      } else {                              //已经获取当前用户信息
        that.setData({
          isMyInfoVisiable: !that.data.isMyInfoVisiable,
        })
      }
    } else {                               //获取自己信息
      api.getMyInfo.call(this, app.getOpenid(), () => {
        that.setData({
          isMyInfoVisiable: !that.data.isMyInfoVisiable,
        })
      })
    }
  },
  
  sendMyCard: function () {
    if (this.data.isGetOtherInfo) {
      api.sendMyCard.call(this, app.getOpenid(), this.data.otherid)
    } else {
      wx.showModal({
        content: '无需给自己发名片',
        showCancel: false
      })
    }
  },

  previewImg: function (e) {
    articleItem.previewImg(e)
  }
})