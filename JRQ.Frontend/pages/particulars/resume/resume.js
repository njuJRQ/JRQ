// pages/particulars/resume/resume.js
const app = getApp();
var api = require('../../../util/api.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    uid:'',
    jid:'',

    isPreviewing: false,   //是否处于预览模式

    icon_user: '/img/icon_user.png',
    icon_age: '/img/icon_age.png',
    icon_eduo: "/img/icon_eduo.png",

    resume : {
      name: '',
      face: "/img/user.png",
      isFresh: false,
      degree: '',
      age: 0,
      phone: '',
      email: '',
      experience: 0,
      expectPosition: '',
      expectCity: '',
      lowWage: 0,
      highWage: 0,
      internship: [],
      education: [],
    },

    freshOrNot: ["非应届生", "应届生"],
    degrees: ['高中','大专','本科','硕士','博士'],
    degreeIndex: 0,
    years: [],
    currentYear:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.data.uid=options.uid;
    this.data.jid=options.jid;

    /* 获取数据 */
    api.getResume.call(this, options.uid, options.jid, () => {
      /* 初始化处理, 顺序不要颠倒！！！ */
      this.ensureInternAndEducation();   // 保证至少有一个education和internship
      this.initialExpectation(options);   // 根据职位信息填充预期职位和预期工作地点
      this.initialYears();  // 初始化年份数组和教育经历的年份picker选中信息
      this.initialDegreeIndex();  // 为所有学历字段添加当前选择的索引信息

      var that = this;
      api.getMyInfo.call(that, app.getOpenid(), () => {
        /* 若之前未填写，则自动获取手机和邮箱 */
        console.log(that.data.myInfo)
        if (that.data.resume.phone == '') {
          that.data.resume.phone = that.data.myInfo.phone;
        }
        if (that.data.resume.email == '') {
          that.data.resume.email = that.data.myInfo.email;
        }
        that.setData(that.data)
      })
    });
  },

  ensureInternAndEducation: function (){
    if(this.data.resume.internship.length == 0){
      this.data.resume.internship.push({ company: "", fromTime: '', toTime: '', position: '', grade: '', content: '' });
    }
    if (this.data.resume.education.length == 0) {
      this.data.resume.education.push({ school: "", fromTime: '', toTime: '', degree: '', major: '' });
    }
  },

  initialExpectation: function (options){
    //console.log(options);
    this.data.resume.expectPosition = options.expectPosition;
    this.data.resume.expectCity = options.city;
    this.setData(this.data);
  },

  initialDegreeIndex: function () {
    this.data.degreeIndex=this.data.degrees.indexOf(this.data.resume.degree);
    for (var i = 0; i < this.data.resume.education.length;i++){
      // 添加一个degreeIndex
      this.data.resume.education[i].degreeIndex = this.data.degrees.indexOf(this.data.resume.education[i].degree);
    }
    this.setData(this.data);
  },

  initialYears: function(){
    var date = new Date(Date.parse(new Date()));
    // 获取当前年份  
    var currentYear = date.getFullYear();
    this.data.currentYear = currentYear.toString();
    // 初始化可能的年份数组，因为picker的value是数组的下标，为了统一只能从0开始
    this.data.years = this.getLoopArray(0, currentYear + 4); 
    
    // 对教育经历中的年份为空的进行预先选择当前年份
    for(var i = 0;i<this.data.resume.education.length;i++){
      if (this.data.years.indexOf(this.data.resume.education[i].fromTime == -1)) {
        this.data.resume.education[i].fromTime = currentYear.toString();
      }
      if (this.data.years.indexOf(this.data.resume.education[i].toTime == -1)) {
        this.data.resume.education[i].toTime = currentYear.toString();
      }
    }

    this.setData(this.data);
  },

  getLoopArray: function (start, end) {
    var start = start || 0;
    var end = end || 1;
    var array = [];
    for(var i = start; i <= end; i++) {
      array.push(i);
    }
    return array;
  },

  changeDate: function(event){
    var i = event.currentTarget.dataset.index;
    if (event.currentTarget.dataset.kind == 'from'){
      //把2018-01-01 替换为 2018.01.01
      this.data.resume.internship[i].fromTime=event.detail.value.replace(/-/g,'.');
    }
    else if(event.currentTarget.dataset.kind == 'to'){
      this.data.resume.internship[i].toTime = event.detail.value.replace(/-/g, '.');
    }
    this.setData(this.data);
  },

  changeYear: function (event) {
    var i = event.currentTarget.dataset.index;
    if (event.currentTarget.dataset.kind == 'from') {
      this.data.resume.education[i].fromTime = event.detail.value;
    }
    else if (event.currentTarget.dataset.kind == 'to') {
      this.data.resume.education[i].toTime = event.detail.value;
      //数组不从0开始则this.data.resume.education[i].toTime = this.data.years[event.detail.value]
    }
    this.setData(this.data);
  },

 
  changeFresh: function (event) {
    this.data.resume.isFresh = event.detail.value == 1 ? true : false;
    this.setData(this.data);
  },

  changeDegree: function (event) {
    this.data.degreeIndex = event.detail.value;
    this.data.resume.degree = this.data.degrees[this.data.degreeIndex];
    this.setData(this.data);
  },

  changeItemDegree:function(event){
    var i = event.currentTarget.dataset.index;
    this.data.resume.education[i].degreeIndex = event.detail.value;
    this.data.resume.education[i].degree = this.data.degrees[event.detail.value];
    this.setData(this.data);
  },

  updateName: function (e) {
    this.data.resume.name = e.detail.value;
  },
  updateAge: function (e) {
    this.data.resume.age = e.detail.value;
  },
  updatePhone: function (e) {
    this.data.resume.phone = e.detail.value;
  },
  updateEmail: function (e) {
    this.data.resume.email = e.detail.value;
  },
  updateExperience: function (e) {
    this.data.resume.experience = e.detail.value;
  },
  updateLow: function (e) {
    this.data.resume.lowWage = e.detail.value;
  },
  updateHigh: function (e) {
    this.data.resume.highWage = e.detail.value;
  },
  updateCompany: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.internship[i].company = e.detail.value;
  },
  updatePosition: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.internship[i].position = e.detail.value;
  },
  updateGrade: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.internship[i].grade = e.detail.value;
  },
  updateContent: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.internship[i].content = e.detail.value;
  },
  updateSchool: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.education[i].school = e.detail.value;
  },
  updateMajor: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.education[i].major = e.detail.value;
  },

  preview: function(){
    this.data.isPreviewing = !this.data.isPreviewing;
    this.setData(this.data);
  },
  
  save: function () {
    // 只保存不需要检查信息完整性
    console.log(this.data.resume.education)
    /* api.js中showToast提示是否成功 */
    api.saveResume.call(this)
  },

  saveAndSend: function (){
    var that = this;
    if (this.checkInfomation()) {
      console.log(this.data.resume)
      wx.showModal({
        title: '提示',
        content: '是否确认投递简历？',
        success: function (res) {
          if (res.confirm) {
            api.saveResumeAndSend.call(that)
          } 
        }
      })
    }
  },

  checkInfomation: function (){
    /* 检查输入合法性 */
    if (typeof (this.data.resume.name) == undefined || this.data.resume.name==''){
      wx.showToast({
        title: '请填写姓名',
        icon: 'none'
      })
      return false;
    } 
    if (typeof (this.data.resume.degree) == undefined || this.data.resume.degree == '') {
      wx.showToast({
        title: '请填写最高学历',
        icon: 'none'
      })
      return false;
    }
    if (typeof (this.data.resume.phone) == undefined || this.data.resume.phone == '') {
      wx.showToast({
        title: '请填写手机',
        icon: 'none'
      })
      return false;
    }
    if (typeof (this.data.resume.email) == undefined || this.data.resume.email == '') {
      wx.showToast({
        title: '请填写邮箱',
        icon: 'none'
      })
      return false;
    }
    if (!/^1[34578]\d{9}$/.test(this.data.resume.phone)) {
      wx.showToast({
        title: '手机号码有误，请重填',
        icon: 'none'
      })
      return false;
    }
    if (!/^\w+((.\w+)|(-\w+))@[A-Za-z0-9]+((.|-)[A-Za-z0-9]+).[A-Za-z0-9]+$/.test(this.data.resume.email)) {
      wx.showToast({
        title: '邮箱地址有误，请重填',
        icon: 'none'
      })
      return false;
    }
    return true;
  },

  addInternship:function(){
    this.data.resume.internship.push({ company: "", fromTime: '', toTime: '', position: '', grade: '', content: '' });
    this.setData(this.data);
  },

  deleteInternship: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.internship.splice(i,1);
    this.setData(this.data);
  },

  addEducation: function () {
    /* 这里要直接对fromTime ， toTime ， degreeIndex 做初始化!!! */
    this.data.resume.education.push({ school: "", fromTime: this.data.currentYear, toTime: this.data.currentYear, degree: '', major: '' ,degreeIndex:-1});
    this.setData(this.data);
  },

  deleteEducation: function (e) {
    var i = e.currentTarget.dataset.index;
    this.data.resume.education.splice(i, 1);
    this.setData(this.data);
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