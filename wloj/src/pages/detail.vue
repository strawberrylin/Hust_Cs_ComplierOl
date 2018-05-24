<template>
  <el-container style="height:100vh;background-color: #E9EEF3;">
    <el-header height=40px>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple"></div></el-col>
      </el-row>
    </el-header>
    <el-container style="justify-content:center;align-items:center;">
      <div style="width:400px;height:400px;background-color:#FFFFFF;border: 20px solid #CCCCCC;">
        <label style="background-color:#969696;">源码</label>
        <p v-html="code"></p>
      </div>
      <div style="width:400px;height:400px;background-color:#FFFFFF;border: 20px solid #CCCCCC;">
        <label style="background-color:#969696;">输出</label>
        <p v-html="output"></p>
      </div>
      <div style="width:200px;height:400px;background-color:#FFFFFF;border: 20px solid #CCCCCC;direction:vertical;">
        <label style="background-color:#969696;">留言板</label>
        <el-main style="height:180px;" v-text="words">
        </el-main>
        <div style="height:180px;">
          <el-input type="textarea" v-model="comment">
          </el-input>
        </div>
        <el-button type="primary" style="width:200px;" @click="submit">确定</el-button>
      </div>
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      code: this.$route.params[4],
      output: this.$route.params[5],
      words: '',
      comment: '',
      commentT: '',
      commentS: ''
    }
  },
  methods: {
    submit: function () {
      let type = this.$route.params[6]
      if (type === '1') {
        this.commentT = this.comment
        this.commentS = this.words
      } else if (type === '2') {
        this.commentT = this.words
        this.commentS = this.comment
      }
      this.$ajxj({
        method: 'post',
        url: 'record/updatecomment',
        params: {
          usernum: this.$route.params[0],
          labnum: this.$route.params[1],
          commentT: this.commentT,
          commentS: this.commentS
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            alert(data.message)
          }
        })
    }
  },
  mounted: function () {
    let type = this.$route.params[6]
    if (type === '1') {
      this.words = this.$route.params[2]
      this.comment = this.$route.params[3]
    } else if (type === '2') {
      this.words = this.$route.params[3]
      this.comment = this.$route.params[2]
    }
  }
}
</script>

<style>
</style>
