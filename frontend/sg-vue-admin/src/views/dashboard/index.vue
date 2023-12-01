<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      数据库实训项目
    </div>
    <div>
      软件2001李威翰：<a href="https://space.bilibili.com/663528522"></a>   基于MybatisPlus的前后端分离开发式博客系统
    </div>
    <div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
