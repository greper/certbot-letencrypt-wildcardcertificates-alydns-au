
module.exports = {
  alias: {
  },
  optimizeDeps: {
    include: ['@ant-design/colors','@ant-design/icons-vue','@ant-design','ant-design-vue'],


  },
  proxy: {
    '/api': {
      target: "http://localhost:8080",
      changeOrigin: true,
      // target: 'http://qiniu.veryreader.com/D2CrudPlusExample',
      ws: true
    }
  },
  cssPreprocessOptions: {
    less: {
      javascriptEnabled: true
    }
  }
}

