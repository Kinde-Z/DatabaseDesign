<template>
  <div>
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入药品名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="80"></el-table-column>
      <el-table-column prop="id" label="药品编号" width="140"></el-table-column>
      <el-table-column prop="name" label="药品名称" width="230"></el-table-column>
      <el-table-column prop="number" label="药品数量" width="160"></el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>

export default {
  name: "Stock",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      this.request.get("/drug/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name,
        }
      }).then(res=>{
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    reset(){
      this.name = ""
      this.load()
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.headerBg{
  background: #eee!important;
}
</style>