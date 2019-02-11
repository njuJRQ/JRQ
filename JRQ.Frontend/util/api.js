const app = getApp()
const util = require('./util.js')

function getAbstractList(kind, openid, lastId, lastIdType) {
  return new util.Promise((resolve, reject) => {
    var that = this
    wx.showLoading({
      title: '载入中',
    })
    wx.request({
      url: app.globalData.backendUrl + "getAbstractListBefore",
      data: {
        kind: kind,
        openid: openid,
        articleId: lastId,
        articleType: lastIdType
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          resolve(res.data.abstractList)
        } else {
          reject()
        }
      }
    })
  })
}

function getAbstractListByCondition(openid, condition) {
  return new util.Promise((resolve, reject) => {
    if (condition == "") {
      condition = null
    }
    wx.showLoading({
      title: '搜索中',
    })
    wx.request({
      url: app.globalData.backendUrl + "getAbstractListByCondition",
      data: {
        openid: openid,
        condition: condition
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          wx.hideLoading()
          let articles = res.data.abstractList
          articles.forEach((article) => {
            article.images = article.images.map((image) => app.globalData.picUrl + image)
          })
          resolve(articles)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}
// function getFeedList(kind,openid, id) {
//   var that = this
//   wx.showLoading({
//     title: '载入中',
//   })

//   wx.request({
//     url: app.globalData.backendUrl + "getFeedViewListBefore",
//     header: {
//       'Authorization': 'Bearer ' + app.getToken(),
//       'content-type': 'application/x-www-form-urlencoded'
//     },
//     data: {
//       kind: kind,
//       openid: openid,
//       id: id
//     },
//     method: 'GET',
//     success: (res) => {
//       wx.hideLoading()
//       console.log(res)
//       if (res.statusCode == 200) {
//         var articles = res.data.feedViews
//         if (articles.length <= 0) {
//           return
//         }
//         articles.forEach((article) => {
//           article.writerFace = app.globalData.picUrl + article.writerFace
//           article.images = article.images.map((image) => {
//             return app.globalData.picUrl + image
//           })
//         })
//         switch (kind) {
//             case 'latest': article.kindName = "最热";break;
//             case 'weekly': article.kindName = "一周内"; break;
//             case 'monthly': article.kindName = "一月内"; break;
//             default: break;
//           }
//         that.data.articles = that.data.articles.concat(res.data.feedViews)
//         var articles = that.data.articles
//         that.data.lastId = articles[articles.length - 1].id
//         that.setData(that.data)
//       } else if (res.statusCode == 500) {
//         wx.showModal({
//           title: res.data.error,
//           content: res.data.message,
//           showCancel: false
//         })
//       }
//     },
//     fail: (res) => {
//       wx.hideLoading()
//       wx.showToast({
//         title: '服务器未连接',
//         icon: 'none'
//       })
//     }
//   })
// }
function getFeedListl(kind, openid, id) {
  var that = this
  wx.showLoading({
    title: '载入中',
  })
  wx.request({
    url: app.globalData.backendUrl + "getFeedListBeforeByKind",
    // url: app.globalData.backendUrl + "getFeedListByLikeNum",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    data: {
      kind: kind,
      openid: openid,
      // articleId: lastId,
      // articleType: lastIdType,
      id: id
    },

    method: 'GET',
    success: (res) => {
      wx.hideLoading()
      /*console.log(res)*/
      if (res.statusCode == 200) {
        var articles = res.data.feedViews
        if (articles.length <= 0) {
          return
        }
        articles.forEach((article) => {
          console.log(article)
          article.writerFace = app.globalData.picUrl + article.writerFace
          // console.log(article.writerFace)

          article.images = article.images.map((image) => {
            return app.globalData.picUrl + image
          })
          article.kindName = "最热";
          switch (article.kind) {
            case 'latest':
              article.kindName = "最热";
              break;
            case 'weekly':
              article.kindName = "一周内";
              break;
            case 'monthly':
              article.kindName = "一月内";
              break;
            default:
              break;
          }
        })

        that.data.articles = that.data.articles.concat(res.data.feedViews)
        var articles = that.data.articles
        that.data.id = articles[articles.length - 1].id
        //that.data.lastIdType = articles[articles.length - 1].kind
        that.setData(that.data)
      } else if (res.statusCode == 500) {
        wx.showModal({
          title: res.data.error,
          content: res.data.message,
          showCancel: false
        })
      }
    },
    fail: (res) => {
      wx.hideLoading()
      wx.showToast({
        title: '服务器未连接',
        icon: 'none'
      })
    }
  })
}

function getFeedList(kind, openid, lastId, id) {
  if (!id) {
    id = "";
  }
  var that = this
  wx.showLoading({
    title: '载入中',
  })
  wx.request({
    url: app.globalData.backendUrl + "getFeedListBeforeByKind",
    // url: app.globalData.backendUrl + "getFeedListByLikeNum",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    data: {
      kind: kind,
      openid: openid,
      articleId: lastId,
      // articleType: lastIdType,
      id: id
    },

    method: 'GET',
    success: (res) => {
      wx.hideLoading()
      /*console.log(res)*/
      if (res.statusCode == 200) {
        var articles = res.data.feedViews
        if (articles.length <= 0) {
          return
        }
        articles.forEach((article) => {
          console.log(article)
          article.writerFace = app.globalData.picUrl + article.writerFace
          // console.log(article.writerFace)

          article.images = article.images.map((image) => {
            return app.globalData.picUrl + image
          })
          article.kindName = "最热";
          switch (kind) {
            case 'latest':
              article.kindName = "最热";
              break;
            case 'weekly':
              article.kindName = "一周内";
              break;
            case 'monthly':
              article.kindName = "一月内";
              break;
            default:
              break;
          }
        })

        that.data.articles = that.data.articles.concat(res.data.feedViews)
        var articles = that.data.articles
        that.data.lastId = articles[articles.length - 1].id
        //that.data.lastIdType = articles[articles.length - 1].kind
        that.setData(that.data)
      } else if (res.statusCode == 500) {
        wx.showModal({
          title: res.data.error,
          content: res.data.message,
          showCancel: false
        })
      }
    },
    fail: (res) => {
      wx.hideLoading()
      wx.showToast({
        title: '服务器未连接',
        icon: 'none'
      })
    }
  })
}

function getMyCourse(openid, courseId) {
  return new util.Promise((resolve, reject) => {
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: app.globalData.backendUrl + "getMyCourse",
      data: {
        openid: openid,
        courseId: courseId
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        wx.hideLoading()
        console.log(res)
        if (res.statusCode == 200) {
          let course = res.data.course
          course.image = app.globalData.picUrl + course.image
          course.video = app.globalData.picUrl + course.video
          resolve(course)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getDocument(openid,id) {
  return new util.Promise((resolve, reject) => {
    var that = this;
    wx.request({
      url: app.globalData.backendUrl + "getMyDocument",
      data: {
        openid: openid,
        documentId: id
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          res.data.document.preview = app.globalData.picUrl + res.data.document.preview
          resolve(res.data.document)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getProject(openid,id) {
  return new util.Promise((resolve, reject) => {
    var that = this
    wx.request({
      url: app.globalData.backendUrl + "getMyProject",
      data: {
        openid: openid,
        projectId: id
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          resolve(res.data.project)
        }
        else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getAd(showPlace) {
  return new util.Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.backendUrl + "getCheckedAd",
      data: {
        showPlace: showPlace
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          res.data.ad.image = app.globalData.picUrl + res.data.ad.image
          resolve(res.data.ad)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function likePlus(openid, kind, articleId, article) {
  var that = this
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
      if (article.hasLiked) {
        article.likeNum--;
      } else {
        article.likeNum++;
      }
      article.hasLiked = !article.hasLiked
      that.setData(that.data)
    }
  })
}

function purchaseCourse(courseId, openid, price, date, then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "addPurchase",
    data: {
      openid: openid,
      type: 'course',
      detail: courseId,
      price: price,
      date: date
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      console.log('购买课程结果：', res)
      if (res.data.ok == false) {
        wx.showModal({
          content: res.data.message,
          showCancel: false
        })
      } else if (res.data.ok == true) {
        wx.showModal({
          content: '购买课程成功',
          showCancel: false
        })
        getMyCourse.call(that, app.getOpenid(), that.data.course.id, () => {
          that.data.isOwnCourse = true
          that.setData(that.data)
        })
        if (then) then()
      }
    }
  })
}

function getPersonList(kind) {
  return new util.Promise((resolve, reject) => {
    wx.showLoading({
      title: '载入中',
    })
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
        wx.hideLoading()
        if (res.statusCode == 200) {
          let cards = res.data.persons
          cards.forEach((card) => {
            card.face = app.globalData.picUrl + card.face
          })
          resolve(cards)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getOtherBasicInfo(id) {
  var that = this
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
      that.data.myInfo = res.data.person
      that.data.myInfo.face = app.globalData.picUrl + res.data.person.face
      that.setData(that.data)
    }
  })
}

function getOtherInfo(myid, otherid, resolve, reject) {
  var that = this
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
      /*console.log(res)*/
      if (res.statusCode == 200) {
        if (JSON.stringify(res.data) != '{}') {
          that.data.myInfo = res.data.card
          that.data.myInfo.face = app.globalData.picUrl + that.data.myInfo.face
          that.setData(that.data)
          if (resolve) resolve()
        } else {
          // 次数和金额都不足
          if (reject) reject()
        }
      }
    }
  })
}

function checkMyReceivedCard(senderOpenid, receiverOpenid) {
  var that = this
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
      console.log(res)
    }
  })
}

function uploadImageOneByOne(photos, index, length, then) {
  console.log(photos)
  if (!photos.length) {
    then()
    return
  }
  wx.uploadFile({
    url: app.globalData.backendUrl + "uploadImage",
    filePath: photos[index],
    name: 'image',
    success: (res) => {
      index++
      if (index == length) {
        then()
        return
      }
      uploadImageOneByOne(photos, index, length, then)
    }
  })
}

function publishMyArticle(openid, kind, content, photos) {
  /**
   * 方法：publishMyFeed
   * 参数：
   * 文本内容：content
   */
  var that = this
  wx.showLoading({
    title: '发布圈子中',
  })
  uploadImageOneByOne(photos, 0, photos.length, () => {
    wx.request({
      url: app.globalData.backendUrl + "publishMyFeed",
      data: {
        writerOpenid: openid,
        kind: kind,
        date: util.getTodayDate(),
        content: content
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        console.log(res)
        wx.hideLoading()
        wx.showToast({
          title: '发布成功',
          icon: 'succes',
          duration: 1000,
          success: () => {
            setTimeout(() => {
              wx.navigateBack()
            }, 1000)
          },
          mask: true
        })
      }
    })
  })
}

function uploadHead(facePath) {
  return new util.Promise((resolve, reject) => {
    wx.showLoading({
      title: '上传中',
    })
    wx.uploadFile({
      //上传用户图片
      url: app.globalData.backendUrl + "uploadHead",
      filePath: facePath,
      name: 'face',
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      success: resolve,
      fail: reject
    })
  })
}

function modifyMyInfo(newMyInfo) {
  return new util.Promise((resolve, reject) => {
    console.log("Face is " + newMyInfo.face)
    uploadHead(newMyInfo.face).then((res) => {
      wx.request({
        //上传用户信息
        url: app.globalData.backendUrl + "updateMyProfile",
        data: newMyInfo,
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: resolve,
        fail: reject
      })
    }).catch((res) => {
      console.log(newMyInfo)
      wx.request({
        //上传用户信息
        url: app.globalData.backendUrl + "updateMyProfileWithoutFile",
        data: newMyInfo,
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: resolve,
        fail: reject
      })
    })
  })
}

function getPersonListByCondition(openid, condition) {
  return new util.Promise((resolve, reject) => {
    if (condition == "") {
      condition = null
    }
    wx.showLoading({
      title: '搜索中',
    })
    wx.request({
      url: app.globalData.backendUrl + "getPersonListByCondition",
      data: {
        openid: openid,
        condition: condition
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        wx.hideLoading()
        if (res.statusCode == 200) {
          res.data.persons.forEach((person) => {
            person.face = app.globalData.picUrl + person.face
          })
          resolve(res.data.persons)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getMyPersonList(openid, kind) {
  var that = this
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
      console.log(res)
      that.data.cards = res.data.cards
      that.data.cards.forEach((card) => {
        card.face = app.globalData.picUrl + card.face
      })
      that.setData(that.data)
    }
  })
}

function getUserHistoryAbstractList(myOpenid, otherOpenid) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "getUserHistoryAbstractList",
    data: {
      myOpenid: myOpenid,
      otherOpenid: otherOpenid
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      that.data.myArticles = res.data.abstractList
      that.data.myArticles.forEach((article) => {
        article.writerFace = app.globalData.picUrl + article.writerFace
        article.images = article.images.map((image) => app.globalData.picUrl + image)
      })
      that.setData(that.data)
    }
  })
}

function downloadFile(filepath, then) {
  var that = this
  wx.showLoading({
    title: '下载中',
  })
  wx.downloadFile({
    url: app.globalData.picUrl + filepath,
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    success: (res) => {
      wx.hideLoading()
      wx.saveFile({
        tempFilePath: res.tempFilePath,
        success: (res) => {
          that.setData({
            savedFilePath: res.savedFilePath
          })
          if (then) then()
        }
      })
    }
  })
}

function getClassificationList() {
  return new util.Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.backendUrl + "getClassificationList",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if(res.statusCode == 200) {
          resolve(res.data.classifications)
        }
        else {
          reject()
        }
      }
    })
  })
}

function setMyUserAsEnterprise(enterpriseName, description, licenseUrl, openid, username, password, then) {
  var that = this
  wx.uploadFile({
    url: app.globalData.backendUrl + "uploadLicense",
    filePath: licenseUrl,
    name: 'license',
    success: (res) => {
      // 上传license成功
      console.log(res)
      wx.request({
        url: app.globalData.backendUrl + "setMyUserAsEnterprise",
        data: {
          enterpriseName: enterpriseName,
          description: description,
          openid: openid,
          username: username,
          password: password
        },
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: (res) => {
          if (then) then(res)
        }
      })
    }
  })
}

function updateMe(openid, detail, price, date) {
  wx.request({
    url: app.globalData.backendUrl + "addPurchase",
    data: {
      openid: openid,
      type: 'level',
      detail: detail,
      price: price,
      date: date
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      wx.showToast({
        title: res.data.message,
      })
    }
  })
}

function sendMyCard(senderOpenid, receiverOpenid) {
  return new util.Promise((resolve, reject) => {
    wx.showLoading({
      title: '正在发送名片',
    })
    getMyUser(senderOpenid).then((res) => {
      console.log(res)
      wx.request({
        url: app.globalData.backendUrl + "sendMyCard",
        data: {
          senderOpenid: senderOpenid,
          receiverOpenid: receiverOpenid,
          page: "/pages/me/myHistory/myHistory?id=" + senderOpenid,
          data: {
            "keyword1": {
              "value": res.username
            },
            "keyword2": {
              "value": "您可点击查看Ta的名片，然后进入个人中心确认接收或拒绝！"
            },
            "keyword3": {
              "value": res.company
            },
            "keyword4": {
              "value": res.label
            }
          },
          emphasisKeyword: "keyword1.DATA"
        },
        header: {
          'Authorization': 'Bearer ' + app.getToken(),
          'content-type': 'application/x-www-form-urlencoded'
        },
        method: 'GET',
        success: (res) => {
          console.log(res);
          if (res.statusCode == 200) {
            resolve(res)
          } else {
            reject()
          }
        },
        fail: reject
      })
    })
  })
}

function getMyUser(openid) {
  return new util.Promise((resolve, reject) => {
    var that = this
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
        if (res.statusCode == 200) {
          res.data.user.face = app.globalData.picUrl + res.data.user.face
          resolve(res.data.user)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function isEnterprise(openid) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "isMyUserEnterprise",
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
        isEnterprise: res.data.ok
      })
    }
  })
}

function getMyEnterpriseAdmin(openid) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "getMyEnterpriseAdmin",
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
        username: res.data.admin.username,
        password: res.data.admin.password,
        date: res.data.admin.date
      })
    }
  })
}

function getMyCardLimits(openid) {
  return new util.Promise((resolve, reject) => {
    var that = this
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
        if (res.statusCode == 200) {
          resolve(res.data)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getLevelList() {
  return new util.Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.backendUrl + "getLevelList",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          resolve(res.data.levels)
        }
        else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getPrivilegeList() {
  return new util.Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.backendUrl + "getPrivilegeList",
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          resolve(res.data.privileges)
        }
        else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function isOtherCardAccessible(userOpenid, otherOpenid, then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "isOtherCardAccessible",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    data: {
      userOpenid: userOpenid,
      otherOpenid: otherOpenid
    },
    method: 'GET',
    success: (res) => {
      if (res.statusCode == 200) {
        if (then) then(res.data.ok)
      }
    }
  })
}

function getMyCourseListBefore(openid, lastCourseId) {
  var that = this
  wx.showLoading({
    title: '载入中',
  })
  wx.request({
    url: app.globalData.backendUrl + "getMyCourseListBefore",
    data: {
      openid: openid,
      id: lastCourseId
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      wx.hideLoading()
      /*console.log(res.data)*/
      res.data.courseList.forEach((article) => {
        article.image = app.globalData.picUrl + article.image
      })
      that.data.articles = that.data.articles.concat(res.data.courseList)
      var articles = that.data.articles
      that.data.lastId = articles[articles.length - 1].id
      that.setData(that.data)
    }
  })
}

function isAdminUsernameExistent(username, then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "isAdminUsernameExistent",
    data: {
      username: username
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      then(res.data.ok)
    }
  })
}

function getWxQrCode(then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "getWxQrCode",
    data: {
      scene: 'id=' + app.getOpenid(),
      page: 'pages/me/myHistory/myHistory',
      width: 200,
      autoColor: true,
      lineColorR: 0,
      lineColorG: 0,
      lineColorB: 0,
      isHyaline: true
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      if (res.statusCode == 200) {
        console.log(res)
        if (res.data.ok) {
          wx.getImageInfo({
            src: app.globalData.picUrl + res.data.imagePath,
            success: (res) => {
              if (then) then({
                imagePath: res.path
              })
            }
          })
        }
      }
    }
  })
}

function getMySubmittedEnterprise(openid) {
  return new util.Promise((resolve, reject) => {
    wx.request({
      url: app.globalData.backendUrl + "getMySubmittedEnterprise",
      data: {
        openid: openid
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        if (res.statusCode == 200) {
          resolve(res.data.enterprise)
        }
        else {
          reject()
        }
      },
      fail: reject
    })
  })
}

function getClassificationDescriptionList(then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "getClassificationDescriptionList",
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      if (res.statusCode == 200) {
        if (then) then(res.data)
      }
    }
  })
}

function getNewsListBefore(newsId, then) {
  var that = this
  wx.request({
    url: app.globalData.backendUrl + "getNewsListBefore",
    data: {
      type: "user",
      newsId: newsId
    },
    header: {
      'Authorization': 'Bearer ' + app.getToken(),
      'content-type': 'application/x-www-form-urlencoded'
    },
    method: 'GET',
    success: (res) => {
      if (res.statusCode == 200) {
        if (then) then(res.data)
      }
    }
  })
}

function uploadFormId(openid, formId) {
  return new util.Promise((resolve, reject) => {
    //if (formId == undefined || formId == "the formId is a mock one") {
    if (formId == undefined) {
      resolve()
      return
    }
    wx.request({
      url: app.globalData.backendUrl + "uploadFormId",
      data: {
        openid: openid,
        formId: formId
      },
      header: {
        'Authorization': 'Bearer ' + app.getToken(),
        'content-type': 'application/x-www-form-urlencoded'
      },
      method: 'GET',
      success: (res) => {
        console.log(res)
        if (res.statusCode == 200) {
          resolve(res.data)
        } else {
          reject()
        }
      },
      fail: reject
    })
  })
}

module.exports = {
  getAbstractList: getAbstractList,
  getAbstractListByCondition: getAbstractListByCondition,
  getFeedList: getFeedList,
  getMyCourse: getMyCourse,
  getDocument: getDocument,
  getProject: getProject,
  getAd: getAd,
  likePlus: likePlus,
  purchaseCourse: purchaseCourse,
  getPersonList: getPersonList,
  getOtherBasicInfo: getOtherBasicInfo,
  getOtherInfo: getOtherInfo,
  checkMyReceivedCard: checkMyReceivedCard,
  publishMyArticle: publishMyArticle,
  modifyMyInfo: modifyMyInfo,
  getPersonListByCondition: getPersonListByCondition,
  getMyPersonList: getMyPersonList,
  getUserHistoryAbstractList: getUserHistoryAbstractList,
  downloadFile: downloadFile,
  getClassificationList: getClassificationList,
  setMyUserAsEnterprise: setMyUserAsEnterprise,
  updateMe: updateMe,
  sendMyCard: sendMyCard,
  getMyUser: getMyUser,
  isEnterprise: isEnterprise,
  getMyEnterpriseAdmin: getMyEnterpriseAdmin,
  getMyCardLimits: getMyCardLimits,
  getLevelList: getLevelList,
  getPrivilegeList: getPrivilegeList,
  isOtherCardAccessible: isOtherCardAccessible,
  getMyCourseListBefore: getMyCourseListBefore,
  isAdminUsernameExistent: isAdminUsernameExistent,
  getWxQrCode: getWxQrCode,
  getMySubmittedEnterprise: getMySubmittedEnterprise,
  getClassificationDescriptionList: getClassificationDescriptionList,
  getNewsListBefore: getNewsListBefore,
  uploadFormId: uploadFormId
}