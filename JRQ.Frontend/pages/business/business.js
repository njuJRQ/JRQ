// pages/business/business.js
var api = require('../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    image: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/44.jpg',
    images: [{
      id: 0,
      image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%9C%B0%E4%BA%A7.png',
      //  text:'地金市场' 
    }, {
      id: 1,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E9%87%91%E8%9E%8D.png',
      // text: '一级市场' 

    }, {
      id: 2,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E6%89%A7%E7%85%A7%E5%8A%9E%E7%90%86.png',
      // text: '牌照市场' 

    }, {
      id: 3,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E8%82%A1%E7%A5%A8.png',
      // text: '二级市场' 

    }, {
      id: 4,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%A4%A7%E9%A2%9D%E7%9F%AD%E6%8B%86.png',
      // text: '大额短诉' 

    }, {
      id: 5,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%9F%BA%E9%87%91%E6%9C%8D%E5%8A%A1.png',
      // text: '基金服务' 

    }, {
      id: 6,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E4%B8%8D%E8%89%AF%E8%B5%84%E4%BA%A7.png',
      // text: '不良资产' 

    }, {
      id: 7,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E8%B5%84%E4%BA%A7%E8%AF%81%E5%88%B8%E5%8C%96.png',
      // text: '资产证券化' 

    }, {
      id: 8,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E7%A5%A8%E6%8D%AE.png',
      // text: '票据市场' 

    }, {
      id: 9,
        image_a: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/%E5%85%B6%E5%AE%83.png',
      // text: '其他类' 

    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    api.getAd.call(this, 'index', (res) => {
      this.setData({
        image: res.ad.image
      })
    })
  },
  catchTapCategory: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id //获取当前文章id
    var goodsId = e.currentTarget.dataset.goodsid;
    console.log('goodsId:' + goodsId);
    if ("0" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './cardinal/cardinal?goodsId=' + goodsId + '&marketType=GOLD_MARKET'
      })
    }
    if ("1" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './stair/stair?goodsId=' + goodsId + '&marketType=PRIMARY_MARKET'
      })
    }
    if ("2" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './financial/financial?goodsId=' + goodsId + '&marketType=FINANCIAL_LICENSE'
      })
    }
    if ("3" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './government/government?goodsId=' + goodsId + '&marketType=SECONDARY_MARKET'
      })
    }
    if ("4" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './wholesale/wholesale?goodsId=' + goodsId + '&marketType=LARGE_SHORT_BREAK'
      })
    }
    if ("5" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './fund/fund?goodsId=' + goodsId + '&marketType=FUND_SERVICE'
      })
    }
    if ("6" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './property/property?goodsId=' + goodsId + '&marketType=BAD_ASSETS'
      })
    }
    if ("7" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './security/security?goodsId=' + goodsId + '&marketType=ASSET_SECURITIZATION'
      })
    }
    if ("8" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './note/note?goodsId=' + goodsId + '&marketType=PAPER_MARKET'
      })
    }
    if ("9" == e.currentTarget.dataset.goodsid) {
      wx.navigateTo({
        url: './second/second?goodsId=' + goodsId + '&marketType=OTHERS'
      })
    }
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function() {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function() {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function() {

  }
})