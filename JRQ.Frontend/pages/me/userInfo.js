// pages/me/userInfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    myInfo: {
      // writerface:'../../default/default-icon.png',
      axis: [
        {
          time: '2019',
          name: '张三',
          event: '垃圾太多'
        },
        {
          time: '2-18',
          name: '王三',
          event: '垃圾太多'
        },
        {
          time: '2-17',
          name: '张三',
          event: '垃圾太多'
        },
        {
          time: '2-16',
          name: '张三',
          event: '垃圾太多'
        }],

      writerface:[
        '/pages/me/img/writerface.jpg',
        '/pages/me/img/huiyuan-icon.png',
        '/pages/me/img/bianjian-icon.png',
        '../../default/default-pic.png',
        '/pages/me/img/VIP-icon.png'

      ],
      username: 'EMILY',
      position: '财务',
      company: '北京大同信托有限公司',
      
      label: ['内构重组', '短融过桥', '大宗交易', '银行业务', '包里融租']
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})