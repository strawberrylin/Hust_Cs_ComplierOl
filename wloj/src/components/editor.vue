<template>
  <div>
    <el-input
      type="textarea"
      :rows="12"
      placeholder="请输入内容"
      v-model="textarea">
    </el-input>
  </div>
</template>

<script>
export default {
  data () {
    return {
      textarea: ''
    }
  }
}
</script>

<style>

</style>
