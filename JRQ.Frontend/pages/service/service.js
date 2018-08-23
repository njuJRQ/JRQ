// pages/service/service.js
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
    },{
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: 'sybl'
    },{
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: 'dcjy'
    },{
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: 'jrpz'
    },{
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: 'dcjy'
    },{
      face: '../../default/default-pic.png',
      userName: 'USERNAME',
      position: '项目经理',
      area: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      detail: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      categoryId: 'sybl'
    },{
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
    var categoryDict = {
      rzzl: {text: '融资租赁', class_: 'card-category-rzzl'},
      sybl: {text: '商业保理', class_: 'card-category-sybl'},
      dcjy: {text: '地产交易', class_: 'card-category-dcjy'},
      jrpz: {text: '金融牌照', class_: 'card-category-jrpz'}
    };
    this.data.cards.forEach(function(card){
      card['categoryText'] = categoryDict[card.categoryId].text;
      card['categoryClass'] = categoryDict[card.categoryId].class_;
    });
    this.setData(this.data);
  },
  //展示资金类
  showCapitalClass: function() {
    console.log('show capital class')
  },
  //展示股票类
  showStockClass: function() {
    console.log('show stock class')
  },
  //展示并购类
  showMergeClass: function() {
    console.log('show merge class')
  }
})