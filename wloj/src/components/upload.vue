<template>
  <el-upload
  class="el-upload-m"
  ref="upload"
  :action="UploadUrl()"
  :before-upload="beforeUpload"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  :auto-upload="false"
  :data="uploadData">
  <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
  <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">提交</el-button>
  </el-upload>
</template>
<script>
export default {
  data () {
    return {
      fileList: [],
      uploadData: {
        curouse: 1,
        username: 'U201414808'
      }
    }
  },
  methods: {
    submitUpload () {
      this.$refs.upload.submit()
    },
    handleRemove (file, fileList) {
      console.log(file, fileList)
    },
    handlePreview (file) {
      console.log(file)
    },
    UploadUrl: function () {
      return 'http://localhost/wloj/file/upload'
    }
  }
}
</script>
<style>
.el-upload-m {
    height: 280px;
    margin: 0 0 0 0;
  }
</style>
