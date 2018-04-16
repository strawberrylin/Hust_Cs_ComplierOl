<template>
  <el-container style="height:100vh;background-color: #E9EEF3;">
    <el-header height=40px>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">
          <el-button type="success" class="userIcon">返回</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container style="justify-content:center;align-items:center;">
      <div style="width:400px;height:400px;background-color:#FFFFFF;border: 20px solid #FFE3FF;">
        <code>int main</code>
      </div>
      <div style="width:400px;height:400px;background-color:#FFFFFF;border: 20px solid #FFE3FF;">
        <code>output</code>
      </div>
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
  </el-container>
</template>

<script>
export default {
}
</script>

<style>
</style>
