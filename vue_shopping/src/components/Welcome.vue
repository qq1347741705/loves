<template>
  <div>
    <div id="div_show">
        <h3>钱仁涛和小猪猪已经认识了{{date_show}}啦</h3>
    </div>
    <div>
      <el-carousel :interval="3000" type="card" height="500px">
        <el-carousel-item v-for="photo in photos" :key="photo.id">
          <img :src="photo.src" />
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      date: new Date() - new Date("2020/5/1 18:00"),
      date_show: " ",
      photos: [
        { src: "http:////39.108.158.47:8080//img//1.png", id: 1 },
        { src: "http:////39.108.158.47:8080//img//2.JPG", id: 2 },
        { src: "http:////39.108.158.47:8080//img//3.JPG", id: 3 },
        { src: "http:////39.108.158.47:8080//img//4.JPG", id: 4 },
        { src: "http:////39.108.158.47:8080//img//5.JPG", id: 5 },
      ],
    };
  },
  methods: {},
  mounted: function () {
    var _this = this; //声明一个变量指向Vue实例this，保证作用域一致
    this.timer = setInterval(function () {
      _this.date = new Date() - new Date("2020/5/1 18:00");
      var time = parseInt(_this.date / 1000);
      var s = time % 60;
      var min = parseInt((time / 60) % 60);
      var h = parseInt((time / 3600) % 24);
      var day = parseInt((time / 3600 / 24) % 30);
      var mon = parseInt((time / 3600 / 24 / 30) % 12);
      var y = parseInt(time / 3600 / 24 / 30 / 12);
      _this.date_show =
        y != 0
          ? y + "年"
          : "" + mon + "月" + day + "天" + h + "小时" + min + "分钟" + s + "秒";
    }, 1000);
  },

  beforeDestory: function () {
    if (this.timer) {
      clearInterval(this.timer); //在vue示例销毁前，清除我们的定时器
    }
  },
};
</script>

<style scoped>
el-carousel-item {
  background-color: pink;
}
img {
  height: 500px;
  width: 400px;
}
#div_show {
  height: 100px;
  line-height: 100px;
}
h3 {
  color: #D24D57;
}
</style>