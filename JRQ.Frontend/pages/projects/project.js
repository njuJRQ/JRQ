// pages/projects/project.js
const app = getApp();
var api = require('../../util/api.js')
import articleItem from '../../template/articleItem/articleItem'
Page({
  data: {
    isShow: true,
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
    curentTab: '',
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
    console.log('1-----' + e)
    var that = this;
    that.setData({
      currentTab: e.detail.current
    })
    console.log(this.data.currentTab)
    switch (this.data.currentTab) {
      
      case 0:
        console.log('0enter showPrior')
        //isShow: false
        that.showPrior()
        break;
      case 1:
        console.log('1enter showHot')
        //isShow: false
        that.showHot()
        break;
      case 2:
        console.log('2enter showTime')
        //isShow: false
        that.showTime()
        break;
      default:
    }
    console.log("bing")



  },

  // 点击tab切换 
  swichNav: function(e) {
    console.log('2-----' + e)
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
      console.log(this.data.currentTab)
      switch (this.data.currentTab) {
        case '0':
          console.log('enter showPrior')
          //isShow: false
          that.showPrior()
          break;
        case '1':
          //isShow: false
          that.showHot()
          break;
        case '2':
          //isShow: false
          that.showTime()
          break;
        default:
      }
    }

  },

  showPrior: function() {
    console.log('enter showPrior')
    this.setData({
      currentKind: "isPreferred",
      articles: [],
      openid: "",
      id: "",

      //lastIdType: ""
    })

    // api.getFeedList.call(this, 'latest', app.getOpenid(), this.data.lastId)
    console.log("showPrior")
    api.getFeedList.call(this, 'isPreferred', app.getOpenid(), this.data.id)
    console.log(api.getFeedList.call(this, 'isPreferred', app.getOpenid(), this.data.id))
    console.log("showPrior success")
  },

  showHot: function() {
    this.setData({
      currentKind: 'hot',
      articles: [],
      lastId: "",
      lastIdType: "",
      flag: false
    })
    api.getFeedList.call(this, 'hot', app.getOpenid(), this.data.lastId, this.data.id)
  },
  showTime: function() {
    this.setData({
      currentKind: 'time',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })

    api.getFeedList.call(this, 'time', app.getOpenid(), this.data.lastId, this.data.id)
  },

  onPullDownRefresh: function() {
    this.onLoad()
  },

  onReachBottom: function() {
    api.getFeedList.call(this, app.getOpenid(), this.data.lastId)
  }


})