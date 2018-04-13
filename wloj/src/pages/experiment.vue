<template>
  <el-container style="height: 100vh;">
    <el-header height="40px">
      <el-row type="flex" class="row-bg" justify="end">
        <el-col :span="5"><div class="grid-content bg-purple"></div>实验代码提交与检测系统</el-col>
        <el-col :span="6"><div class="grid-content bg-purple"></div></el-col>
        <el-col :span="4"><div class="grid-content bg-purple">
          <el-dropdown @command="handleCommand">
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown" >
              <el-dropdown-item>个人中心</el-dropdown-item>
              <el-dropdown-item>实验记录</el-dropdown-item>
              <el-dropdown-item>注销</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button type="success" class="userIcon">{{$store.state.user.username}}</el-button>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <el-menu :default-openeds="['1']">
        <el-submenu index="1">
          <template slot="title"><i class="el-icon-menu"></i>导航</template>
          <el-menu-item-group>
            <el-menu-item index="1-1">实验1</el-menu-item>
            <el-menu-item index="1-2">实验2</el-menu-item>
            <el-menu-item index="1-3">实验3</el-menu-item>
            <el-menu-item index="1-4">实验4</el-menu-item>
            <el-menu-item index="1-5">实验5</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        </el-menu>
      </el-Aside>
      <el-container>
        <el-aside style="background-color: #FFFFFF;border: 2px solid #9E9E9E;">题目</el-aside>
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
                  <el-button type="primary">提交<i class="el-icon-upload el-icon--right"></i></el-button>
                </div>
              </el-col>
            </el-row>
          </el-header>
          <Editor></Editor>
          <el-main style="background-color:#242424;border-top: 30px solid #FDF5E6;border-left: 30px solid #FDF5E6;color:green;">
            Hello, this is the output.
          </el-main>
        </el-container>
      </el-container>
    </el-container>
    <el-footer height="36px">@Copyright 2018 By Strawberrylin</el-footer>
  </el-container>
</template>

<script>
import Editor from '../components/editor.vue'
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
      }, {
        value: '选项5',
        label: 'Scala'
      }],
      value: '',
      inputC: '',
      inputR: ''
    }
  },
  components: {
    Editor
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
    border-top: 1px solid #363636;
  }
</style>
