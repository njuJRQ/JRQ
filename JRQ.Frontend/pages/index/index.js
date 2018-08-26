//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    articles: [],
    ad: ""
  },
  //事件处理函数
  //onLoad函数
  onLoad: function () {
    //展示广告
    this.showAd();
    //展示所有文章
    this.showAll();
  },
  //展示广告
  showAd() {
    var fakeData = '../../default/default-pic.png';
    this.setData({
      ad: fakeData
    })

    /*
     * 方法：getAd
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          ad: res.data.ad
          //ad: fakeData
        })
      }
    })
  },
  //展示所有文章
  showAll: function() {
    // 测试用假数据
    var fakeData = [{
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
    }];

    this.setData({
      articles: fakeData
    });

    /*
     * 方法：getIndexAllArticles
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          articles: res.data.articleList
          //articles: fakeData
        })
      }
    })
  },
  //展示课程
  showCourses: function() {
    console.log('show courses');
    
    /*
     * 方法：getIndexCourseArticles
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          articles: res.data.articleList
          //articles: fakeData
        })
      }
    })
  },
  //展示文档
  showDocuments: function() {
    console.log('show documents');
    
    /*
     * 方法：getIndexDocumentArticles
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          articles: res.data.articleList
          //articles: fakeData
        })
      }
    })
  },
  //展示项目
  showProjects: function() {
    console.log('show projects');
    
    /*
     * 方法：getIndexProjectArticles
     * 参数：
     * 无
     */

    wx.request({
      url: app.globalData.backendUrl + "",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        this.setData({
          articles: res.data.articleList
          //articles: fakeData
        })
      }
    })
  },
  //展示文章详情(TODO)
  onTouchThisArticle: function(event) {
    //获取当前文章id
    var id = event.currentTarget.dataset.id;
    console.log(id);
  },
  //点赞数加一
  likePlus: function(event) {
    //获取当前文章id
    var id = event.currentTarget.dataset.id;
    //获取当前文章
    var article = this.getCurrentArticle(id);
    /*
     * 方法：likePlus
     * 参数：
     * id: id
     * username: username
     */
    wx.request({
      url: app.globalData.backendUrl + "",
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