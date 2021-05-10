<template>
  <div>
    <div id="div_title">
      <h2>{{ blog.title }}</h2>
    </div>
    <div style="height: 70px"></div>
    <div id="div_content">
      <p v-html="blog.content"></p>
    </div>
    <div style="height: 5px"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  props: ["blog"],
};
</script>

<style scoped>
#div_title {
  margin: 0 auto;
  width: 60%;
  height: 150px;
  line-height: 120px;
  background-color: pink;
}
#div_content {
  margin: 0 auto;
  background-color: pink;
  width: 60%;
}
#div_btn {
    margin: 0 auto;
    width: 60%;
}
#div_btn el-button {
    
}
</style>