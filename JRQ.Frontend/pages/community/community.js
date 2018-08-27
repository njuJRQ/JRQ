// pages/community/community.js
//获取应用实例
const app = getApp();

Page({

  /**
   * 页面的初始数据
   */
  data: {
    articles: [{
      id: 1,
      text: '《有效识别金融项目》课程。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '锄禾日当午',
      date: '2020-01-01',
      likeNum: 8888
    }, {
      id: 2,
      text: '与钧融资本成功签订2个亿的基金合约，环保领域。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '汗滴禾下土',
      date: '2020-01-01',
      likeNum: 9999
    }, {
      id: 3,
      text: '《有效识别金融项目》课程。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerFace: '../../default/default-icon.png',
      writerName: '锄禾日当午',
      date: '2020-01-01',
      likeNum: 8888
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showAll();
  },
  onShare: function () {
    console.log('on share')
  },
  //展示所有文章
  showAll: function () {
    /**
     * 方法：getArticles
     * 参数：
     * 无
     */
    wx.request({
      url: app.globalData.backendUrl + "getArticles",
      data: {
        kind: 'all'
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          articles: res.data.articleList
        })
      }
    })
  },
  //点赞数加一
  likePlus: function (event) {
    //获取当前文章id
    var id = event.currentTarget.dataset.id;
    //获取当前文章
    var article = this.getCurrentArticle(id);
    /**
     * 方法：likePlus
     * 参数：
     * id: id
     * username: username
     */
    wx.request({
      url: app.globalData.backendUrl + "likePlus",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        article.likeNum++;
        this.setData(this.data)
      }
    })
  },
  //获取当前文章
  getCurrentArticle: function (id) {
    var that = null;
    for (var i = 0; i < this.data.articles.length; i++) {
      if (this.data.articles[i].id == id)
        that = this.data.articles[i];
    }
    return that;
  }
})