<template>

  <div id="app">
    <el-row :gutter="0">

      <el-col :span="24">

        <el-card shadow="always" v-show="searchWorkspace == false" style="text-align: center">
          <i class="el-icon-upload"></i>
          <span> 操作</span>
          <span @click="openExpress" style="color: #409EFF;cursor: pointer;margin-left: 20px"> 上一页</span>
          <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
            展示
          </el-button>
        </el-card>

        <el-card class="box-card" shadow="always" v-show="searchWorkspace == true">
          <div slot="header" class="clearfix">
            <i class="el-icon-upload"></i>
            <span> 操作</span>
            <span @click="openExpress" style="color: #409EFF;cursor: pointer;margin-left: 20px"> 上一页</span>
            <el-button style="float: right; padding: 3px 0" type="text" @click="searchWorkspace = !searchWorkspace">
              收起
            </el-button>
          </div>

          <el-form :model="form" :rules="forms" :status-icon="true"
                   ref="form" label-width="100px" class="demo-ruleForm">

            <el-form-item label="软件名称" prop="name">
              <el-input v-model="form.name" class="common-width"></el-input>
            </el-form-item>

            <el-form-item label="软件公告" prop="notice">
              <el-input
                v-model="form.notice"
                class="common-width"
                type="textarea"
                :rows="8"
                placeholder="请输入软件公告"
              >
              </el-input>
            </el-form-item>

            <el-form-item label="换绑策略" prop="changeStrategyType">
              <el-radio-group v-model="form.changeStrategyType" size="medium">
                <el-radio border :label=1 >支持换绑定</el-radio>
                <el-radio border :label=2 >不支持换绑定</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="多开策略" prop="dosingStrategyType">
              <el-radio-group v-model="form.dosingStrategyType" size="medium">
                <el-radio border :label=1 >只支持单机</el-radio>
                <el-radio border :label=2 >无限制</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item label="注册状态" prop="registerStatus">
              <el-radio-group v-model="form.registerStatus" size="medium">
                <el-radio border :label=1 @change="registerStatus = true">开放注册</el-radio>
                <el-radio border :label=2 @change="registerStatus = false">关闭注册</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item v-if="registerStatus == false" label="关闭注册后的返回信息" prop="registerCloseMsg">
              <el-input
                v-model="form.registerCloseMsg"
                class="common-width"
                type="textarea"
                :rows="4"
                placeholder="请输入关闭注册后的返回信息"
              >
              </el-input>
            </el-form-item>

            <el-form-item label="服务状态" prop="serviceStatus">
              <el-radio-group v-model="form.serviceStatus" size="medium">
                <el-radio border :label=1 @change="serviceStatus = true">收费</el-radio>
                <el-radio border :label=2 @change="serviceStatus = true">免费开放</el-radio>
                <el-radio border :label=3 @change="serviceStatus = false">关闭开放使用</el-radio>
              </el-radio-group>
            </el-form-item>

            <el-form-item v-if="serviceStatus == false" label="关闭状态下的返回信息" prop="appCloseMsg">
              <el-input
                v-model="form.appCloseMsg"
                class="common-width"
                type="textarea"
                :rows="4"
                placeholder="请输入关闭状态下的返回信息"
              >
              </el-input>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="submitForm('form')">提交</el-button>
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

      if (this.$route.query.id != null) {

        this.$axios.post("soft/getDetail", this.$qs.stringify({
          id: this.$route.query.id
        })).then((rsp) => {
          this.form = rsp.data.detail;
          delete this.form.createAt;
          delete this.form.updateAt;
          if (rsp.data.detail.registerStatus == 2) {
            this.registerStatus = false;
          }
          if (rsp.data.detail.serviceStatus == 3) {
            this.serviceStatus = false;
          }
        });
      }

    },
    methods: {
      //上一页
      openExpress() {
        this.$router.push({
          name: 'SoftList',
        })
      },
      //表单操作
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            if (this.$route.query.id != null) {
              this.submit(true);
            } else {
              this.submit(false);
            }
          } else {
            this.$message.error('提交错误');
            return false;
          }
        });
      },
      submit(isUpdate) {

        let data = this.form;

        let url = "soft/create";
        if (isUpdate == true) {
          url = "soft/update";
        }

        this.$axios({
          method: 'post',
          url: url,
          data: this.$qs.stringify(data),
        }).then((rsp) => {
          this.$message(rsp.msg);
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
    },
    data() {
      return {
        //收起放下
        searchWorkspace: true,

        registerStatus: true,
        serviceStatus: true,

        //表单配置
        form: {
          name: '',
          notice: '',
          changeStrategyType: 1,
          dosingStrategyType: 1,
          registerStatus: 1,
          registerCloseMsg: '',
          serviceStatus: 2,
          appCloseMsg: '',
        },
        forms: {
          name: [
            {required: true, message: '请填写软件名称', trigger: 'blur'},
          ],
          notice: [
            {required: true, message: '请填写软件公告', trigger: 'blur'},
            {min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur'}
          ],
          changeStrategyType: [
            {required: true, message: '请勾选换绑策略', trigger: 'blur'},
          ],
          dosingStrategyType: [
            {required: true, message: '请勾选多开策略', trigger: 'blur'},
          ],
          registerStatus: [
            {required: true, message: '请勾选注册状态', trigger: 'blur'},
          ],
          registerCloseMsg: [
            {required: true, message: '请填写关闭注册后的返回信息', trigger: 'blur'},
            {min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur'}
          ],
          serviceStatus: [
            {required: true, message: '请勾选服务状态', trigger: 'blur'},
          ],
          appCloseMsg: [
            {required: true, message: '请填写关闭状态下的返回信息', trigger: 'blur'},
            {min: 1, max: 255, message: '长度在 1 到 255 个字符', trigger: 'blur'}
          ],
        },

      }
    }
  }
</script>

<style>
  .common-width {
    width: 500px;
  }
</style>
