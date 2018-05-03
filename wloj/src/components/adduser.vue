<template>
  <el-container style="justify-content:center;align-items;center;">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="form.username" placeholder="输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="号码">
        <el-input v-model="form.usernum" placeholder="请输入帐号"></el-input>
      </el-form-item>
      <el-form-item label="类型">
        <el-select v-model="value" placeholder="请选择" style="width:230px;">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:230px;" @click="onClickSubmit">添加</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script>
import md5 from 'js-md5'
export default {
  data () {
    return {
      form: {
        username: '',
        usernum: ''
      },
      options: [
        {
          value: '学生',
          label: ''
        },
        {
          value: '老师',
          label: ''
        }
      ],
      value: ''
    }
  },
  methods: {
    onClickSubmit: function () {
      let usertype
      if (this.value === '学生') {
        usertype = 2
      } else if (this.value === '老师') {
        usertype = 1
      }
      this.$ajxj({
        method: 'post',
        url: '/user/add',
        params: {
          userNum: this.form.usernum,
          userName: this.form.username,
          password: md5('123456'),
          type: usertype
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.form.username = ''
            this.form.usernum = ''
            this.value = ''
          }
        })
    }
  }
}

</script>

<style>

</style>
