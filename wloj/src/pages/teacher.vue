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
              <el-dropdown-item>注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-left:150px;margin-right:150px;">
      <el-tab-pane label="实验记录" name="first">
        <el-container style="margin-right:80px;">
          <el-aside style="width: 200px;background-color: #FFFFFF">
            <el-menu :default-openeds="['1']">
            <el-submenu index="1">
            <template slot="title"><i class="el-icon-menu"></i>实验</template>
              <el-menu-item-group>
                <el-menu-item index="1-1">字符串操作实验</el-menu-item>
                <el-menu-item index="1-2">多线程实验</el-menu-item>
                <el-menu-item index="1-3">多进程实验</el-menu-item>
                <el-menu-item index="1-4">共享内存实验</el-menu-item>
                <el-menu-item index="1-5">文件系统实验</el-menu-item>
              </el-menu-item-group>
            </el-submenu>
            </el-menu>
          </el-aside>
          <el-container>
            <el-table :data="tableData" max-height="250">
              <el-table-column fixed prop="name" label="姓名" width="150">
              </el-table-column>
              <el-table-column fixed prop="account" label="学号" width="150">
              </el-table-column>
              <el-table-column prop="status" label="状态" width="150">
              </el-table-column>
              <el-table-column prop="score" label="评分" width="150">
                <template slot-scope="scope">
                  <el-input v-model="table.score"></el-input>
                </template>
              </el-table-column>
              <el-table-column fixed="right" label="操作" width="150">
                <template slot-scope="scope">
                  <el-button @click.native.prevent="deleteRow(scope.$index, tableData)" type="text" size="small">
                    查看
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-container>
        </el-container>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <el-container style="margin-right:80px;margin-left:80px;">
          <el-main style="margin-left:80px;margin-right:80px;">
          <div id="reset">
          <el-form ref="form" label-width="80px" >
            <el-form-item label="帐号">
              <el-input v-model="form.name" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="原密码">
              <el-input v-model="form.oldpassword" type="password" placeholder="初始密码为123456"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model="form.newpassword" type="password" placeholder="請輸入新密碼"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="submitbtn"  @click="onSubmit" style="width:220px;">修改</el-button>
            </el-form-item>
          </el-form>
          </div>
          </el-main>
        </el-container>
      </el-tab-pane>
    </el-tabs>
    </el-container>
    <el-footer height="36px">Copyright @2018 Strawberrylin</el-footer>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        name: '王林',
        account: 'U201414808',
        status: '已提交',
        score: ''
      }],
      activeName: 'first',
      form: {
        name: '',
        oldpassword: '',
        newpassword: ''
      },
      table: {
        score: 100
      }
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    handleClick (tab, event) {
      console.log(tab, event)
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
