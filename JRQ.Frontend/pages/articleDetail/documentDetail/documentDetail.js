// pages/articleDetail/documentDetail/documentDetail.js
const app = getApp()
var api = require('../../../util/api.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    document: {
      id: 3,
      title: '这是一个文档标题',
      content: '这是文档的内容',
      writerName: '锄禾日当午',
      date: '2018-1-1',
      likeNum: 999
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    try {
      api.getDocument.call(this, options.id)
    } catch (e) {
      console.log('获取编号为' + options.id + '的文档失败')
    }
  }
})