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
  onShow: function (options) {
    this.showNewCards()
  },

  //更新搜索条件
  updateSearchCondition(e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function () {
    api.getPersonListByCondition.call(this, this.data.searchCondition)
  },

  //展示新收到的名片
  showNewCards: function () {
    api.getMyPersonList.call(this, app.getOpenid(), 'new')
  },

  //展示我持有的名片
  showCurrentCards: function () {
    api.getMyPersonList.call(this, app.getOpenid(), 'current')
  },

  //展示拥有我名片的
  showWhoHasMyCard: function () {
    api.getMyPersonList.call(this, app.getOpenid(), 'whoHasMyCard')
  },

  onClickThisCard: function (e) {
    var id = e.currentTarget.dataset.id
    api.checkMyReceivedCard.call(this, app.getOpenid(), id) //设置当前的卡片为已读
    wx.navigateTo({
      url: '../myHistory/myHistory?id=' + id,
    })
  }
})