<template>
  <el-container>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="实验次数">
        <el-select v-model="value" placeholder="请选择" style="width:230px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="实验名称">
        实验名称
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">删除</el-button>
        <el-button type="success">退出</el-button>
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
        num: ''
      },
      options: [{
        value: '实验1',
        label: ''
      }, {
        value: '实验2',
        label: ''
      }, {
        value: '实验3',
        label: ''
      }, {
        value: '实验4',
        label: ''
      }, {
        value: '实验5',
        label: ''
      }],
      value: ''
    }
  },
  methods: {
    onSubmit () {
      console.log('submit!')
    }
  }
}
</script>
<style>

</style>
