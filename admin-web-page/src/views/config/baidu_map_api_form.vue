<template>

  <div id="app">
    <el-row :gutter="0">

      <el-col :span="24">

        <el-card shadow="always" v-show="searchWorkspace == false" style="text-align: center">
          <i class="el-icon-upload"></i>
          <span> 操作</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
            展示
          </el-button>
        </el-card>

        <el-card class="box-card" shadow="always" v-show="searchWorkspace == true">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload"></i>
            <span> 操作</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
              收起
            </el-button>
          </div>

          <el-alert
            title="系统对接了百度地图开放平台，用于拿到用户的ip地理位置 百度开放平台：http://lbsyun.baidu.com/apiconsole/key"
            type="info" :closable="false">
          </el-alert>
          <br/>
          <el-form :model="form" :rules="forms" :status-icon="true"
                   ref="form" label-width="100px" class="demo-ruleForm">

            <el-form-item label="appkey" prop="appkey">
              <el-input v-model="form.appkey" class="common-width"></el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('form')">立即保存</el-button>
              <el-button @click="resetForm('form')">重置</el-button>
            </el-form-item>

          </el-form>

        </el-card>

      </el-col>

    </el-row>

  </div>

</template>

<script>
  export default {
    mounted() {

      this.$axios.post("baiduMapToken/getDetail").then((rsp) => {
        if (rsp.data.detail != null) {
          this.form = rsp.data.detail;
        }
      });

    },
    data() {
      return {
        //收起放下
        searchWorkspace: true,

        //表单配置
        form: {
          appkey: '',
        },
        forms: {
          appkey: [
            {required: true, message: '请填写appkey', trigger: 'blur'},
          ],
        },

      }
    },
    methods: {
      //表单操作
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.submit();
          } else {
            this.$message.error('提交错误');
            return false;
          }
        });
      },
      submit() {

        let data = this.form;

        this.$axios({
          method: 'post',
          url: "baiduMapToken/update",
          data:this.$qs.stringify(data),
        }).then((rsp) => {
          this.$message(rsp.msg);
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    }
  }
</script>

<style>
  .common-width {
    width: 500px;
  }
</style>
