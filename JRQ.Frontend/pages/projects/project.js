// pages/projects/project.js
const app = getApp();
var api = require('../../util/api.js')
import articleItem from '../../template/articleItem/articleItem'
Page({
  data: {
    articles: [],
    writerFace: '',
    writerName: '',
    writerOpenid: '',
    content: '',
    date: '',
    images: null,
    writerOpenid: '',
    id: '',
    // test:'你好吗',
    openid: '',
    currentKind: null,
    curentTab:'',
    searchCondition: null,
  
    // 页面配置  
    winWidth: 0,
    winHeight: 0,
    // tab切换 
    currentTab: 0,
  },
  onLoad: function() {
    var that = this;
    that.showPrior()
    // 获取系统信息 
    wx.getSystemInfo({
      success: function(res) {
        console.log(res)
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    })
    
  },

  // 滑动切换tab 
  bindChange: function(e) {
    var that = this;
    that.setData({
      currentTab: e.detail.current
    });
  },

  // 点击tab切换 
  swichNav: function(e) {
    console.log("enter switchNav")
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      console.log("curentTab success")
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
      console.log('changeTab')
      switch (currentKind) {
        case 'prior':
          showPrior()
          break;
        case 'hot':
          showHot()
          break;
        case 'time':
          showTime()
          break;
        default:
      }
    }
    
  },

  showPrior: function() {
    this.setData({
      currentKind: "prior",
      articles: [],
      openid: "",
      id: "",

      //lastIdType: ""
    })
    console.log('enter showPrior')
    // api.getFeedList.call(this, 'latest', app.getOpenid(), this.data.lastId)
    console.log("showPrior")
    api.getFeedList.call(this, 'prior', app.getOpenid(), this.data.id)
    console.log("showPrior success")
  },

  showHot: function () {
    this.setData({
      currentKind: 'hot',
      articles: [],
      lastId: "",
      lastIdType: "",
      flag: false
    })
    api.getFeedList.call(this, 'hot', app.getOpenid(), this.data.lastId, this.data.id)
  },
  showTime: function () {
    this.setData({
      currentKind: 'time',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'time', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },
  
  onPullDownRefresh: function () {
    this.onLoad()
  },

  onReachBottom: function () {
    api.getFeedList.call(this, app.getOpenid(), this.data.lastId)
  }

 
})