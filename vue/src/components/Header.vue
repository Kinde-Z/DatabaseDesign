<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px;" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width: 150px; cursor: pointer; text-align: right">
      <span>{{user.nickname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <router-link to="/person" style="text-decoration: none; display: block">
          <el-dropdown-item style="font-size: 14px; padding: 5px 0">个人信息</el-dropdown-item>
        </router-link>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none; display: block" @click="logout">退出</span>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    collapse: Boolean,
    user: Object
  },
  computed: {
    currentPathName() {
      return this.$store.state.currentPathName;  //需要监听的数据
    }
  },
  data(){
    return{
      //-----------
      // user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods: {
    logout() {
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  },
  watch: {
    currentPathName (newVal, oldVal){
      console.log(newVal)
    }
  }
}
</script>

<style scoped>

</style>