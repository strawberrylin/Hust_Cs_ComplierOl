<template>
  <el-container style="height: 100vh;background-color:#E9EEF3">
    <el-header height="40px">
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">
          <el-dropdown @command="handleCommand">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown" >
              <el-dropdown-item command="person">个人中心</el-dropdown-item>
              <el-dropdown-item command="logout">注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside style="width: 200px;background-color: #FFFFFF">
        <el-menu
        :default-openeds="['1']"
        @select="handleSelect"
        >
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-menu"></i>实验</template>
          <el-menu-item-group
            v-for="item in asideItems"
            :key="item.labNum"
            >
            <el-menu-item :index="''+item.labNum" v-text="item.labName"></el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        </el-menu>
      </el-Aside>
      <el-container>
        <el-aside style="border: 2px solid #9E9E9E;">
          <el-main style="text-align:center;">
            题目：<p v-html="this.labname"></p>
          </el-main>
          <el-main>
            要求:<p v-html="this.question"></p>
          </el-main>
          <el-main>
            样例输入:<p v-html="this.input"></p>
          </el-main>
          <el-main>
            样例输出:<p v-html="this.output"></p>
          </el-main>
        </el-aside>
        <el-container>
          <el-header style="background-color: #696969;height:40px;line-height:36px">
            <el-row :gutter="20">
              <el-col :span="6">
                <div class="grid-content bg-purple">
                  <el-select v-model="value" placeholder="请选择">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
                  </el-select>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="grid-content bg-purple">
                  <el-input
                    placeholder="请输入编译参数"
                    v-model="inputC"
                  clearable>
                  </el-input>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="grid-content bg-purple">
                  <el-input
                  placeholder="请输入运行参数"
                  v-model="inputR"
                  clearable>
                </el-input>
                </div>
              </el-col>
              <el-col :span="6">
                <div class="grid-content bg-purple">
                </div>
              </el-col>
            </el-row>
          </el-header>
          <Editor v-on:listenToChildEvent="getCode" v-bind:type="value"></Editor>
          <el-container style="height:100vx;">
          <el-main style="background-color:#FFFFFF;border-left: 30px solid #FDF5E6;color:green;">
            <el-table
            :data="resultData"
            style="width:100%;"
            height="200">
              <el-table-column
              fixed
              prop="result"
              >
              </el-table-column>
            </el-table>
          </el-main>
          </el-container>
        </el-container>
      </el-container>
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
  </el-container>
</template>

<script>
import Editor from '../components/editor.vue'
import {
  mapActions
} from 'vuex'
export default {
  data () {
    return {
      options: [{
        value: '选项1',
        label: 'C'
      }, {
        value: '选项2',
        label: 'C++'
      }, {
        value: '选项3',
        label: 'Java'
      }, {
        value: '选项4',
        label: 'Python'
      }],
      value: '选项1',
      inputC: '',
      inputR: '',
      labname: '',
      question: '',
      input: '',
      output: '',
      asideItems: this.$store.state.lab,
      labnum: '',
      resultData: []
    }
  },
  components: {
    Editor
  },
  computed: {
    isLogin: function () {
      return this.$store.state.login.isLogin
    }
  },
  methods: {
    ...mapActions(['logOut', 'loginFail', 'storeRecord']),
    onLogout: function () {
      this.logOut()
      this.loginFail()
      this.$router.push({path: '/login'})
    },
    person: function () {
      this.$ajxj({
        method: 'post',
        url: '/record/personal',
        params: {
          usernum: this.$store.state.user.usernum
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.storeRecord(data.data)
            this.$router.push({
              name: 'person',
              params: data.data
            })
          }
        })
    },
    handleCommand (command) {
      if (command === 'logout') {
        this.onLogout()
      }
      if (command === 'person') {
        this.person()
      }
    },
    onClickSubmit: function () {
      console.log(this.$route.params[1].labNum)
    },
    handleSelect: function (key, keyPath) {
      this.labnum = this.asideItems[key - 1].labNum
      this.$ajxj({
        method: 'post',
        url: '/lab/question',
        params: {
          labNum: this.labnum
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            this.labname = data.data[0]
            this.question = data.data[1]
            this.input = data.data[2]
            this.output = data.data[3]
          }
        })
    },
    getCode: function (data) {
      this.$ajxj({
        method: 'post',
        url: '/lab/compile',
        params: {
          labNum: this.labnum,
          usernum: this.$store.state.user.usernum,
          code: data,
          compile_param: this.inputC,
          run_param: this.inputR
        }
      })
        .then((response) => {
          let data = response
          if (data.code === 200) {
            let arr = []
            for (let i = 0; i < data.data.length; i++) {
              var obj = {}
              obj.result = data.data[i]
              arr[i] = obj
            }
            this.resultData = arr
          }
        })
    }
  }
}
</script>

<style>
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
  .el-main {
    background-color: #E9EEF3;
  }
</style>
