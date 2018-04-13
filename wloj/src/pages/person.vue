<template>
  <el-container style="height:100vh;width:100%;background-color: #E9EEF3;">
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-left:150px;margin-right:150px;">
      <el-tab-pane label="实验记录" name="first">
        <el-container style="margin-left:80px;margin-left:80px;">
          <el-main>
            <el-table :data="tableData" style="width: 100%;" max-height="250">
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
                  <el-button @click.native.prevent="deleteRow(scope.$index, tableData4)" type="text" size="small">
                    查看
                  </el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-main>
          <el-main>
          </el-main>
        </el-container>
      </el-tab-pane>
      <el-tab-pane label="修改密码" name="second">
        <el-container style="margin-left:80px;margin-left:80px;">
        <div id="reset">
          <el-form ref="form" label-width="80px">
            <el-form-item label="帐号">
              <el-input v-model="form.name" placeholder="请输入学号"></el-input>
            </el-form-item>
            <el-form-item label="原密码">
              <el-input v-model="form.oldpassword" type="password" placeholder="初始密码为123456"></el-input>
            </el-form-item>
            <el-form-item label="新密码">
              <el-input v-model="form.newpassword" type="password" placeholder="初始密码为123456"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" class="submitbtn"  @click="onSubmit">提交</el-button>
            </el-form-item>
          </el-form>
        </div>
        </el-container>
      </el-tab-pane>
    </el-tabs>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      tableData: [{
        date: '2018-4-12',
        experiment: '实验一',
        status: '未评分',
        score: '0'
      }],
      activeName: 'first',
      form: {
        name: '',
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
    }
  }
}

</script>

<style>
#reset {
    width: 300px;
    margin: 0 auto;
  }
</style>
