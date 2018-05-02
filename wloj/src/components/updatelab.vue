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
        <el-input v-model="form.name"></el-input>
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
        <el-button type="primary" @click="onSubmit">更新</el-button>
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
        desc: '',
        input: '',
        output: '',
        data: []
      },
      options: [],
      value: ''
    }
  },
  methods: {
    onSubmit () {
      this.$ajxj({
        method: 'post',
        url: '/lab/update',
        params: {
          labNum: this.value,
          labName: this.form.name,
          labContent: this.form.desc,
          labInput: this.form.input,
          labOutput: this.form.output
        }
      })
        .then(response => {
          let data = response
          if (data.code === 200) {
          }
        })
    },
    getLabList () {
      this.$ajxj({
        method: 'get',
        url: '/lab/list'
      })
        .then(response => {
          let data = response
          if (data.code === 200) {
            this.data = data.data
            let arr = []
            for (let i = 0; i < data.data.length; i++) {
              var obj = {}
              obj.value = data.data[i].labNum
              obj.label = ''
              arr[i] = obj
            }
            this.options = arr
          }
        })
    }
  },
  mounted: function () {
    this.getLabList()
  },
  watch: {
    value: function () {
      for (let i = 0; i < this.data.length; i++) {
        if (this.value === this.data[i].labNum) {
          this.form.name = this.data[i].labName
        }
      }
      this.$ajxj({
        method: 'post',
        url: '/lab/question',
        params: {
          labNum: this.value
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.form.desc = data.data[1]
            this.form.input = data.data[2]
            this.form.output = data.data[3]
          }
        })
    }
  }
}
</script>
<style>

</style>
