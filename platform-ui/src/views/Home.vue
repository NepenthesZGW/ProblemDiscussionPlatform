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

      <SelectPro v-model="form.haha" placeholder="请输入" :query="query"></SelectPro>
    </div>
  </div>
</template>

<script>

import SelectPro from "@/components/SelectPro";
export default {
  name: 'Home',
  components: {SelectPro},
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '1',
      form:{
      }
    };
  },
  watch: {
    'form.haha'(val) {
      console.log("========")
      console.log(val)
      console.log("========")
    }
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
    query(str) {
      console.log(str)
      return new Promise(resolve => {
        const arr = [
          {
            value: '选项1',
            label: '黄金糕'
          }, {
            value: '选项2',
            label: '双皮奶'
          }, {
            value: '选项3',
            label: '蚵仔煎'
          }, {
            value: '选项4',
            label: '龙须面'
          }, {
            value: '选项5',
            label: '北京烤鸭'
          }
        ]
        resolve(arr)
      })
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
