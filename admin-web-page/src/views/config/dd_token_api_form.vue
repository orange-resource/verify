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
            title="这里的钉钉机器人配置, 主要作用于用户给软件留言之后自动通知到钉钉群"
            type="info" :closable="false">
          </el-alert>
          <br/>
          <el-form :model="form" :rules="forms" :status-icon="true"
                   ref="form" label-width="100px" class="demo-ruleForm">

            <el-form-item label="secret" prop="secret">
              <el-input v-model="form.secret" class="common-width"></el-input>
            </el-form-item>
            <el-form-item label="token" prop="token">
              <el-input v-model="form.token" class="common-width"></el-input>
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

      this.$axios.post("ddToken/getDetail").then((rsp) => {
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
          secret: '',
          token: ''
        },
        forms: {
          secret: [
            { required: true, message: '请填写secret', trigger: 'blur' },
          ],
          token: [
            { required: true, message: '请填写token', trigger: 'blur' },
          ]
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
          url: "ddToken/update",
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
