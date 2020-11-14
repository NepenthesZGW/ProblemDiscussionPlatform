<template>
  <div id="home">
    <!-- 导航 -->
    <div>
      <el-menu
              :default-active="activeIndex2"
              class="el-menu-demo"
              mode="horizontal"
              @select="handleSelect"
              background-color="#545c64"
              text-color="#fff"
              active-text-color="#ffd04b">
        <el-menu-item index="1">首页</el-menu-item>
        <el-submenu index="2">
          <template slot="title">我的工作台</template>
          <el-menu-item index="2-1">选项1</el-menu-item>
          <el-menu-item index="2-2">选项2</el-menu-item>
          <el-menu-item index="2-3">选项3</el-menu-item>
        </el-submenu>
        <el-menu-item index="RecordProblem">记录问题</el-menu-item>

      </el-menu>
    </div>

    <!-- 用户信息 -->
    <div id="member">
      <el-dropdown  @click="member()">
        <span style="color: white">用户信息</span>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>黄金糕</el-dropdown-item>
          <el-dropdown-item>狮子头</el-dropdown-item>
          <el-dropdown-item>螺蛳粉</el-dropdown-item>
          <el-dropdown-item>双皮奶</el-dropdown-item>
          <el-dropdown-item>蚵仔煎</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>

    <div>


    </div>
  </div>
</template>

<script>

export default {
  name: 'Home',
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1'
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      if (key == 'RecordProblem'){
        this.doRecordProblem()
      }
    },
    doRecordProblem() {
      this.$router.push('/RecordProblem')
    },
    member(){

    }

  }
}
</script>

<style scoped>
  #home{
    position: relative;
  }
  #member{
    position: absolute;
    top: 0;
    right: 0;
  }
</style>
