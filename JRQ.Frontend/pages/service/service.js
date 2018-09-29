// pages/service/service.js
//获取应用实例
const app = getApp()
var api = require('../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
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
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    api.getAd.call(this, 'service')
    this.showCapitalClass();
  },
  
  //展示资金类
  showCapitalClass: function(event) {
    api.getPersonList.call(this, 'capital')
  },

  //展示股票类
  showStockClass: function() {
    api.getPersonList.call(this, 'stock')
  },

  //展示并购类
  showMergeClass: function() {
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
    api.getPersonListByCondition.call(this, this.data.searchCondition)
  },

  //点击广告跳转
  onAd: function () {
    wx.navigateTo({
      url: '../ad/ad?url=' + this.data.ad.link
    })
  },
})