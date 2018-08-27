// pages/service/service.js
//获取应用实例
const app = getApp()

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
      categoryId: 'rzzl'
    }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'sybl'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'dcjy'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'jrpz'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'dcjy'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'sybl'
      }, {
        face: '../../default/default-pic.png',
        userName: 'USERNAME',
        position: '项目经理',
        area: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        categoryId: 'sybl'
      }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showClass('all');
  },
  showClass: function(kind) {
    var categoryDict = {
      rzzl: { text: '融资租赁', class_: 'card-category-rzzl' },
      sybl: { text: '商业保理', class_: 'card-category-sybl' },
      dcjy: { text: '地产交易', class_: 'card-category-dcjy' },
      jrpz: { text: '金融牌照', class_: 'card-category-jrpz' }
    };
    this.data.cards.forEach(function (card) {
      card['categoryText'] = categoryDict[card.categoryId].text;
      card['categoryClass'] = categoryDict[card.categoryId].class_;
    });
    this.setData(this.data);
    /**
     * 方法：getPeople
     * 参数：
     * 无
     */
    wx.request({
      url: app.globalData.backendUrl + "getPeople",
      data: { kind: kind },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        var categoryDict = {
          rzzl: { text: '融资租赁', class_: 'card-category-rzzl' },
          sybl: { text: '商业保理', class_: 'card-category-sybl' },
          dcjy: { text: '地产交易', class_: 'card-category-dcjy' },
          jrpz: { text: '金融牌照', class_: 'card-category-jrpz' }
        };
        res.data.peopleList.forEach(function (card) {
          card['categoryText'] = categoryDict[card.categoryId].text;
          card['categoryClass'] = categoryDict[card.categoryId].class_;
        });
        this.setData({
          cards: res.data.peopleList
        })
      }
    })
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