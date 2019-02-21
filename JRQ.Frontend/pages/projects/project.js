// pages/projects/project.js
Page({
  data: {
    
    // currentkind:'',
    // searchCondition:'',

    // project:[],
    // projectId:'',
    // projectIdType:'',
    openid: '',
    currentKind: null,
    searchCondition: null,
    lastId: "",
    lastIdType: "",
    flag: false,
    // 页面配置  
    winWidth: 0,
    winHeight: 0,
    // tab切换 
    currentTab: 0,
  },
  onLoad: function () {
    var that = this;
    // 获取系统信息 
    wx.getSystemInfo({
      success: function (res) {
        that.setData({
          winWidth: res.windowWidth,
          winHeight: res.windowHeight
        });
      }
    });
  },
  // 滑动切换tab 
  bindChange: function (e) {
    var that = this;
    that.setData({ currentTab: e.detail.current });
  },
  // 点击tab切换 
  swichNav: function (e) {
    var that = this;
    if (this.data.currentTab === e.target.dataset.current) {
      return false;
    } else {
      that.setData({
        currentTab: e.target.dataset.current
      })
    }
  },

  //修改
  //钧融优选
  showPrior: function () {
    this.setData({
      currentKind: 'prior',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'prior', app.getOpenid(), this.data.projectId, this.data.projectIdType)
  },

  //根据热度展示项目
  showByHot: function () {
    this.setData({
      currentKind: 'hot',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'hot', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },

  //根据时间展示项目
  showByTime: function () {
    this.setData({
      currentKind: 'time',
      searchCondition: null,
      articles: [],
      lastId: "",
      lastIdType: ""
    })
    api.getAbstractList.call(this, 'time', app.getOpenid(), this.data.lastId, this.data.lastIdType)
  },

  // 搜索项目
  //更新搜索条件
  updateSearchCondition: function (e) {
    this.data.searchCondition = e.detail.value;
  },

  //搜索触发函数
  onSearch: function () {
    if (!this.data.searchCondition) {
      this.showAll();
      return;
    }
    console.log('search article: ' + this.data.searchCondition)
    api.getAbstractListByCondition.call(this, app.getOpenid(), this.data.searchCondition)
  },

})