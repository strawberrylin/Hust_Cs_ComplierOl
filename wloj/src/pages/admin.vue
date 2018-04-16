<template>
  <el-container style="height:100vh;">
    <el-header height=40px>
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">
          <el-button type="success" class="userIcon">退出</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container style="justify-content:center;align-items;center;">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="实验题目管理" name="first">
          <el-container style="justify-content:center;align-items:center;">
            <el-aside style="width: 200px;background-color: #FFFFFF">
              <el-menu
              :default-openeds="['1']"
              router
              unique-opened
              @open="handleOpen"
              @close="handleClose">
              <el-submenu index="1">
              <template slot="title"><i class="el-icon-menu"></i>实验</template>
                <el-menu-item-group>
                  <el-menu-item index="1-1">添加</el-menu-item>
                  <el-menu-item index="1-2">删除</el-menu-item>
                  <el-menu-item index="1-3">修改</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              </el-menu>
            </el-aside>
            <el-container>
              <el-main>
                <router-view></router-view>
              </el-main>
            </el-container>
          </el-container>
        </el-tab-pane>
        <el-tab-pane label="用户管理" name="second">
          <el-container style="justify-content:center;align-items:center;">
            <el-aside style="width: 200px;background-color: #FFFFFF">
              <el-menu :default-openeds="['1']">
              <el-submenu index="1">
              <template slot="title"><i class="el-icon-menu"></i>用户</template>
                <el-menu-item-group>
                  <el-menu-item index="1-1">添加</el-menu-item>
                  <el-menu-item index="1-2">删除</el-menu-item>
                  <el-menu-item index="1-3">修改</el-menu-item>
                </el-menu-item-group>
              </el-submenu>
              </el-menu>
            </el-aside>
            <el-container>
              <el-main>
              </el-main>
            </el-container>
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
      activeName: 'first'
    }
  },
  methods: {
    handleClick (tab, event) {
      console.log(tab, event)
    },
    handleOpen (key, keyPath) {
      console.log(key, keyPath)
    },
    handleClose (key, keyPaht) {
      console.log(key, keyPaht)
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
</style>
