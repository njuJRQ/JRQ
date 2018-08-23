//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    articles: [{
        text: '《有效识别金融项目》课程。',
        images: [
          '../../default/default-pic.png',
          '../../default/default-pic.png',
          '../../default/default-pic.png'
        ],
        writerFace:  '../../default/default-icon.png',
        writerName: '锄禾日当午',
        date: '2020-01-01',
        likeNum: 8888
      },
      {
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
      }
    ]
  },
  //事件处理函数
  //onLoad函数
  onLoad: function() {
    this.data.articles.push({
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
    })
    this.setData(this.data)
  },
  //展示课程
  showCourses: function() {
    console.log('show courses');
  },
  //展示文档
  showDocuments: function() {
    console.log('show documents');
  },
  //展示项目
  showProjects: function() {
    console.log('show projects');
  }
})