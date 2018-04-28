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
                  <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
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
export default {
  data () {
    return {
      tableData: this.$route.params,
      activeName: 'first',
      form: {
        oldpassword: '',
        newpassword: ''
      }
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    handleClick (tab, event) {
      console.log(tab, event)
    },
    onSubmit: function () {

    },
    test: function () {
      console.log(this.temp)
    }
  },
  computed: {
    isLogin: function () {
      return this.$store.state.login.isLogin
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
