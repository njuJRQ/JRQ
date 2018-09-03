const app = getApp()

function getAbstractList(kind, openid, that) {
  wx.request({
    url: app.globalData.backendUrl + "getAbstractList",
    data: {
      kind: kind,
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      console.log(res.data)
      that.setData({
        articles: res.data.abstractList
      })
    }
  })
}

function getFeedList(that) {
  wx.request({
    url: app.globalData.backendUrl + "getFeedList",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      console.log(res.data)
      that.setData({
        articles: res.data.feeds
      })
    }
  })
}

function getCourse(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getCourse",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        course: res.data.course
      })
    }
  })
}

function getDocument(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getDocument",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        document: res.data.document
      })
    }
  })
}

function getProject(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getProject",
    data: {
      id: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        project: res.data.project
      })
    }
  })
}

function getAd(that) {
  /**
   * 方法：getAd
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getCheckedAd",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        ad: res.data.ad,
        adLink: res.data.link
      })
    }
  })
}

function likePlus(openid, kind, articleId, context) {
  wx.request({
    url: app.globalData.backendUrl + "likePlus",
    data: {
      openid: openid,
      kind: kind,
      articleId: articleId
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      context.that.onLoad()
    }
  })
}

function purchaseCourse(courseId, openid, that) {
  wx.request({
    url: app.globalData.backendUrl + "purchaseCourse",
    data: {
      courseId: courseId,
      openid: openid
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

function getPersonList(kind, that, then) {
  wx.request({
    url: app.globalData.backendUrl + "getPersonList",
    data: {
      workClass: kind
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.persons
      })
      if (then) then()
    }
  })
}

function getMyInfo(openid, that, then) {
  /**
   * 方法：getUser
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyUser",
    data: {
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        newMyInfo: res.data.user
      })
      that.setData({
        myInfo: res.data.user
      })
      if (then) then()
    }
  })
}

function getOtherBasicInfo(id, that) {
  wx.request({
    url: app.globalData.backendUrl + "getPerson",
    data: {
      openid: id
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      /*console.log(res)*/
      that.setData({
        myInfo: res.data.person
      })
    }
  })
}

function getOtherInfo(myid, otherid, that, then) {
  /**
   * 方法：getOtherCard
   * 参数：
   * 无
   */
  wx.request({
    url: app.globalData.backendUrl + "getOtherCard",
    data: {
      userOpenid: myid,
      otherOpenid: otherid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      console.log(res)
      if (res.statusCode == 200) {
        that.setData({
          myInfo: res.data.card
        })
        if (then) then()
      }
      else if (res.statusCode == 500) {
        wx.showModal({
          title: res.data.error,
          content: res.data.message,
          showCancel: false
        })
      }
    }
  })
}

function checkMyReceivedCard(senderOpenid, receiverOpenid) {
  wx.request({
    url: app.globalData.backendUrl + "checkMyReceivedCard",
    data: {
      senderOpenid: senderOpenid,
      receiverOpenid: receiverOpenid
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

function publishMyArticle(openid, kind, content, photos, that) {
  //TODO
  /**
   * 方法：publishMyFeed
   * 参数：
   * 文本内容：content
   */
  var date = new Date()
  wx.request({
    url: app.globalData.backendUrl + "publishMyFeed",
    data: {
      writerOpenid: openid,
      kind: kind,
      date: [date.getFullYear(), date.getMonth() + 1, date.getDate()].join('-'),
      content: content,
      images: photos
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      wx.showToast({
        title: '发布成功',
        icon: 'succes',
        duration: 1000,
        success: () => {
          setTimeout(() => { wx.navigateBack() }, 1000)
        },
        mask: true
      })
    }
  })
}

function modifyMyInfo(that) {
  /**
   * 方法：updateUser
   * 参数：
   * 用户头像：face
   * 用户名：username
   * 电话：phone
   * 邮箱：email
   * 城市：city
   * 公司：company
   * 部门：department
   * 职位：position
   * 个人简介：intro
   */
  console.log(that.data.newMyInfo.face)
  wx.uploadFile({
    url: app.globalData.backendUrl + "uploadHead",
    filePath: that.data.newMyInfo.face,
    name: 'face',
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: (res) => {
      wx.request({
        //上传用户信息
        url: app.globalData.backendUrl + "updateMyProfile",
        data: {
          openid: app.getOpenid(),
          username: that.data.newMyInfo.username,
          phone: that.data.newMyInfo.phone,
          email: that.data.newMyInfo.email,
          city: that.data.newMyInfo.city,
          company: that.data.newMyInfo.company,
          department: that.data.newMyInfo.department,
          position: that.data.newMyInfo.position,
          intro: that.data.newMyInfo.intro,
          label: that.data.newMyInfo.label
        },
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: (res) => {
          wx.showToast({
            title: '修改成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
        }
      })
    }
  })
}

function getPersonListByCondition(condition, that) {
  /**
   * 方法：searchCards
   * 参数：
   * 搜索条件：condition
   */
  wx.request({
    url: app.globalData.backendUrl + "getPersonListByCondition",
    data: {
      condition: that.data.searchCondition
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.persons
      })
    }
  })
}

function getMyPersonList(openid, kind, that) {
  /**
   * 方法：getMyPersonList
   * 参数：
   * 用户openId：openId
   * 展示类别：kind
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyCardList",
    data: {
      openid: openid,
      kind: kind
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        cards: res.data.persons
      })
    }
  })
}

function getMyHistoryAbstractList(openid, that) {
  /**
   * 方法：getMyHistoryAbstractList
   * 参数：用户openId：openId
   */
  wx.request({
    url: app.globalData.backendUrl + "getMyHistoryAbstractList",
    data: {
      openid: openid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        myArticles: res.data.abstractList
      })
    }
  })
}

function downloadFile(filepath) {
  wx.downloadFile({
    url: filepath,
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: (res) => {
      wx.saveFile({
        tempFilePath: res.tempFilePath
      })
    }
  })
}

function getClassificationList(that) {
  wx.request({
    url: app.globalData.backendUrl + "getClassificationList",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.setData({
        labelArray: res.data.classifications.map((c) => c.userLabel)
      })
    }
  })
}

module.exports = {
  getAbstractList: getAbstractList,
  getFeedList: getFeedList,
  getCourse: getCourse,
  getDocument: getDocument,
  getProject: getProject,
  getAd: getAd,
  likePlus: likePlus,
  purchaseCourse: purchaseCourse,
  getPersonList: getPersonList,
  getMyInfo: getMyInfo,
  getOtherBasicInfo: getOtherBasicInfo,
  getOtherInfo: getOtherInfo,
  checkMyReceivedCard: checkMyReceivedCard,
  publishMyArticle: publishMyArticle,
  modifyMyInfo: modifyMyInfo,
  getPersonListByCondition: getPersonListByCondition,
  getMyPersonList: getMyPersonList,
  getMyHistoryAbstractList: getMyHistoryAbstractList,
  downloadFile: downloadFile,
  getClassificationList: getClassificationList
}