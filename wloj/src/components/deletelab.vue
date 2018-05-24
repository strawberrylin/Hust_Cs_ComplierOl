<template>
  <el-container style="justify-content:center;align-items;center;">
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
        {{name}}
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width:230px;" @click="onSubmit">删除</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      form: {

      },
      name: '',
      options: [],
      value: ''
    }
  },
  methods: {
    onSubmit () {
      this.$ajxj({
        method: 'post',
        url: '/lab/delete',
        params: {
          labNum: this.value
        }
      })
        .then((response) => {
          let data = response
          alert(data.message)
          if (data.code === 200) {
            this.$router.push({path: '/admin/'})
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
          this.name = this.data[i].labName
        }
      }
    }
  }
}
</script>
<style>

</style>
