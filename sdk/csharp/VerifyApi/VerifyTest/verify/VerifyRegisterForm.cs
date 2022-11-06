using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Windows.Forms;
using VerifyApi;
using VerifyTest.verify.data;

namespace VerifyTest.verify
{
    internal partial class VerifyRegisterForm : Form
    {
        public VerifyRegisterForm()
        {
            InitializeComponent();
        }

        /// <summary>
        /// 获取验证码
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonGetVc_Click(object sender, EventArgs e)
        {
            new Thread(new ThreadStart(() => 
            {
                try
                {
                    pictureBoxVc.Image = VerifyApiLaunch.getVerificationCode(VerifyOverAll.rsaPublicKey);
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message, "程序错误");
                }
            })).Start();
        }

        /// <summary>
        /// 点击注册按钮
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void buttonRegister_Click(object sender, EventArgs e)
        {
            if (textBoxAccount.Text == "")
            {
                MessageBox.Show("账号不能为空", "提示");
                return;
            }
            if (textBoxPassword.Text == "")
            {
                MessageBox.Show("密码不能为空", "提示");
                return;
            }
            if (textBoxQqNumber.Text == "")
            {
                MessageBox.Show("QQ号不能为空", "提示");
                return;
            }
            if (textBoxName.Text == "")
            {
                MessageBox.Show("姓名不能为空", "提示");
                return;
            }
            if (textBoxSecruityCode.Text == "")
            {
                MessageBox.Show("安全码不能为空", "提示");
                return;
            }
            if (textBoxVc.Text == "")
            {
                MessageBox.Show("验证码不能为空", "提示");
                return;
            }

            try
            {
                string msg = VerifyApiLaunch.register
                (
                    textBoxAccount.Text,
                    textBoxPassword.Text,
                    textBoxQqNumber.Text,
                    VerifyOverAll.softId,
                    textBoxName.Text,
                    textBoxSecruityCode.Text,
                    VerifyOverAll.rsaPublicKey,
                    textBoxVc.Text
                );
                MessageBox.Show(msg, "注册结果");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "程序错误");
            }
        }

        //////
    }
}
