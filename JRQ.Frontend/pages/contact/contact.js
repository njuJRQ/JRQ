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
    isShow:true,
    openId: '',
    user: [],

    cards: [],

    /*
    cards: [{
      openid: 1,
      face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
      username: 'username',
      position: '项目经理',
      city: '亚太区',
      company: '上海崇尚金融担保有限公司 (美资)',
      intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
      label: '融资租赁',
      bgColor: 'rgba(255, 161, 177, 0.699)'
    }, {
        openid: 2,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理',
        bgColor: 'rgba(138, 138, 252, 0.767)'
      }, {
        openid: 3,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易',
        bgColor: 'rgba(109, 156, 90, 0.726)'
      }, {
        openid: 4,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '金融牌照',
        bgColor: 'rgba(255, 58, 58, 0.678)'
      }, {
        openid: 5,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '地产交易',
        bgColor: 'rgba(109, 156, 90, 0.726)'
      }, {
        openid: 6,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
        username: 'username',
        position: '项目经理',
        city: '亚太区',
        company: '上海崇尚金融担保有限公司 (美资)',
        intro: '中铁三十九局电商30个E,寻靠谱资方。139999999 严',
        label: '商业保理',
        bgColor: 'rgba(138, 138, 252, 0.767)'
      }, {
        openid: 7,
        face: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/default/default-pic.png',
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
    currentKind: 'capital',
    capitalClassDesc: "",
    stockClassDesc: "",
    mergeClassDesc: "",
    bg1: bg1,
    vipImage: 'http://junrongcenter.oss-cn-beijing.aliyuncs.com/img/VIP-icon.png',
    label: ['内构重组', '短融过桥', '大宗交易', '银行业务', '包里融租']
  },

  onReachBottom: function () {
    api.getAbstractList.call(this, this.data.currentKind, app.getOpenid(), this.data.lastId, this.data.lastIdType)
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
    var that = this
    api.getClassificationDescriptionList.call(this, (res) => {
      res.classificationDescriptionItems.forEach((item) => {
        switch (item.workClass) {
          case "stock":
            this.data.stockClassDesc = item.description;
            break;
          case "merge":
            this.data.mergeClassDesc = item.description;
            break;
          case "capital":
            this.data.capitalClassDesc = item.description;
            break;
          default:
            break;
        }
      })
      this.setData(this.data)
      this.showCapitalClass();
    })
    this.setData({
      searchCondition: null
    })

  },

  //展示资金类
  showCapitalClass: function(event) {
    this.setData({
      currentKind: 'capital',
      currentKindName: this.data.capitalClassDesc,
      searchCondition: null
    })
    api.getPersonList.call(this, 'capital')
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
    this.setData({
      openid: '',
      userId:'',
      userIdType:''

    })
    api.getContactList.call(this, app.getOpenid(), this.data.userId, this.data.userIdType)

  }

})