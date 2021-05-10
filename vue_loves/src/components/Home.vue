<template>
  <div>
    <el-container>
      <el-aside width="200px"> </el-aside>
      <el-container>
        <el-header>
          <el-menu
            class="el-menu-top"
            mode="horizontal"
            router
          >
            <el-menu-item index="/welcome">Welcome</el-menu-item>
            <el-menu-item index="/blogList">看看写了些什么</el-menu-item>
            <el-menu-item index="/release">写一些什么吧</el-menu-item>
            <el-menu-item index="/photo" disabled>相册</el-menu-item>
            <img :src="img"></img>
            <el-button type="danger" class="btnExit" v-on:click="exit">退出</el-button>
          </el-menu>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
        <el-footer></el-footer>
      </el-container>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      img: ''
    };
  },
  methods: {
    exit: function () {
      window.sessionStorage.removeItem('currentUser');
      window.sessionStorage.removeItem('token');
      this.$router.push('/login');
    },
    getImg: function () {
      this.$http.get('/getImg').then((res) => {
        if(this.$checkLogin(res,this) == false)
            return;
        this.img = res.data.data.img;
      }).catch((err) => {
        console.log(err);
      });
    }
  },
  mounted: function () {
    this.getImg();
  }
};
</script>

<style lang="less" scoped>
.el-header {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
}
.el-footer {
  background-color: pink;
  color: #333;
  text-align: center;
}
.el-aside {
  background-color: pink;
  color: #333;
  text-align: center;
}

.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-menu-top {
}
.btnExit {
  position: absolute;
  right: 70px;
  top: 10px;
}
img {
  position: absolute;
  right: 170px;
  top: 12px;
  height: 35px;
  width: 35px;
  border-radius: 50%;
  background-color: #eee;
}
</style>