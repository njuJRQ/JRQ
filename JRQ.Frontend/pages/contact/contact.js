// pages/contact/contact.js
// pages/service/service.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')
const {
  bg1
} = require('../../util/data.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    isShow: true,
    openId: '',

    cards: [],

    searchCondition: null,
    capitalClassDesc: "",
    stockClassDesc: "",
    mergeClassDesc: "",
    bg1: bg1,
    vipImage: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/img/VIP-icon.png',
    label: ['内构重组', '短融过桥', '大宗交易', '银行业务', '包里融租']
  },

  onReachBottom: function() {
    var lastOpenid = this.data.cards[this.data.cards.length - 1].openid;
    this.getPersonList(lastOpenid)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var condition = true
    api.getIOSQualification.call(this, (res) => {
      console.log(res)
      condition = res
      if (!condition) {
        this.setData({
          isShow: false
        })
      }
    })
    this.showContactList();
  },

  //点击当前文章触发函数
  onClickThisCard: function(e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + id,
    })
  },

  //更新搜索条件
  updateSearchCondition: function(e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function() {
    console.log('search service people: ' + this.data.searchCondition)
    api.getPersonListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  },

  showContactList: function() {
    this.getPersonList(-1)
  },

  getPersonList: function(lastPersonId) {
    var that = this
    wx.showLoading({
      title: '载入中',
    })
    wx.request({
      url: app.globalData.backendUrl + "getPersonList",
      data: {
        openid: lastPersonId
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        wx.hideLoading()
        var newCards = res.data.persons
        newCards.forEach((card) => {
          card.face = app.globalData.picUrl + card.face
          return card
        })
        var cards = that.data.cards;
        cards = cards.concat(newCards);
        that.setData({
          cards: cards
        })
      }
    })
  }
})