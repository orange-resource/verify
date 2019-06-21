using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using VerifyApi;
using VerifyTest.verify.data;

namespace VerifyTest.verify
{
    internal partial class VerifyUpdatePassword : Form
    {
        public VerifyUpdatePassword()
        {
            InitializeComponent();
        }

        private void buttonUpdatePassword_Click(object sender, EventArgs e)
        {
            if (textBoxAccount.Text == "")
            {
                MessageBox.Show("账号不能为空", "提示");
                return;
            }
            if (textBoxNewPassword.Text == "")
            {
                MessageBox.Show("新密码不能为空", "提示");
                return;
            }
            if (textBoxSecurityCode.Text == "")
            {
                MessageBox.Show("安全码不能为空", "提示");
                return;
            }

            try
            {
                string msg = VerifyApiLaunch.updatePassword
                (
                    textBoxAccount.Text,
                    textBoxNewPassword.Text,
                    VerifyOverAll.softId,
                    textBoxSecurityCode.Text
                );
                MessageBox.Show(msg, "修改通知");
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "软件错误");
            }
        }

        ////
    }
}
