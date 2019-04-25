// pages/particulars/association/association.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    header_img:'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E7%AD%89%E5%BE%85%E4%BD%A0%E7%9A%84%E5%8A%A0%E5%85%A5.png',
    mian_imgA:'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E8%8B%8F%E5%B7%9E.png',
    mian_imgB:'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E4%B8%8A%E6%B5%B7.png',
    mian_imgC:'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%8C%97%E4%BA%AC.png',
    mian_imgD: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%8C%97%E4%BA%AC.jpg',
    mian_imgE: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%8D%97%E4%BA%AC.jpg',
    mian_imgF: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%8E%A6%E9%97%A8.jpg'
    , 
    mian_imgG: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%90%88%E8%82%A5.jpg'
    , 
    mian_imgH: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%A4%A7%E8%BF%9E.jpg',
    mian_imgI: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%A4%A9%E6%B4%A5.jpg',
    mian_imgJ: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E5%AE%81%E6%B3%A2.jpg',
    mian_imgK: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E6%AD%A6%E6%B1%89.jpg',
    mian_imgL: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E6%B2%88%E9%98%B3.jpg',
    mian_imgM: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E8%A5%BF%E5%AE%89.jpg',
    mian_imgN: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E9%87%8D%E5%BA%86.jpg',
    mian_imgO: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E9%95%BF%E6%B2%99.jpg',
    mian_imgP: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E9%9D%92%E5%B2%9B.jpg',
    mian_imgQ: 'https://image-s1.oss-cn-shanghai.aliyuncs.com/junrongquan/4.2/%E8%8B%8F%E5%B7%9E.png'
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  letShowUserTagsModal2: function () {
    this.setData({
      showUserTagsModal2: true
    })
  },

  userTagsModalCancel: function () {
    this.setData({
      showUserTagsModal: false,
      showUserTagsModal2: false,
    })
    console.log(this.data.showUserTagsModal)
  },
  userTagsModalConfirm2: function () {
    this.setData({
      showUserTagsModal2: false
    })
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