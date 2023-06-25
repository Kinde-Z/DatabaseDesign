<template>
  <el-card style="width: 500px; margin: 100px auto">
    <el-form label-width="80px" size="small">
      <el-form-item label="用户名">
        <el-input v-model="form.username" disabled autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="电话">
        <el-input v-model="form.phone" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="地址">
        <el-input type="textarea" v-model="form.address" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item style="text-align: center">
        <el-button type="primary" @click="save">确 定</el-button>
      </el-form-item>
    </el-form>
  </el-card>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Person",
  data(){
    return{
      form: {},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  created() {
    // this.request.get("/user/username/"+this.user.username).then(res => {
    //   if(res.code === '200'){
    //     this.form = res.data
    //   }
    // })
    this.getUser().then(res => {
      this.form = res
    })
  },
  methods: {
    // ----------
    async getUser(){
      return (await this.request.get("/user/username/"+this.user.username)).data
    },
    save(){
      request.post("/user", this.form).then(res=>{
        if(res.data){
          this.$message.success("保存成功")
          //触发父级更新User的方法
          this.$emit("refreshUser")

          //更新浏览器存储的用户信息---------------------
          this.getUser().then(res => {
            res.token = JSON.parse(localStorage.getItem("user")).token
            localStorage.setItem("user", JSON.stringify(res))
          })
        }else{
          this.$message.error("保存失败")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>