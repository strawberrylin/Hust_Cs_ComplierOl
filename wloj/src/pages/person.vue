<template>
  <el-container v-if="isLogin" style="height:100vh;background-color: #E9EEF3;justify-content:center;align-items;center;">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="实验记录" name="first">
        <el-container>
          <el-main>
            <el-table :data="tableData" max-height="500">
              <el-table-column fixed prop="date" label="日期" width="150">
              </el-table-column>
              <el-table-column prop="experiment" label="实验" width="300px">
              </el-table-column>
              <el-table-column prop="status" label="状态" width="150">
              </el-table-column>
              <el-table-column prop="score" label="成绩" width="150">
              </el-table-column>
              <el-table-column fixed="right" label="操作" width="150">
                <template slot-scope="scope">
                  <el-button @click="lookup(scope.$index,scope.row)" type="text" size="small">
                    查看
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-main>
        </el-container>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <el-container>
          <el-main id="reset">
          <el-form ref="form" label-width="80px" >
            <el-form-item label="原密码">
              <el-input v-model="form.oldpassword" type="password" placeholder="初始密码为123456"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model="form.newpassword" type="password" placeholder="請輸入新密碼"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="submitbtn"  @click="onSubmit" style="width:180px;">修改</el-button>
            </el-form-item>
          </el-form>
          </el-main>
        </el-container>
      </el-tab-pane>
    </el-tabs>
  </el-container>
</template>

<script>

import md5 from 'js-md5'
import {
  mapActions
} from 'vuex'
export default {
  data () {
    return {
      data: this.$route.params,
      tableData: [],
      activeName: 'first',
      form: {
        oldpassword: '',
        newpassword: ''
      }
    }
  },
  mounted: function () {
    this.getRecord()
  },
  methods: {
    ...mapActions(['logOut', 'loginFail']),
    lookup: function (index, row) {
      this.$ajxj({
        method: 'post',
        url: '/record/detail',
        params: {
          codepath: this.data[index].recordPath,
          outputpath: this.data[index].resultPath,
          usernum: this.data[index].mainKey.user.usernum,
          labnum: this.data[index].mainKey.lab.labNum,
          commentT: this.data[index].commentT,
          commentS: this.data[index].commentS,
          type: this.data[index].mainKey.user.type
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.$router.push({
              name: 'detail',
              params: data.data
            })
          }
        })
    },
    handleClick (tab, event) {
      console.log(tab, event)
    },
    onSubmit: function () {
      this.$ajxj({
        method: 'post',
        url: '/user/update',
        params: {
          usernum: this.$store.state.user.usernum,
          oldpassword: md5(this.form.oldpassword),
          newpassword: md5(this.form.newpassword)
        }
      })
        .then((response) => {
          let data = response
          alert(data.message)
          if (data.code === 200) {
            this.logOut()
            this.loginFail()
            this.$router.push({path: '/login'})
          }
        })
    },
    getRecord: function () {
      let arr = []
      if (this.tableData.length === 0) {
        this.data = []
        let i = 0
        for (var n in this.$store.state.record) {
          this.data[i++] = this.$store.state.record[n]
        }
      }
      for (let i = 0; i < this.data.length; i++) {
        var obj = {}
        obj.date = this.data[i].date
        obj.experiment = this.data[i].mainKey.lab.labName
        if (this.data[i].state === 0) {
          obj.status = '未查看'
        } else if (this.data[i].state === 1) {
          obj.status = '已批阅'
        }
        obj.score = this.data[i].score
        arr[i] = obj
      }
      this.tableData = arr
    }
  },
  computed: {
    isLogin: function () {
      return true
    }
  }
}

</script>

<style>
#reset {
    width: 300px;
    margin-left: 200px;
    margin-right: 200px;
  }
</style>
