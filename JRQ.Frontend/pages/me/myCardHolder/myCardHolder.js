// pages/myCardHolder/myCardHolder.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cards: [
      {
        openid: 1,
        username: 'USERNAME',
        face: '../../../default/default-pic.png',
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
        openid: 2,
        username: 'USERNAME',
        face: '../../../default/default-pic.png',
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
        openid: 3,
        username: 'USERNAME',
        face: '../../../default/default-pic.png',
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
    api.getPersonListByCondition(this.data.searchCondition, this)
  },
  //展示我的某一类型的名片
  showCards: function (kind) {
    api.getMyPersonList(app.getOpenid(), kind, this)
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
  onClickThisCard: function (e) {
    var id = e.currentTarget.dataset.id
    api.checkMyReceivedCard(app.getOpenid(), id)
    wx.navigateTo({
      url: '../myHistory/myHistory?id='+id,
    })
  }
})