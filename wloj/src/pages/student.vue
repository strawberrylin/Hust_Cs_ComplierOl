<template>
  <el-container v-if="isLogin">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
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
    </el-aside>
    <el-container>
      <el-header>
        <Header></Header>
      </el-header>
      <el-container>
        <el-containner>
          <el-header class="el-header-s">
            <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          </el-header>
          <el-main class="el-main-editor">
            <Editor></Editor>
          </el-main>
        </el-containner>
        <el-main class="el-main-upload">
          <el-container class="el-container-i">
            <el-input class="el-input-m"
              placeholder="请输入编译参数"
              v-model="inputC"
              clearable>
            </el-input>
            <el-input class="el-input-m"
              placeholder="请输入运行参数"
              v-model="inputR"
              clearable>
            </el-input>
          </el-container>
          <Upload></Upload>
        </el-main>
      </el-container>
      <el-container>
        <el-main>
          <Editor></Editor>
        </el-main>
        <el-main>
          <Editor></Editor>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import Header from '../components/header.vue'
import Editor from '../components/editor.vue'
import Upload from '../components/upload.vue'
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
    Header,
    Editor,
    Upload
  },
  computed: {
    isLogin: function () {
      console.log('login state:' + this.$store.state.login.isLogin)
      return this.$store.state.login.isLogin
    }
  }
}
</script>

<style>
  .el-header, .el-footer {
    background-color: #eee;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: left;
    margin-top: 0;
    line-height: 160px;
  }
  body > .el-container {
    margin: 0 50px 0 50px;
  }
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }
  .el-main-editor {
    height: 340px;
    width: 600px;
  }
  .el-main-upload {
    height: 400px;
  }
  .el-header-s {
    text-align: left;
    margin-bottom: 0;
  }
  .el-input-m {
    margin: 0 20 0 20;
    width: 245px;
  }
  .el-container-i {
    height: 100px;
    border: #D3DCE6
  }
</style>
