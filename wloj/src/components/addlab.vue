<template>
  <el-container>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="实验名称">
        <el-input v-model="form.name" placeholder="请输入实验名称"></el-input>
      </el-form-item>
      <el-form-item label="实验次数">
        <el-input v-model="form.name" placeholder="请输入实验名称"></el-input>
      </el-form-item>
      <el-form-item label="实验要求">
        <el-input type="textarea" v-model="form.desc"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">立即创建</el-button>
        <el-button>取消</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script>
  export default {
    data() {
      return {
        form: {
          name: '',
          region: '',
          desc: ''
        }
      }
    },
    methods: {
      onSubmit() {
        console.log('submit!');
      }
    }
  }

</script>
<style>


</style>

