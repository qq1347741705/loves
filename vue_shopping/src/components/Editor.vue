<template>
  <div class="div2">
    <div>
      <el-input v-model="title" placeholder="请输入标题(30个字以内哦)..."></el-input>
      <br><br><br><br>
    </div>
    <div ref="editor" class="textBox"></div>
    <div class="div1">
      <br />
      <el-button
        type="danger"
        v-on:click="reset"
        icon="el-icon-delete"
        id="btn1"
        :disabled="editorContent == '' && title == '' ? true : false"
        >清空</el-button
      >
      <el-button
        type="primary"
        v-on:click="upload"
        id="btn2"
        :disabled="editorContent == '' || title == '' ? true : false"
        >上传<i class="el-icon-upload el-icon--right"></i
      ></el-button>
    </div>
  </div>
</template>

<script>
import E from "wangeditor";
export default {
  data() {
    return {
      editorContent: "",
      editor: null,
      title: "",
    };
  },
  methods: {
    upload: function () {
      if (this.editorContent == "") {
        this.$message.error("请输入内容！！！！");
      }
      this.$http
        .post("/uploadBlog", {
          content: this.editorContent,
          title: this.title
        })
        .then((res) => {
          if(this.$checkLogin(res,this) == false)
            return;
          this.title = '';
          this.editor.txt.clear();
          this.$message({
            type: "success",
            message: "上传成功!",
          });
        })
        .catch(function (err) {
          console.log(err);
        });
    },
    reset: function () {
      this.editor.txt.clear();
      this.title = ''
      this.$message({
        type: "success",
        message: "清空成功!",
      });
    },
  },
  mounted() {
    this.editor = new E(this.$refs.editor);
    //内容发送改变时执行的函数
    this.editor.config.onchange = (newHtml) => {
      this.editorContent = newHtml;
    };
    this.editor.config.uploadFileName = "file";
    this.editor.config.uploadImgServer = "http://10.22.77.37:9000/uploadFile";
    this.editor.config.uploadImgMaxSize = 15 * 1024 * 1024; // 20M
    //this.editor.config.showLinkImg = false
    // errno 即错误代码，0 表示没有错误
    // data 是一个数组，返回图片Object，Object中包含需要包含url、alt和href三个属性,
    //它们分别代表图片地址、图片文字说明和跳转链接,alt和href属性是可选的，可以不设置或设置为空字符串,需要注意的是url是一定要填的。
    this.editor.create();
  },
};
</script>

<style scoped>
#btn1 {
  position: absolute;
  right: 200px;
}
#btn2 {
  position: absolute;
  right: 60px;
}

.div1 {
  height: 50px;
}

.div2 {
  margin: 20px;
}
</style>