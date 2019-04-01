var articleItem = {
  //图片点击事件
  previewImg: function(event) {
    var src = event.currentTarget.dataset.src; //获取data-src
    var imgList = event.currentTarget.dataset.list; //获取data-list
    //图片预览
    wx.previewImage({
      current: src, // 当前显示图片的http链接
      urls: imgList // 需要预览的图片http链接列表
    })
  },

  onClickThisFace: function(event) {
<<<<<<< HEAD
    
    var id = event.currentTarget.dataset.id
    console.log(id)
=======
    console.log(event.currentTarget.dataset)
    var id = event.currentTarget.dataset.id.writerOpenid
>>>>>>> 17b369c5b1690b3abc9f7906a9fd0a6f81328a23
    wx.navigateTo({
      url: '../me/myHistory/myHistory?id=' + id,
    })
  }
}

export default articleItem;