<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(50,65,86)" text-color="#fff" active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
      <b style="color: white;" v-show="logoTextShow">药库管理系统</b>
    </div>
    <el-menu-item index="/">
      <i class="el-icon-s-home" ></i>
      <span slot="title">主页</span>
    </el-menu-item>

    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu index="3">
      <template slot="title">
        <i class="el-icon-s-order"></i>
        <span>基础信息</span>
      </template>
      <el-menu-item index="/supplier">
        <i class="el-icon-search"></i>
        <span slot="title">供应商信息</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu index="4">
      <template slot="title">
        <i class="el-icon-box"></i>
        <span>库存管理</span>
      </template>
      <el-menu-item index="/stock">
        <i class="el-icon-search"></i>
        <span slot="title">库存查询</span>
      </el-menu-item>
      <el-menu-item index="/maintain">
        <i class="el-icon-tickets"></i>
        <span slot="title">药品信息维护</span>
      </el-menu-item>
      <el-menu-item index="/check">
        <i class="el-icon-notebook-2"></i>
        <span slot="title">库存盘点</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu index="5">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span>出入库管理</span>
      </template>
      <el-menu-item index="/out">
        <i class="el-icon-remove-outline"></i>
        <span slot="title">药品出库</span>
      </el-menu-item>
      <el-menu-item index="/in">
        <i class="el-icon-circle-plus-outline"></i>
        <span slot="title">药品入库</span>
      </el-menu-item>
    </el-submenu>

    <el-submenu index="6">
      <template slot="title">
        <i class="el-icon-s-data"></i>
        <span slot="title">统计报表</span>
      </template>
      <el-menu-item index="/out_info">
        <i class="el-icon-date"></i>
        <span slot="title">出库统计</span>
      </el-menu-item>
      <el-menu-item index="/in_info">
        <i class="el-icon-date"></i>
        <span slot="title">入库统计</span>
      </el-menu-item>
    </el-submenu>

  </el-menu>
</template>

<script>
export default {
  name: "Aside.vue",
  props: {
    isCollapse: Boolean,
    logoTextShow: Boolean
  }
}
</script>

<style scoped>

</style>