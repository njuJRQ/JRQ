// pages/community/community.js
//获取应用实例
const app = getApp();
var api = require('../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    articles: [{
      id: 1,
      content: '《有效识别金融项目》课程。',
      images: [
        '../../default/default-pic.png',
        '../../default/default-pic.png',
        '../../default/default-pic.png'
      ],
      writerOpenid: '123',
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
   * 生命周期函数--监听页面加载a
   */
  onShow: function (options) {
  //展示圈子文章
    //api.getAbstractList('feed', app.getOpenid(), this)
    api.getFeedList(this)
  },
  onPullDownRefresh: function () {
    this.onShow()
  },
  onShare: function () {
    console.log('on share')
  },
  //点赞数加一
  likePlus: function (event) {
    var id = event.currentTarget.dataset.id //获取当前文章id
    var article = this.getCurrentArticle(id) //获取当前文章
    api.likePlus(app.getOpenid(), 'feed', id, {
      article: article,
      that: this
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