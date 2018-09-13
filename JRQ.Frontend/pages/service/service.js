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
      label: '融资租赁'
    }, {
        openid: 2,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理'
      }, {
        openid: 3,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易'
      }, {
        openid: 4,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '金融牌照'
      }, {
        openid: 5,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易'
      }, {
        openid: 6,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理'
      }, {
        openid: 7,
        face: '../../default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理'
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onShow: function (options) {
    this.showClass('capital');
  },
  onPullDownRefresh: function () {
    this.onShow()
  },
  showClass: function(kind) {
    var that = this
    api.getPersonList(kind, this, ()=>{
      that.addLabel()
    })
  },
  addLabel: function() {
    var categoryDict = {
      融资租赁: { text: '融资租赁', class_: 'card-category-rzzl' },
      商业保理: { text: '商业保理', class_: 'card-category-sybl' },
      地产交易: { text: '地产交易', class_: 'card-category-dcjy' },
      金融牌照: { text: '金融牌照', class_: 'card-category-jrpz' }
    };
    this.data.cards.forEach(function (card) {
      card['categoryText'] = categoryDict[card.label].text;
      card['categoryClass'] = categoryDict[card.label].class_;
    });
    this.setData(this.data)
  },
  //展示资金类
  showCapitalClass: function(event) {
    this.showClass('capital');
    console.log('show capital class')
  },
  //展示股票类
  showStockClass: function() {
    this.showClass('stock');
    console.log('show stock class')
  },
  //展示并购类
  showMergeClass: function() {
    this.showClass('merge');
    console.log('show merge class')
  },
  onClickThisCard: function (e) {
    var id = e.currentTarget.dataset.id
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + id,
    })
  }
})