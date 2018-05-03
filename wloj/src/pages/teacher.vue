<template>
  <el-container style="height:100vh;background-color:#FFFFFF;">
    <el-header height=40px>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">
          <el-dropdown @command="handleCommand">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown" >
              <el-dropdown-item command="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container style="justify-content:center;">
    <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="实验记录" name="first">
        <el-container>
          <el-aside style="width: 200px;background-color: #FFFFFF">
            <el-menu :default-openeds="['1']"
            @select="handleSelect">
            <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>实验</template>
              <el-menu-item-group
                v-for="item in asideItems"
                :key="item.labNum">
                <el-menu-item :index="''+item.labNum" v-text="item.labName"></el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            </el-menu>
          </el-aside>
          <el-container style="justify-content:center;" direction="vertical">
            <el-main style="width: 800px;">
              <el-table
                :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                max-height="250"
                stripe
                >
                <el-table-column fixed prop="name" label="姓名" width="120">
                </el-table-column>
                <el-table-column fixed prop="account" label="学号" width="120">
                </el-table-column>
                <el-table-column prop="status" label="状态" width="120">
                </el-table-column>
                <el-table-column prop="score" label="成绩" width="120">
                </el-table-column>
                <el-table-column label="评分" width="120">
                  <template slot-scope="scope">
                    <el-input v-model="scope.row.cal"></el-input>
                  </template>
                </el-table-column>
                <el-table-column fixed="right" label="操作" width="150">
                  <template slot-scope="scope">
                    <el-button @click="calculate(scope.$index, tableData)" type="text" size="small">
                      评分
                    </el-button>
                    <el-button @click="lookup(scope.$index, tableData)" type="text" size="small">
                      查看
                    </el-button>
                  </template>
                </el-table-column>
              </el-table>
            </el-main>
            <el-container style="justify-content:center;align-items:center;">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[1, 10, 20, 50]"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
              </el-pagination>
            </el-container>
          </el-container>
        </el-container>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <el-container style="justify-content:center; width:1000px;">
          <el-main id="reset">
            <el-form ref="form" v-model="form" label-width="80px" >
              <el-form-item label="原密码">
                <el-input v-model="form.oldpassword" type="password" placeholder="初始密码为123456"></el-input>
              </el-form-item>
              <el-form-item label="新密码">
                <el-input v-model="form.newpassword" type="password" placeholder="請輸入新密碼"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary"  class="submitbtn"  @click="onSubmit" style="width:220px;">修改</el-button>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </el-tab-pane>
    </el-tabs>
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
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
      tableData: [],
      activeName: 'first',
      form: {
        oldpassword: '',
        newpassword: ''
      },
      asideItems: this.$store.state.lab,
      resultData: [],
      labnum: '',
      currentPage: 1,
      pageSize: 1
    }
  },
  methods: {
    ...mapActions(['logOut', 'loginFail']),
    onLogout: function () {
      this.logOut()
      this.loginFail()
      this.$router.push({path: '/login'})
    },
    lookup (index, rows) {
      console.log(this.currentPage * index)
      this.$ajxj({
        method: 'post',
        url: '/record/detail',
        params: {
          codepath: this.resultData[(this.currentPage - 1) * this.pageSize + index].recordPath,
          outputpath: this.resultData[(this.currentPage - 1) * this.pageSize + index].resultPath
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
    handleCommand (command) {
      if (command === 'logout') {
        this.onLogout()
      }
    },
    handleSelect: function (key, keyPath) {
      let labnum = this.asideItems[key - 1].labNum
      this.labnum = labnum
      this.getLabnumRecord(labnum)
    },
    handleSizeChange: function (size) {
      this.pageSize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    calculate: function (index, tableData) {
      this.$ajxj({
        method: 'post',
        url: '/record/update',
        params: {
          usernum: tableData[(this.currentPage - 1) * this.pageSize + index].account,
          labnum: this.labnum,
          score: tableData[(this.currentPage - 1) * this.pageSize + index].cal
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.getLabnumRecord(this.labnum)
          }
        })
    },
    getLabnumRecord: function (labnum) {
      this.$ajxj({
        method: 'post',
        url: '/record/labnum',
        params: {
          labnum: labnum
        }
      })
        .then((response) => {
          let data = response
          this.resultData = data.data
          if (data.code === 200) {
            let arr = []
            for (let i = 0; i < data.data.length; i++) {
              var obj = {}
              obj.name = data.data[i].mainKey.user.username
              obj.account = data.data[i].mainKey.user.usernum
              if (data.data[i].state === 0) {
                obj.status = '未查看'
              } else {
                obj.status = '已批阅'
              }
              obj.cal = ''
              obj.score = data.data[i].score
              arr[i] = obj
            }
            this.tableData = arr
          }
        })
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
          if (data.code === 200) {
            this.logOut()
            this.loginFail()
            this.$router.push({path: '/login'})
          }
        })
    }
  }
}
</script>

<style>
#reset {
    width: 300px;
    margin-left: 330px;
    margin-right: 330px;
  }
.el-header, .el-footer {
    background-color: #363636;
    color: #EAEAEA;
    text-align: center;
    line-height: 36px;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
  }
</style>
