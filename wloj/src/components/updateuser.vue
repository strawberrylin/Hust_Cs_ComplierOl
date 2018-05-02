<template>
  <el-container direction="vertical">
    <el-container>
    <el-table
      :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
      max-height="250"
      stripe
      >
      <el-table-column fixed prop="name" label="姓名" width="150">
      </el-table-column>
      <el-table-column fixed prop="account" label="帐号" width="150">
      </el-table-column>
      <el-table-column prop="password" label="密码" width="150">
        <template slot-scope="scope">
          <el-input v-model="tableData.password"></el-input>
        </template>
      </el-table-column>
      <el-table-column prop="type" label="类型" width="150">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="update(scope.$index)"
          type="text"
          size="small">
            更新
          </el-button>
          <el-button @click.native.prevent="deleteRow(scope.$index, tableData)"
          type="text"
          size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    </el-container>
    <el-container style="justify-content:center;align-items:center;">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[1, 20, 50, 100]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="tableData.length">
      </el-pagination>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      tableData: [],
      currentPage: 1,
      pageSize: 1
    }
  },
  methods: {
    deleteRow (index, rows) {
      rows.splice(index, 1)
    },
    update: function () {

    },
    handleSizeChange: function (size) {
      this.pageSize = size
    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage
    },
    getUsers: function () {
      this.$ajxj({
        method: 'get',
        url: 'user/list'
      })
        .then((response) => {
          let data = response
          let arr = []
          let k = 0
          for (let i = 0; i < data.data.length; i++) {
            if ((data.data[i].type === 1) || (data.data[i].type === 2)) {
              var obj = {}
              obj.name = data.data[i].username
              obj.account = data.data[i].usernum
              if (data.data[i].type === 1) {
                obj.type = '老师'
              } else {
                obj.type = '学生'
              }
              obj.password = data.data[i].password
              arr[k] = obj
              k++
            }
          }
          this.tableData = arr
          console.log(this.tableData)
        })
    }
  },
  mounted: function () {
    this.getUsers()
  }
}

</script>

<style>

</style>
