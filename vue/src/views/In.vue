<template>
  <div>
    <el-card style="text-align: center; margin: 100px auto; width: 600px">
      <el-header style="font-size: 25px">药品入库</el-header>
      <el-form input-width="120px" label-width="80px" size="small">
        <el-form-item label="药品名称">
          <el-select clearable v-model="form.drugId" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in drugs" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="供应商">
          <el-select clearable v-model="form.supplierId" placeholder="请选择" style="width: 100%">
            <el-option v-for="item in suppliers" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入库数量">
          <el-input v-model="form.number" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input type="textarea" v-model="form.message" autocomplete="off"></el-input>
        </el-form-item>
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form>
    </el-card>
<!--    <div slot="footer" class="dialog-footer">-->
<!--      -->
<!--    </div>-->
  </div>
</template>

<script>

export default {
  name: "In",
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
      suppliers: [],
    }
  },
  created() {
    this.load()
  },
  methods: {
    load(){
      this.request.get("/drug/ids").then(res=>{
        this.drugs = res.data
      })
      this.request.get("/supplier/ids").then(res=>{
        this.suppliers = res.data
      })
    },
    save(){
      this.request.post("/in", this.form).then(res=>{
        if(res.code === '200'){
          this.$message.success("入库成功")
          this.dialogFormVisible = false
          this.form = {}
          this.load()
        }else{
          this.$message.error("入库失败")
        }
      })
    },

  }
}
</script>