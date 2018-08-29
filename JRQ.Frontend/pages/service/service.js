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
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: '融资租赁'
    }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '商业保理'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '地产交易'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '金融牌照'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '地产交易'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '商业保理'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: '商业保理'
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showClass('capital');
  },
  showClass: function(kind) {
    this.addLabel()
    api.getPersonList(kind, this)
  },
  addLabel: function() {
    var categoryDict = {
      融资租赁: { text: '融资租赁', class_: 'card-category-rzzl' },
      商业保理: { text: '商业保理', class_: 'card-category-sybl' },
      地产交易: { text: '地产交易', class_: 'card-category-dcjy' },
      金融牌照: { text: '金融牌照', class_: 'card-category-jrpz' }
    };
    this.data.cards.forEach(function (card) {
      card['categoryText'] = categoryDict[card.categoryId].text;
      card['categoryClass'] = categoryDict[card.categoryId].class_;
    });
    this.setData(this.data)
  },
  //展示资金类
  showCapitalClass: function(event) {
    this.showClass('capitalClass');
    console.log('show capital class')
  },
  //展示股票类
  showStockClass: function() {
    this.showClass('stockClass');
    console.log('show stock class')
  },
  //展示并购类
  showMergeClass: function() {
    this.showClass('mergeClass');
    console.log('show merge class')
  }
})