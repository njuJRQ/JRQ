// pages/myCardHolder/myCardHolder.js
var app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cards: [
      {
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
      }, {
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
      }, {
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
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showCards('new');
  },
  updateSearchCondition (e) {
    this.data.searchCondition = e.detail.value;
  },
  //搜索触发函数
  onSearch: function() {
    console.log('on search')
    /**
     * 方法：searchCards
     * 参数：
     * 搜索条件：condition
     */
    wx.request({
      url: app.globalData.backendUrl + "searchCards",
      data: {
        condition: this.data.searchCondition
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          cards: res.data.results
        })
      }
    })
  },
  showCards: function (kind) {
    /**
     * 方法：showCards
     * 参数：
     * 用户openId：openId
     * 展示类别：kind
     */
    wx.request({
      url: app.globalData.backendUrl + "showCards",
      data: {
        openId: app.getOpenId(),
        kind: kind
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          cards: res.data.results
        })
      }
    })
  },
  //展示新收到的名片
  showNewCards: function () {
    console.log('show new cards');
    this.showCards('new');
  },
  //展示我持有的名片
  showCurrentCards: function () {
    console.log('show current cards');
    this.showCards('current');
  },
  //展示拥有我名片的
  showWhoHasMyCard: function () {
    console.log('show who has my card');
    this.showCards('whoHasMyCard');
  },
})