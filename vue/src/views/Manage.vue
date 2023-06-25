<template>
  <el-container style="min-height: 100vh;">
    <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246); box-shadow: 5px 0 6px rgb(0,21,41,0.35)">
    <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
    </el-aside>

    <el-container>
      <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"/>
      </el-header>

      <el-main>
        <!--当前页面的子路由会在router-view展示-->
        <!--<router-view/>-->
        <router-view @refreshUser="getUser"/>
      </el-main>

    </el-container>
  </el-container>
</template>

<script>
import Aside from "@/components/Aside";
import Header from "@/components/Header";
import request from "@/utils/request";

export default {
  name: 'HomeView',
  data(){
    return {

      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      //-----------
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  components: {
    Aside,
    Header
  },
  methods: {
    collapse(){
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      }else{
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
    //---------------
    getUser(){
      //从后台获取User数据
      this.request.get("/user/username/"+this.user.username).then(res => {
        //重新赋值的最新User数据
        this.user = res.data
      })
    }
  }
}
</script>
