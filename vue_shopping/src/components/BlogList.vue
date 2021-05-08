<template>
  <div>
    <div v-show="!blogShow">
      <div v-for="blog in blogInfo">
        <div id="div1" v-on:click="getBlog(blog.id)">
          <div id="div2">
            <br />
            <h2 id="title">
              {{
                blog.title.length > 20
                  ? blog.title.substr(0, 20) + "..."
                  : blog.title
              }}
            </h2>
          </div>
          <div id="div4">
            <span class="el-icon-s-custom">: </span>&nbsp;&nbsp;

            <span> {{ blog.author }}</span
            ><br />
            <span class="el-icon-time">: </span>
            <span id="timeShow"> {{ blog.strTime }}</span>
          </div>
        </div>
        <el-divider></el-divider>
      </div>
      <el-pagination
        background
        layout="prev, pager, next"
        :page-count="total"
        @current-change="setPage"
        :current-page="currentPage"
      >
      </el-pagination>
    </div>
    <div v-show="blogShow">
      <div>
        <el-button
          type="primary"
          icon="el-icon-back"
          v-on:click="blogBack"
          id="btn_back"
          >返回</el-button
        >
        <el-button
          v-show="currentUser == blog.author"
          type="danger"
          plain
          id="btn_delete"
          icon="el-icon-delete"
          v-on:click="deleteBlog"
          >删除</el-button
        >
      </div>
      <blog-info v-bind:blog="blog"></blog-info>
    </div>
  </div>
</template>
<script>
import BlogInfo from "./BlogInfo.vue";
export default {
  components: { BlogInfo },
  data() {
    return {
      blogInfo: [],
      total: 1, //总页数
      pageSize: 5,
      currentPage: 1, //当前页
      blog: [],
      blogShow: false,
      currentUser: "",
    };
  },
  methods: {
    getAllBlog: function () {
      //   var curPage = Number(window.sessionStorage.getItem("currentPage"));
      //   if (curPage != 1) this.currentPage = curPage;
      this.$http
        .get("/getAllBlog", {
          params: {
            pageSize: this.pageSize,
            pageNum: this.currentPage,
          },
        })
        .then((res) => {
          this.blogInfo = res.data.blogInfo;
          this.total = res.data.total;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    setPage: function (currentPage) {
      this.currentPage = currentPage;
      window.sessionStorage.setItem("currentPage", currentPage);
      this.getAllBlog();
      //console.log("set page:" + currentPage);
    },
    getBlog: function (id) {
      this.$http
        .get("/getBlog", {
          params: {
            id: id,
          },
        })
        .then((res) => {
          this.blog = res.data;
          this.blogShow = true;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    blogBack: function () {
      this.blogShow = false;
    },
    deleteBlog: function () {
      //删除文章
      this.$confirm("此操作将永久删除该内容, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http
            .post("/deleteBlog", {
              id: this.blog.id,
            })
            .then((res) => {
              if (res.data.status != 0) return;
              this.$message({
                type: "success",
                message: "删除成功!"
              });
              this.blogShow = false;
              this.getAllBlog();
            })
            .catch((err) => {
              console.log(err);
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

  },
  mounted: function () {
    this.$myfun();
    //window.sessionStorage.setItem("currentPage", 1);
    //window.vue = this;
    // var curPage = Number(window.sessionStorage.getItem("currentPage"));
    // if (curPage != 1) this.currentPage = curPage;
    this.currentUser = window.sessionStorage.getItem("username");
    this.getAllBlog();
  },
};
</script>
<style scoped>
#div1 {
  position: relative;
  left: 200px;
  height: 150px;
  width: 60%;
  border-radius: 5px;
  background-color: pink;
}
#div2 {
  height: 60px;
  text-align: center;
  margin: auto;
}
#div3 {
  height: 70px;
}
#div4 {
  height: 50px;
  position: relative;
  top: 30px;
}
#div4 span {
  position: relative;
  left: 250px;
}
#title {
  margin-top: 10px;
}
#btn_back {
  position: relative;
  right: 450px;
}
#btn_delete {
  position: relative;
  left: 480px;
}
</style>