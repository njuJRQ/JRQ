//index.js
//获取应用实例
const app = getApp()

Page({
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
      }],
    ad: ""
  },
  //事件处理函数
  //onLoad函数
  onLoad: function () {
    //展示广告
    this.showAd();
    //展示所有文章
    this.showArticles('all');
  },
  //展示广告
  showAd() {
    var fakeData = '../../default/default-pic.png';
    this.setData({
      ad: fakeData
    })

    /**
     * 方法：getAd
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "getAd",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          ad: res.data.ad
        })
      }
    })
  },
  //展示文章
  showArticles: function(kind) {
    /**
     * 方法：getArticles
     * 参数：
     * 文章类别：kind
     */
    wx.request({
      url: app.globalData.backendUrl + "getArticles",
      data: {
        kind: kind
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
  //展示课程
  showCourses: function() {
    console.log('show courses');
    this.showArticles('course');
  },
  //展示文档
  showDocuments: function() {
    console.log('show documents');
    this.showArticles('document');
  },
  //展示项目
  showProjects: function() {
    console.log('show projects');
    this.showArticles('project');
  },
  //展示文章详情
  onTouchThisArticle: function(event) {
    //获取当前文章id
    var id = event.currentTarget.dataset.id;
    console.log(id);
    //TODO
  },
  //点赞数加一
  likePlus: function(event) {
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
      data: {
        id: id,
        openId: app.getOpenId()
      },
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
  getCurrentArticle: function(id) {
    var that = null;
    for(var i = 0; i < this.data.articles.length; i++) {
      if (this.data.articles[i].id == id)
        that = this.data.articles[i];
    }
    return that;
  }
})