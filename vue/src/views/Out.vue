<template>
  <div>
    <el-card style="text-align: center; margin: 100px auto; width: 600px">
      <el-header style="font-size: 25px">药品出库</el-header>
      <el-form input-width="120px" label-width="80px" size="small">
        <el-form-item label="药品名称">
          <el-select clearable v-model="form.drugId" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in drugs" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="操作人">
          <el-input v-model="user.nickname" disabled autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出库数量">
          <el-input v-model="form.number" autocomplete="off" @blur="getDrugNum"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.message" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>

export default {
  name: "Out",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
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
      this.request.get("/drug/ids").then(res=>{
        this.drugs = res.data
      })
    },
    save(){
      if(this.form.number <= this.form.drugNum) {
        this.request.post("/out", this.form).then(res => {
          if (res.code === '200') {
            this.$message.success("出库成功")
            console.warn(this.form)
            this.dialogFormVisible = false
            this.form = {}
            this.getUserId()
            this.load()
          } else {
            this.$message.error("出库失败")
          }
        })
      }else{
        this.$message.error("出库数量超出库存余量")
      }
    },
    getDrugNum(){
      this.request.get("/drug/num", {
        params: {
          drugId: this.form.drugId,
        }
      }).then(res => {
        this.form.drugNum = res.data
      })
    },
    getUserId(){
      this.request.get("/out/user", {
        params: {
          name: this.user.nickname,
        }
      }).then(res => {
        this.form.userId = res.data
      })
    }
  }
}
</script>