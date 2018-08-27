// pages/modifyMyCard/modifyMyCard.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    oldInfo: {
      face: '../../../default/default-pic.png',
      username: 'USERNAME',
      phone: '13952146595',
      email: '13952146595@163.com',
      company: '美国永辉公司',
      department: 'IT技术部',
      position: 'T1初级经理',
      intro: '我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。我要在代码的世界里飞翔。'
    }
  },
  updateName: function (e) {
    this.data.name = e.detail.value;
  },
  updatePhone: function (e) {
    this.data.phone = e.detail.value;
  },
  updateEmail: function (e) {
    this.data.email = e.detail.value;
  },
  updateCompany: function (e) {
    this.data.company = e.detail.value;
  },
  updateDepartment: function (e) {
    this.data.department = e.detail.value;
  },
  updatePosition: function (e) {
    this.data.position = e.detail.value;
  },
  updateIntro: function (e) {
    this.data.intro = e.detail.value;
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  onSave: function(){
    console.log('save');
    /**
     * 方法：modifyMyInfo
     * 参数：
     * 用户头像：face
     * 用户名：username
     * 电话：phone
     * 邮箱：email
     * 公司：company
     * 部门：department
     * 职位：position
     * 个人简介：intro
     */
    wx.request({
      url: app.globalData.backendUrl + "modifyMyInfo",
      data: {
        openId: app.getOpenId(),
        face: "",
        username: this.data.username,
        phone: this.data.phone,
        email: this.data.email,
        company: this.data.company,
        department: this.data.department,
        position: this.data.position,
        intro: this.data.intro
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        //do nothing
      }
    })
  }
})