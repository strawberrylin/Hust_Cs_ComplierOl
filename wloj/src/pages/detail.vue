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
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      code: this.$route.params[0],
      output: this.$route.params[1]
    }
  }
}
</script>

<style>
</style>
