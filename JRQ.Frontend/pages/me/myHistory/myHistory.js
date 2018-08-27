// pages/myHistory/myHistory.js
var app = getApp();

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
      phone: '123456789',
      email: '123456789@163.com',
      company: '美国永辉有限公司',
      department: 'IT技术部',
      position: 'IT初级经理',
      intro: '我要在代码的世界里飞翔。'
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
    this.showMyInfo();
    this.showMyHistoryArticles();
  },
  showMyInfo: function () {
    /**
     * 方法：getMyInfo
     * 参数：
     * 无
     */
    wx.request({
      url: app.globalData.backendUrl + "getMyInfo",
      data: {
        openId: app.getOpenId()
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          myInfo: res.data.myInfo
        })
      }
    })
  },
  showMyHistoryArticles: function () {
    /**
     * 方法：showMyHistoryArticles
     * 参数：用户openId：openId
     */
    wx.request({
      url: app.globalData.backendUrl + "showMyHistoryArticles",
      data: {
        openId: app.getOpenId()
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          myArticles: res.data.articleList
        })
      }
    })
  }
})