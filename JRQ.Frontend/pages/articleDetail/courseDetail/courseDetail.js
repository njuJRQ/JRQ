// pages/articleDetail/articleDetail.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    course: {
      id: 1, //编号
      name: "《有效识别金融项目》课程。", //标题
      image: '../../../default/default-pic.png', //图片
      writerName: '锄禾日当午', //作者名字
      date: '2018-1-1', //日期
      likeNum: 999, //点赞数
      video: 'http://www.w3school.com.cn/i/movie.ogg', //视屏url
      price: 998, //价格
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    api.getCourse(options.id, this) //获取课程详情页
  },
  //购买该课程
  onPurchase: function () {
    var that = this
    wx.showModal({
      title: '确认购买',
      content: '确认以' + that.data.course.price + '的价格购买\r\n' + that.data.course.name +'\r\n吗？',
      success: (res) => {
        //TODO：该接口不安全
        console.log('purchase course')
        api.purchaseCourse(that.data.course.id, app.getOpenid(), that)
      }
    })
  }
})