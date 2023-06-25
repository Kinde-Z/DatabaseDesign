<template>
  <div>
    <el-card style="margin-bottom: 60px">
      <el-header style="font-size: 25px; text-align: center">药品盘点</el-header>
      <el-form :inline="true" :model="formInline" class="demo-form-inline" input-width="120px" label-width="70px" size="small">
        <el-form-item label="药品名称">
          <el-select clearable v-model="form.drugId" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in drugs" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作人">
          <el-input v-model="user.nickname" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="盘点数量">
          <el-input v-model="form.checkNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.message" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="save" style="margin-left: 10px">确 定</el-button>
      </el-form>
    </el-card>
<!--分割-->
    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>

      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定要批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除<i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="60"></el-table-column>
      <el-table-column prop="id" label="盘点单据号" width="90"></el-table-column>
      <el-table-column prop="drugName" label="药品名称" width="180"></el-table-column>
      <el-table-column prop="userName" label="操作人名称" width="150"></el-table-column>
      <el-table-column prop="number" label="药品数量" width="130"></el-table-column>
      <el-table-column prop="checkNum" label="盘点数量" width="130"></el-table-column>
      <el-table-column prop="profit" label="盈亏数量" width="130"></el-table-column>
      <el-table-column prop="checkTime" label="操作时间" width="150"></el-table-column>
      <el-table-column prop="message" label="备注" width="150"></el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-popconfirm
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定要删除该数据吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
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
  name: "Check",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      name: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      drugs: [],
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.getUserId()
    this.load()
  },
  methods: {
    load(){
      this.request.get("/check/page", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          name: this.name
        }
      }).then(res=>{
        this.tableData = res.data.records
        this.total = res.data.total
      })

      this.request.get("/drug/ids").then(res=>{
        this.drugs = res.data
      })
    },
    save(){
      this.request.post("/check", this.form).then(res => {
        if (res.code === '200') {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.form = {}
          this.getUserId()
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    getUserId(){
      this.request.get("/check/user", {
        params: {
          name: this.user.nickname,
        }
      }).then(res => {
        this.form.userId = res.data
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    del(id){
      this.request.delete("/check/"+id).then(res=>{
        if(res.code === '200'){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/check/del/batch", ids).then(res=>{
        if(res.code === '200'){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("批量删除失败")
        }
      })
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