// pages/service/service.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    cards: [],
    /*
    cards: [{
      openid: 1,
      face: '../../default/default-pic.png',
      username: 'username',
      position: '项目经理',
      city: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      label: '融资租赁',
      bgColor: 'rgba(255, 161, 177, 0.699)'
    }, {
        openid: 2,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理',
        bgColor: 'rgba(138, 138, 252, 0.767)'
      }, {
        openid: 3,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易',
        bgColor: 'rgba(109, 156, 90, 0.726)'
      }, {
        openid: 4,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '金融牌照',
        bgColor: 'rgba(255, 58, 58, 0.678)'
      }, {
        openid: 5,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易',
        bgColor: 'rgba(109, 156, 90, 0.726)'
      }, {
        openid: 6,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理',
        bgColor: 'rgba(138, 138, 252, 0.767)'
      }, {
        openid: 7,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理',
        bgColor: 'rgba(138, 138, 252, 0.767)'
      }],
      */
      searchCondition: null,
      kind: 'capital',
      capitalClassDesc: "",
      stockClassDesc: "",
      mergeClassDesc: ""
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this
    api.getClassificationDescriptionList.call(this, (res) => {
      res.classificationDescriptionItems.forEach((item) => {
        switch (item.workClass) {
          case "stock": that.data.stockClassDesc = item.description; break;
          case "merge": that.data.mergeClassDesc = item.description; break;
          case "capital": that.data.capitalClassDesc = item.description; break;
          default: break;
        }
      })
      that.setData(that.data)
    })
    this.setData({
      searchCondition: null
    })
    api.getAd.call(this, 'service', (res) => {
      this.setData({
        ad: res.ad
      })
    })
    this.showCapitalClass();
  },

  onShow: function (options) {
    this.setData({
      searchCondition: null
    })
    switch(this.data.kind) {
      case 'capital': this.showCapitalClass(); break;
      case 'stock': this.showStockClass(); break;
      case 'merge': this.showMergeClass(); break;
      default: break;
    }
  },
  
  //展示资金类
  showCapitalClass: function(event) {
    this.setData({
      kind: 'capital',
      searchCondition: null
    })
    api.getPersonList.call(this, 'capital')
  },

  //展示股票类
  showStockClass: function () {
    this.setData({
      kind: 'stock',
      searchCondition: null
    })
    api.getPersonList.call(this, 'stock')
  },

  //展示并购类
  showMergeClass: function () {
    this.setData({
      kind: 'merge',
      searchCondition: null
    })
    api.getPersonList.call(this, 'merge')
  },
  
  //点击当前文章触发函数
  onClickThisCard: function (e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + id,
    })
  },

  //更新搜索条件
  updateSearchCondition: function (e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function () {
    console.log('search service people: ' + this.data.searchCondition)
    api.getPersonListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  },

  //点击广告跳转
  onAd: function () {
    wx.navigateTo({
      url: '../ad/ad?url=' + this.data.ad.link
    })
  },
})