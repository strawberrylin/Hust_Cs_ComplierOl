<template>
  <el-container>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="实验次数">
        <el-input v-model="form.num" placeholder="请输入实验次数"></el-input>
      </el-form-item>
      <el-form-item label="实验名称">
        <el-input v-model="form.name" placeholder="请输入实验名称"></el-input>
      </el-form-item>
      <el-form-item label="实验要求">
        <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item label="样例输入">
        <el-input type="textarea" v-model="form.input"></el-input>
      </el-form-item>
      <el-form-item label="样例输出">
        <el-input type="textarea" v-model="form.output"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">添加</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      form: {
        name: '',
        num: '',
        desc: '',
        input: '',
        output: ''
      }
    }
  },
  methods: {
    onSubmit () {
      this.$ajxj({
        method: 'post',
        url: '/lab/add',
        params: {
          labNum: this.form.num,
          labName: this.form.name,
          labContent: this.form.desc,
          labInput: this.form.input,
          labOutput: this.form.output
        }
      })
        .then(response => {
          let data = response
          if (data.code === 200) {
            this.form.num = ''
            this.form.name = ''
            this.form.desc = ''
            this.form.input = ''
            this.form.output = ''
          }
        })
    }
  }
}
</script>
<style>

</style>
