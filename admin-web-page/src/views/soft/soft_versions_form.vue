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

            <el-form-item label="版本号" prop="number">
              <el-input v-model="form.number" class="common-width"></el-input>
            </el-form-item>

            <el-form-item label="更新公告(日志)" prop="notice">
              <el-input
                v-model="form.notice"
                class="common-width"
                type="textarea"
                :rows="15"
                placeholder="请输入更新公告"
              >
              </el-input>
            </el-form-item>

            <el-form-item label="更新地址" prop="updateUrl">
              <el-input v-model="form.updateUrl" class="common-width"></el-input>
            </el-form-item>

            <el-form-item label="是否强制更新" prop="forcedStatus">
              <el-radio-group v-model="form.forcedStatus" size="medium">
                <el-radio border :label=1 >不强制</el-radio>
                <el-radio border :label=2 >强制</el-radio>
              </el-radio-group>
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
        this.$axios.post("softVersion/getDetail", this.$qs.stringify({
          softId: this.$route.query.id
        })).then((rsp) => {
          if (rsp.data.detail != null) {
            this.form = rsp.data.detail;
            delete this.form.createAt;
            delete this.form.updateAt;
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
            if (this.$route.query.id != null && this.form.id != undefined) {
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

        let url = "softVersion/create";
        data.softId = this.$route.query.id;
        if (isUpdate == true) {
          url = "softVersion/update";
        }

        this.$axios({
          method: 'post',
          url: url,
          data: this.$qs.stringify(data),
        }).then((rsp) => {
          this.$message(rsp.msg);

          if (rsp.code == 200) {
            this.openExpress();
          }
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

        //表单配置
        form: {
          number: '',
          notice: '',
          forcedStatus: 1,
          updateUrl: '',
        },
        forms: {
          number: [
            { required: true, message: '请填写版本号', trigger: 'blur' },
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
